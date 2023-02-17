package tasks.infrastructure.primary.commands;

import tasks.domain.Task;
import tasks.domain.TaskId;
import tasks.domain.TaskRepository;
import tasks.domain.TaskState;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UpdateTaskCommandHandler {

    private final TaskRepository taskRepository;

    public UpdateTaskCommandHandler(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void handle(UpdateTaskCommand updateTaskCommand) {
        TaskId taskId = TaskId.of(updateTaskCommand.getId());
        Task taskRetrieved = taskRepository.readTaskById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));

        String content = updateTaskCommand.getContent().isBlank() ? taskRetrieved.getContent() : updateTaskCommand.getContent();
        LocalDateTime dueDate = !updateTaskCommand.getDueDate().isBlank() ? LocalDate.parse(updateTaskCommand.getDueDate()).atStartOfDay() : taskRetrieved.getDueDate();
        TaskState state = updateTaskCommand.getState().isBlank() ? taskRetrieved.getState() : TaskState.valueOf(updateTaskCommand.getState());
        LocalDateTime closeDate = taskRetrieved.getCloseDate();

        if (state == TaskState.done) {
            closeDate = LocalDateTime.now();
        }

        Task taskToUpdate = new Task(taskId, content, taskRetrieved.getCreationDate(), dueDate, closeDate, state, taskRetrieved.getSubTasks());

        taskRepository.updateTaskById(taskId, taskToUpdate);
    }
}
