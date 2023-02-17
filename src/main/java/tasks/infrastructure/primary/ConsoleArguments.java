package tasks.infrastructure.primary;

import tasks.infrastructure.primary.commands.*;
import tasks.infrastructure.primary.queries.ListTaskQueryHandler;

import java.util.Arrays;
import java.util.List;

public class ConsoleArguments {

    private final AddTaskCommandHandler addTaskCommandHandler;
    private final ListTaskQueryHandler listTaskQueryHandler;
    private final RemoveTaskCommandHandler removeTaskCommandHandler;
    private final UpdateTaskCommandHandler updateTaskCommandHandler;

    public ConsoleArguments(AddTaskCommandHandler addTaskCommandHandler, ListTaskQueryHandler listTaskQueryHandler, RemoveTaskCommandHandler removeTaskCommandHandler, UpdateTaskCommandHandler updateTaskCommandHandler) {
        this.addTaskCommandHandler = addTaskCommandHandler;
        this.listTaskQueryHandler = listTaskQueryHandler;
        this.removeTaskCommandHandler = removeTaskCommandHandler;
        this.updateTaskCommandHandler = updateTaskCommandHandler;
    }

    public void handleConsoleCommands(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments given.");
        } else {
            List<String> argsList = Arrays.asList(args);
            switch (argsList.get(0)) {
                case "add":
                    addCommand(argsList.subList(1, argsList.size()));
                    break;
                case "remove":
                    removeCommand(argsList.subList(1, argsList.size()));
                    break;
                case "list":
                    listQuery();
                    break;
                case "update":
                    updateCommand(argsList.subList(1, argsList.size()));
                    break;
                default:
                    System.out.println("Unknown command.");
            }
        }
    }

    private void listQuery() {
        listTaskQueryHandler.handle();
    }

    private void addCommand(List<String> strings) {
        String content = "";
        String dueDate = "";
        String state = "";

        if (strings.size() == 0) {
            System.out.println("No arguments given.");
        }

        for (String arg : strings) {
            if (arg.equals("-c"))
                content = strings.get(strings.indexOf(arg) + 1);
            if (arg.equals("-d"))
                dueDate = strings.get(strings.indexOf(arg) + 1).substring(1);
            if (arg.equals("-s"))
                state = strings.get(strings.indexOf(arg) + 1).substring(1);
        }

        AddTaskCommand addTaskCommand = new AddTaskCommand(content, dueDate, state);
        addTaskCommandHandler.handle(addTaskCommand);

    }

    private void updateCommand(List<String> strings) {
        if (strings.size() == 0) {
            System.out.println("No arguments given.");
        }

        int id = Integer.parseInt(strings.get(0));
        String content = "";
        String dueDate = "";
        String state = "";
        for (String arg : strings) {
            if (arg.equals("-c"))
                content = strings.get(strings.indexOf(arg) + 1);
            if (arg.equals("-d"))
                dueDate = strings.get(strings.indexOf(arg) + 1).substring(1);
            if (arg.equals("-s"))
                state = strings.get(strings.indexOf(arg) + 1).substring(1);
        }

        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(id, content, dueDate, state);

        updateTaskCommandHandler.handle(updateTaskCommand);
    }

    private void removeCommand(List<String> strings) {
        if (strings.size() == 0) {
            System.out.println("No arguments given.");
        }

        int id = Integer.parseInt(strings.get(0));

        RemoveTaskCommand removeTaskCommand = new RemoveTaskCommand(id);

        removeTaskCommandHandler.handle(removeTaskCommand);
    }
}
