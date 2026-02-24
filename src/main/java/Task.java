public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String toSaveString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}