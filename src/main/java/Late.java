import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Late {
    /**
     * Prints lines before and after the input text
     * @param text
     */
    public static void printWithLines(String text) {
        System.out.println("____________________________________________________________");
        System.out.println(text);
        System.out.println("____________________________________________________________");
    }
    /**
     * Echoes text inputs and saves them in a list
     * Prints the list if input is "list"
     * Exits if input is "bye"
     * @param args text input from user
     */
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Late!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        List<Task> userTasks = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            if (input.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                int i = 1;
                for (Task item : userTasks) {
                    System.out.println(i + "." + item.toString());
                    i++;
                }
                System.out.println("____________________________________________________________");
                continue;
            }

            if (input.toLowerCase().startsWith("mark ")) {
                String numberPart = input.substring(5); //everything after "mark "
                int taskNumber = Integer.parseInt(numberPart);
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                userTasks.get(taskNumber - 1).markAsDone();
                System.out.println(taskNumber + "." + userTasks.get(taskNumber - 1).toString());
                System.out.println("____________________________________________________________");
                continue;
            }

            if (input.toLowerCase().startsWith("unmark ")) {
                String numberPart = input.substring(7); //everything after "mark "
                int taskNumber = Integer.parseInt(numberPart);
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                userTasks.get(taskNumber - 1).markAsUndone();
                System.out.println(taskNumber + "." + userTasks.get(taskNumber - 1).toString());
                System.out.println("____________________________________________________________");
                continue;
            }

            if (input.toLowerCase().startsWith("todo ")) {
                userTasks.add(new ToDo(input.substring(5)));
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(userTasks.get(userTasks.size() - 1).toString());
                System.out.println("Now you have " + userTasks.size() + " task(s) in the list.");
                System.out.println("____________________________________________________________");
                continue;
            }

            if (input.toLowerCase().startsWith("deadline ")) {
                // Remove "deadline " from the start
                String remaining = input.substring(9).trim();

                // Split into description and deadline
                String[] parts = remaining.split("/by");

                String description = parts[0].trim();
                String by = parts[1].trim();
                userTasks.add(new Deadline(description, by));

                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(userTasks.get(userTasks.size() - 1).toString());
                System.out.println("Now you have " + userTasks.size() + " task(s) in the list.");
                System.out.println("____________________________________________________________");
                continue;
            }

            if (input.toLowerCase().startsWith("event ")) {
                // Remove "deadline " from the start
                String remaining = input.substring(6).trim();

                // Split at /from
                String[] firstSplit = remaining.split("/from");
                String description = firstSplit[0].trim();

                // Split the second part at /to
                String[] secondSplit = firstSplit[1].split("/to");
                String from = secondSplit[0].trim();
                String to = secondSplit[1].trim();

                userTasks.add(new Event(description, from, to));

                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(userTasks.get(userTasks.size() - 1).toString());
                System.out.println("Now you have " + userTasks.size() + " task(s) in the list.");
                System.out.println("____________________________________________________________");
                continue;
            }

            userTasks.add(new Task(input));
            System.out.println("____________________________________________________________");
            System.out.println("added: " + input);
            System.out.println("Now you have " + userTasks.size() + " task(s) in the list.");
            System.out.println("____________________________________________________________");
        }
    }
}