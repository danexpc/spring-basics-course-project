package com.yet.spring.core;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.loggers.EventLogger;

public class App {
    private final Client client;
    private final EventLogger eventLogger;

    public App (Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
    }

    public void logEvent(String msg) {
        var message = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        eventLogger.logEvent(message);
    }
}
