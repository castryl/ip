import java.util.Scanner;

public class Late {
    /**
     * Echoes an input and exits if input is "bye".
     * @param args text input from user
     */
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Late!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");
        }
    }
}