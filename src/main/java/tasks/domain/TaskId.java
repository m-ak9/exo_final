package tasks.domain;

public final class TaskId {

    private final int value;

    private TaskId(int id) {
        this.value = id;
    }

    public static TaskId of(int id) {
        return new TaskId(id);
    }

    public int getValue() {
        return value;
    }
}
