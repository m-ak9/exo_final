package tasks.domain;

public interface Logger {
    void err(String logMessage);

    void ok(String logMessage);
}
