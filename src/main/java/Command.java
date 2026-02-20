public enum Command {
    TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, LIST, BYE, UNKNOWN;

    public static Command fromString(String input) {
        if (input == null || input.trim().isEmpty()) {
            return UNKNOWN;
        }
        // Extract the first word of the input (e.g., "todo" from "todo homework")
        String commandPart = input.trim().split(" ")[0].toUpperCase();
        try {
            return Command.valueOf(commandPart);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
