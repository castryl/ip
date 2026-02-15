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
        List<String> userInputs = new ArrayList<>();

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
                int i = 1;
                for (String item : userInputs) {
                    System.out.println(i + ". " + item);
                    i++;
                }
                System.out.println("____________________________________________________________");
                continue;
            }

            userInputs.add(input);
            System.out.println("____________________________________________________________");
            System.out.println("added: " + input);
            System.out.println("____________________________________________________________");
        }
    }
}