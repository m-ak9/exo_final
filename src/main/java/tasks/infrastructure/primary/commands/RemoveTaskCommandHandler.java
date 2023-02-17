package tasks.infrastructure.primary.commands;

import tasks.domain.TaskId;
import tasks.domain.TaskRepository;

public class RemoveTaskCommandHandler {

    private final TaskRepository taskRepository;

    public RemoveTaskCommandHandler(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void handle(RemoveTaskCommand removeTaskCommand) {
        TaskId taskId = TaskId.of(removeTaskCommand.getTaskId());
        taskRepository.deleteTaskById(taskId);
    }
}
