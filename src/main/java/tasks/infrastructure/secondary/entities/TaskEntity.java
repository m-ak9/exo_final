package tasks.infrastructure.secondary.entities;

import java.util.List;

public class TaskEntity {

    int id;
    String content;
    String creationDate;
    String dueDate;
    String closeDate;
    String state;
    List<TaskEntity> subTasks;

    public TaskEntity(int id, String content, String creationDate, String dueDate, String closeDate, String state, List<TaskEntity> subTasks) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.closeDate = closeDate;
        this.state = state;
        this.subTasks = subTasks;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public String getState() {
        return state;
    }

    public List<TaskEntity> getSubTasks() {
        return subTasks;
    }
}
