package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private final int cacheSize;
    private final List<Event> cache;

    public CacheFileEventLogger(String filename, Integer cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<>(cacheSize);
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        for (Event event: cache) {
            super.logEvent(event);
        }
    }
}
