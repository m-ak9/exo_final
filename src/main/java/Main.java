import tasks.domain.TaskRepository;
import tasks.infrastructure.primary.ConsoleArguments;
import tasks.infrastructure.primary.commands.AddTaskCommandHandler;
import tasks.infrastructure.primary.commands.RemoveTaskCommandHandler;
import tasks.infrastructure.primary.commands.UpdateTaskCommandHandler;
import tasks.infrastructure.primary.queries.ListTaskQueryHandler;
import tasks.infrastructure.secondary.FileService;
import tasks.infrastructure.secondary.JsonTaskRepository;

public class Main {
    public static void main(String[] args) {
        //Infrastructure
        FileService fileService = new FileService();
        TaskRepository taskRepository = new JsonTaskRepository(fileService);

        // Handlers
        AddTaskCommandHandler addTaskCommandHandler = new AddTaskCommandHandler(taskRepository);
        RemoveTaskCommandHandler removeTaskCommandHandler = new RemoveTaskCommandHandler(taskRepository);
        UpdateTaskCommandHandler updateTaskCommandHandler = new UpdateTaskCommandHandler(taskRepository);
        ListTaskQueryHandler listTaskQueryHandler = new ListTaskQueryHandler(taskRepository);

        ConsoleArguments consoleArguments = new ConsoleArguments(addTaskCommandHandler, listTaskQueryHandler, removeTaskCommandHandler, updateTaskCommandHandler);
        consoleArguments.handleConsoleCommands(args);
    }
}
