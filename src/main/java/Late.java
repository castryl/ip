import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Late {
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
        List<Task> userInputs = new ArrayList<>();

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
                for (Task item : userInputs) {
                    System.out.println(i + ".[" + item.getStatusIcon() + "] " + item.getDescription());
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
                userInputs.get(taskNumber - 1).markAsDone();
                System.out.println(taskNumber + ".[" + userInputs.get(taskNumber - 1).getStatusIcon()
                        + "] " + userInputs.get(taskNumber - 1).getDescription());
                System.out.println("____________________________________________________________");
                continue;
            }

            if (input.toLowerCase().startsWith("unmark ")) {
                String numberPart = input.substring(7); //everything after "mark "
                int taskNumber = Integer.parseInt(numberPart);
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                userInputs.get(taskNumber - 1).markAsUndone();
                System.out.println(taskNumber + ".[" + userInputs.get(taskNumber - 1).getStatusIcon()
                        + "] " + userInputs.get(taskNumber - 1).getDescription());
                System.out.println("____________________________________________________________");
                continue;
            }

            userInputs.add(new Task(input));
            System.out.println("____________________________________________________________");
            System.out.println("added: " + input);
            System.out.println("____________________________________________________________");
        }
    }
}