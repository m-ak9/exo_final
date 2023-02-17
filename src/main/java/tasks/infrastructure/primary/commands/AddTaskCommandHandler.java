package tasks.infrastructure.primary.commands;

import tasks.domain.Task;
import tasks.domain.TaskId;
import tasks.domain.TaskRepository;
import tasks.domain.TaskState;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddTaskCommandHandler {

    private final TaskRepository taskRepository;

    public AddTaskCommandHandler(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void handle(AddTaskCommand addTaskCommand) {
        LocalDateTime creationDate = LocalDateTime.now();
        LocalDateTime dueDate = !addTaskCommand.getDueDate().isBlank() ? LocalDate.parse(addTaskCommand.getDueDate()).atStartOfDay() : null;
        TaskState taskState = TaskState.valueOf(addTaskCommand.getState().isBlank() ? "todo" : addTaskCommand.getState());
        TaskId id = TaskId.of(taskRepository.nextIdentity());
        Task task = new Task(id, addTaskCommand.getContent(), creationDate, dueDate, null, taskState, null);
        taskRepository.createTask(task);
    }
}
