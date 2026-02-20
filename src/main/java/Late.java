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
            Command command = Command.fromString(input);

            try {
                switch (command) {
                case BYE:
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    return;

                case LIST:
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    int i = 1;
                    for (Task item : userTasks) {
                        System.out.println(i + "." + item.toString());
                        i++;
                    }
                    System.out.println("____________________________________________________________");
                    break;

                case MARK:
                    if (input.length() < 6) {
                        throw new LateException("Please specify the task number to mark.");
                    }
                    String markNumberPart = input.substring(5);
                    int markTaskNumber = Integer.parseInt(markNumberPart);
                    if (markTaskNumber < 1 || markTaskNumber > userTasks.size()) {
                        throw new LateException("Task number out of range.");
                    }
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    userTasks.get(markTaskNumber - 1).markAsDone();
                    System.out.println(markTaskNumber + "." + userTasks.get(markTaskNumber - 1).toString());
                    System.out.println("____________________________________________________________");
                    break;

                case UNMARK:
                    if (input.length() < 8) {
                        throw new LateException("Please specify the task number to unmark.");
                    }
                    String unmarkNumberPart = input.substring(7);
                    int unmarkTaskNumber = Integer.parseInt(unmarkNumberPart);
                    if (unmarkTaskNumber < 1 || unmarkTaskNumber > userTasks.size()) {
                        throw new LateException("Task number out of range.");
                    }
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    userTasks.get(unmarkTaskNumber - 1).markAsUndone();
                    System.out.println(unmarkTaskNumber + "." + userTasks.get(unmarkTaskNumber - 1).toString());
                    System.out.println("____________________________________________________________");
                    break;

                case TODO:
                    if (input.length() < 6) {
                        throw new LateException("The todo command must have a description. e.g. todo homework");
                    }
                    userTasks.add(new ToDo(input.substring(5)));
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(userTasks.get(userTasks.size() - 1).toString());
                    System.out.println("Now you have " + userTasks.size() + " task(s) in the list.");
                    System.out.println("____________________________________________________________");
                    break;

                case DEADLINE:
                    if (input.length() < 9) {
                        throw new LateException("Deadlines must contain a /by e.g. deadline essay /by 2pm");
                    }
                    String deadlineRemaining = input.substring(9).trim();
                    String[] deadlineParts = deadlineRemaining.split("/by");
                    if (deadlineParts.length < 2) {
                        throw new LateException("Deadlines must contain a /by e.g. deadline essay /by 2pm");
                    }
                    String deadlineDescription = deadlineParts[0].trim();
                    String by = deadlineParts[1].trim();
                    if (deadlineDescription.isEmpty()) {
                        throw new LateException("The description of a deadline cannot be empty.");
                    }
                    if (by.isEmpty()) {
                        throw new LateException("The deadline date/time cannot be empty.");
                    }
                    userTasks.add(new Deadline(deadlineDescription, by));
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(userTasks.get(userTasks.size() - 1).toString());
                    System.out.println("Now you have " + userTasks.size() + " task(s) in the list.");
                    System.out.println("____________________________________________________________");
                    break;

                case EVENT:
                    if (input.length() < 7) {
                        throw new LateException("Events must contain a /from and /to e.g. event exam /from 2pm /to 4pm");
                    }
                    String eventRemaining = input.substring(6).trim();
                    String[] firstSplit = eventRemaining.split("/from");
                    if (firstSplit.length < 2) {
                        throw new LateException("Events must contain a /from. e.g. event exam /from 2pm /to 4pm");
                    }
                    String eventDescription = firstSplit[0].trim();
                    String[] secondSplit = firstSplit[1].split("/to");
                    if (secondSplit.length < 2) {
                        throw new LateException("Events must contain a /to. e.g. event exam /from 2pm /to 4pm");
                    }
                    String from = secondSplit[0].trim();
                    String to = secondSplit[1].trim();
                    if (eventDescription.isEmpty()) {
                        throw new LateException("The description of an event cannot be empty.");
                    }
                    if (from.isEmpty()) {
                        throw new LateException("The 'from' time of an event cannot be empty.");
                    }
                    if (to.isEmpty()) {
                        throw new LateException("The 'to' time of an event cannot be empty.");
                    }
                    userTasks.add(new Event(eventDescription, from, to));
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(userTasks.get(userTasks.size() - 1).toString());
                    System.out.println("Now you have " + userTasks.size() + " task(s) in the list.");
                    System.out.println("____________________________________________________________");
                    break;

                case DELETE:
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
                    break;

                case UNKNOWN:
                default:
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