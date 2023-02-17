package tasks.infrastructure.secondary;

import tasks.domain.Logger;

import java.time.LocalDateTime;

import static java.lang.String.format;

public class LoggerService implements Logger {

    @Override
    public void err(String log) {
        StringBuilder message = new StringBuilder();
        message.append(format("[err][%s] ", LocalDateTime.now()));
        message.append(log);
        System.out.println(message);
    }

    @Override
    public void ok(String log) {
        StringBuilder message = new StringBuilder();
        message.append(format("[ok+][%s] ", LocalDateTime.now()));
        message.append(log);
        System.out.println();
    }
}
