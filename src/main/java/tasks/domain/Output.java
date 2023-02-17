package tasks.domain;

public interface Output {

    void invalidArgument();

    void invalidDueDate();

    void invalidContent();

    void invalidStatus();

    void taskUnknown();

    void executionMessage();

}
