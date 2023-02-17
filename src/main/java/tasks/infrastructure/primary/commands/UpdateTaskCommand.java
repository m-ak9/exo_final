package tasks.infrastructure.primary.commands;

public final class UpdateTaskCommand {
    private final int id;
    private final String content;
    private final String dueDate;
    private final String state;

    public UpdateTaskCommand(int id, String content, String dueDate, String state) {
        this.id = id;
        this.content = content;
        this.dueDate = dueDate;
        this.state = state;
    }

    public int getId() {
        return id;
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
