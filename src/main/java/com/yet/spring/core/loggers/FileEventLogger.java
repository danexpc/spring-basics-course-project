package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component("fileEventLogger")
public class FileEventLogger implements EventLogger {

    private final String filename;

    private File file;

    public FileEventLogger(@Value("logs/test.log") String filename) {
        this.filename = filename;
    }

    @PostConstruct
    public void init() throws IOException {
        this.file = new File(this.filename);
        if (!this.file.canWrite()) throw new IOException("Cannot write to file");
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(this.file, event.getMsg(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
