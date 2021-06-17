package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component("cacheEventLogger")
public class CacheFileEventLogger extends FileEventLogger {

    private final int cacheSize;

    private List<Event> cache;

    public CacheFileEventLogger(@Value("logs/test.log") String filename, @Value("${cache.size:5}") Integer cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    @PostConstruct
    public void initCache() {
        this.cache = new ArrayList<>(cacheSize);
    }

    private void writeEventsFromCache() {
        for (Event event : cache) {
            super.logEvent(event);
        }
    }

    @PreDestroy
    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }
}
