package tasks.infrastructure.primary.queries;

import tasks.domain.TaskRepository;

public class ListTaskQueryHandler {

    private final TaskRepository taskRepository;

    public ListTaskQueryHandler(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void handle() {
        taskRepository.readAllTask().forEach(System.out::println);
    }
}
