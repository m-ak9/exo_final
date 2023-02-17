package tasks.infrastructure.secondary.entities;

import tasks.domain.Task;
import tasks.domain.TaskId;
import tasks.domain.TaskState;
import tasks.infrastructure.secondary.entities.TaskEntity;

import java.time.LocalDateTime;

public class TaskMapper {

    public TaskEntity fromDomain(Task task) {
        return new TaskEntity(
                task.getId().getValue(),
                task.getContent(),
                task.getCreationDate().toString(),
                task.getDueDate() != null ? task.getDueDate().toString() : "",
                task.getCloseDate() != null ? task.getCloseDate().toString() : "",
                task.getState().toString(),
                task.getSubTasks() != null
                        ? task.getSubTasks().stream().map(this::fromDomain).toList()
                        : null
        );
    }

    public Task toDomain(TaskEntity entity) {
        return new Task(
                TaskId.of(entity.getId()),
                entity.getContent(),
                LocalDateTime.parse(entity.getCreationDate()),
                entity.getDueDate().isBlank() ? null : LocalDateTime.parse(entity.getDueDate()),
                entity.getCloseDate().isBlank() ? null : LocalDateTime.parse(entity.getCloseDate()),
                TaskState.valueOf(entity.getState()),
                entity.getSubTasks() != null ? entity.getSubTasks().stream().map(this::toDomain).toList() : null
        );
    }
}
