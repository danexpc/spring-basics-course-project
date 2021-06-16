package com.yet.spring.core.loggers;

import com.yet.spring.core.events.Event;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
