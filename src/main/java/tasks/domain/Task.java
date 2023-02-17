package tasks.domain;

import java.time.LocalDateTime;
import java.util.List;

public final class Task {

    private final TaskId id;
    private final String content;
    private final LocalDateTime creationDate;
    private final LocalDateTime dueDate;
    private final LocalDateTime closeDate;
    private final TaskState state;
    private final List<Task> subTasks;

    public Task(TaskId id, String content, LocalDateTime creationDate, LocalDateTime dueDate, LocalDateTime closeDate, TaskState state, List<Task> subTasks) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.closeDate = closeDate;
        this.state = state;
        this.subTasks = subTasks;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    public TaskState getState() {
        return state;
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }

    public TaskId getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", dueDate=" + dueDate +
                ", closeDate=" + closeDate +
                ", state=" + state +
                ", subTasks=" + subTasks +
                '}';
    }
}
