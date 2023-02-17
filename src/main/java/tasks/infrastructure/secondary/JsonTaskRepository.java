package tasks.infrastructure.secondary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tasks.domain.Task;
import tasks.domain.TaskId;
import tasks.domain.TaskRepository;
import tasks.infrastructure.secondary.entities.TaskEntity;
import tasks.infrastructure.secondary.entities.TaskMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.lang.String.format;

public final class JsonTaskRepository implements TaskRepository {

    private static final String FILENAME = "./data.json";
    private final LoggerService log = new LoggerService();
    private final AtomicInteger count;
    private Map<Integer, Task> data;
    private final Gson gson;
    private final FileService fileService;
    private final TaskMapper taskMapper;

    public JsonTaskRepository(FileService fileService) {
        this.count = new AtomicInteger();
        this.data = new ConcurrentHashMap<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.fileService = fileService;
        this.taskMapper = new TaskMapper();
        this.data = readAllTask().stream().collect(Collectors.toMap(task -> task.getId().getValue(), task -> task));
    }

    @Override
    public int nextIdentity() {
        return this.count.getAndIncrement();
    }

    @Override
    public void createTask(Task newTask) {
        log.ok(format("add %s", newTask.getId().getValue()));
        data.put(newTask.getId().getValue(), newTask);
        doSnapshot();
    }

    @Override
    public Optional<Task> readTaskById(TaskId id) {
        log.ok(format("read task n°%s", id.getValue()));
        return Optional.of(data.get(id.getValue()));
    }

    @Override
    public List<Task> readAllTask() {
        log.ok("read all Tasks");
        List<Task> tasks = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            List<TaskEntity> tasksFromFile = Arrays.stream(gson.fromJson(sb.toString(), TaskEntity[].class)).toList();

            tasksFromFile.forEach(taskEntity -> {
                tasks.add(taskMapper.toDomain(taskEntity));
            });
        } catch (IOException err) {
            log.err("Tasks not found");
            err.printStackTrace();
        }

        return tasks;
    }

    @Override
    public void updateTaskById(TaskId id, Task updatedTask) {
        if (!data.containsKey(id.getValue())) {
            log.ok(format(
                    "update %s : task #%s does not exist",
                    updatedTask.getId().getValue(),
                    updatedTask.getId().getValue()
            ));
            return;
        }

        log.ok(format("update %s", updatedTask.getId().getValue()));
        data.put(updatedTask.getId().getValue(), updatedTask);
        doSnapshot();
    }

    @Override
    public void deleteTaskById(TaskId id) {
        log.ok(format("remove %s", id.getValue()));
        data.remove(id.getValue());
        doSnapshot();
    }

    private void doSnapshot() {
        String snapshot = gson.toJson(new ArrayList<>(data.values().stream().map(taskMapper::fromDomain).collect(Collectors.toList())));
        fileService.write(snapshot);
    }
}
