package tasks.infrastructure.primary.commands;

public final class RemoveTaskCommand {
    private final int taskId;

    public RemoveTaskCommand(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }
}
