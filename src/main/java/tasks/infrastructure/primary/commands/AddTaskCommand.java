package tasks.infrastructure.primary.commands;

public class AddTaskCommand {
    private final String content;
    private final String dueDate;
    private final String state;

    public AddTaskCommand(String content, String dueDate, String state) {
        this.content = content;
        this.dueDate = dueDate;
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getState() {
        return state;
    }
}
