package tasks.domain;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    int nextIdentity();

    void createTask(Task newTask);

    Optional<Task> readTaskById(TaskId id);

    List<Task> readAllTask();

    void updateTaskById(TaskId id, Task updatedTask);

    void deleteTaskById(TaskId id);
}
