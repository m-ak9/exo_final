package tasks.infrastructure.secondary;

import com.google.gson.Gson;
import tasks.domain.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.String.format;

// WriterService, ReaderService if we want to respect CQS
public class FileService {
    private final Logger log = Logger.getLogger(FileService.class.getName());
    private static final String FILENAME = "./data.json";

    public void write(String content) {
        String filePath = "./data.json";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(content);
            writer.close();
            log.info(format("[wip] success to write in file [%s]", filePath));
        } catch (IOException e) {
            log.info(format("[wip] fail to write in file [%s]", filePath));
            throw new RuntimeException(e);
        }
    }

    public List<Task> read() {
        log.info("[READ] content read ");
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            Gson gson = new Gson();
            String line;
            while ((line = br.readLine()) != null) {
                Task task = gson.fromJson(line, Task.class);
                tasks.add(task);
            }
        } catch (IOException err) {
            log.info("[READ] No content found");
            err.printStackTrace();
        }

        return tasks;
    }
}
