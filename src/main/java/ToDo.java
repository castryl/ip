public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toSaveString() {
        return "T | " + super.toSaveString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}