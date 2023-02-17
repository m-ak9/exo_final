package tasks.domain;

public enum TaskState {
    todo(0),
    pending(1),
    progress(2),
    done(3),
    cancelled(4),
    closed(5);

    private int value;

    private TaskState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getStringFromValue(int value) {
        for (TaskState s : TaskState.values()) {
            if (s.getValue() == value) {
                return s.name().toLowerCase();
            }
        }
        return null;
    }
}
