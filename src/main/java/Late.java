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

            try {
                if (input.equalsIgnoreCase("bye")) {
                    // Exit program
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                }

                if (input.equalsIgnoreCase("list")) {
                    // List all tasks
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

                if (input.toLowerCase().startsWith("mark")) {
                    // Mark task
                    if (input.length() < 6) {
                        throw new LateException("Please specify the task number to mark.");
                    }
                    String numberPart = input.substring(5); //everything after "mark "
                    int taskNumber = Integer.parseInt(numberPart);
                    if (taskNumber < 1 || taskNumber > userTasks.size()) {
                        throw new LateException("Task number out of range.");
                    }
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    userTasks.get(taskNumber - 1).markAsDone();
                    System.out.println(taskNumber + "." + userTasks.get(taskNumber - 1).toString());
                    System.out.println("____________________________________________________________");
                    continue;
                }

                if (input.toLowerCase().startsWith("unmark")) {
                    // Unmark task
                    if (input.length() < 8) {
                        throw new LateException("Please specify the task number to unmark.");
                    }
                    String numberPart = input.substring(7); //everything after "unmark "
                    int taskNumber = Integer.parseInt(numberPart);
                    if (taskNumber < 1 || taskNumber > userTasks.size()) {
                        throw new LateException("Task number out of range.");
                    }
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    userTasks.get(taskNumber - 1).markAsUndone();
                    System.out.println(taskNumber + "." + userTasks.get(taskNumber - 1).toString());
                    System.out.println("____________________________________________________________");
                    continue;
                }

                if (input.toLowerCase().startsWith("todo")) {
                    // Check for description after todo
                    if (input.length() < 6) {
                        throw new LateException("The todo command must have a description. e.g. todo homework");
                    }

                    // Add todo
                    userTasks.add(new ToDo(input.substring(5)));
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(userTasks.get(userTasks.size() - 1).toString());
                    System.out.println("Now you have " + userTasks.size() + " task(s) in the list.");
                    System.out.println("____________________________________________________________");
                    continue;
                }

                if (input.toLowerCase().startsWith("deadline")) {
                    // Check for complete deadline input
                    if (input.length() < 9) {
                        throw new LateException("Deadlines must contain a /by e.g. deadline essay /by 2pm");
                    }
                    String remaining = input.substring(9).trim();

                    // Split into description and deadline
                    String[] parts = remaining.split("/by");

                    // Check for /by and its trailing input
                    if (parts.length < 2) {
                        throw new LateException("Deadlines must contain a /by e.g. deadline essay /by 2pm");
                    }

                    String description = parts[0].trim();
                    String by = parts[1].trim();

                    if (description.isEmpty()) {
                        throw new LateException("The description of a deadline cannot be empty.");
                    }
                    if (by.isEmpty()) {
                        throw new LateException("The deadline date/time cannot be empty.");
                    }

                    userTasks.add(new Deadline(description, by));

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(userTasks.get(userTasks.size() - 1).toString());
                    System.out.println("Now you have " + userTasks.size() + " task(s) in the list.");
                    System.out.println("____________________________________________________________");
                    continue;
                }

                if (input.toLowerCase().startsWith("event")) {
                    // Check for complete deadline input
                    if (input.length() < 7) {
                        throw new LateException("Events must contain a /from and /to e.g. event exam /from 2pm /to 4pm");
                    }
                    // Remove "event " from the start
                    String remaining = input.substring(6).trim();

                    // Split at /from
                    String[] firstSplit = remaining.split("/from");
                    if (firstSplit.length < 2) {
                        throw new LateException("Events must contain a /from. e.g. event exam /from 2pm /to 4pm");
                    }
                    String description = firstSplit[0].trim();

                    // Split the second part at /to
                    String[] secondSplit = firstSplit[1].split("/to");
                    if (secondSplit.length < 2) {
                        throw new LateException("Events must contain a /to. e.g. event exam /from 2pm /to 4pm");
                    }
                    String from = secondSplit[0].trim();
                    String to = secondSplit[1].trim();

                    if (description.isEmpty()) {
                        throw new LateException("The description of an event cannot be empty.");
                    }
                    if (from.isEmpty()) {
                        throw new LateException("The 'from' time of an event cannot be empty.");
                    }
                    if (to.isEmpty()) {
                        throw new LateException("The 'to' time of an event cannot be empty.");
                    }

                    userTasks.add(new Event(description, from, to));

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(userTasks.get(userTasks.size() - 1).toString());
                    System.out.println("Now you have " + userTasks.size() + " task(s) in the list.");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                if (input.toLowerCase().startsWith("delete")) {
                    if (input.length() < 8) {
                        throw new LateException("Task number to be deleted not indicated");
                    }
                    int deleteIndex = Integer.parseInt(input.substring(7));
                    if (deleteIndex < 1 || deleteIndex > userTasks.size()) {
                        throw new LateException("Task number out of range.");
                    }
                    System.out.println("____________________________________________________________");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(userTasks.get(deleteIndex - 1).toString());
                    userTasks.remove(deleteIndex - 1);
                    System.out.println("Now you have " + userTasks.size() + " task(s) in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new LateException("Unrecognised command entered");
                }
            } catch (LateException e) {
                System.out.println("____________________________________________________________");
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("____________________________________________________________");
                System.out.println("ERROR: Please enter a valid task number.");
                System.out.println("____________________________________________________________");
            }
        }
    }
}