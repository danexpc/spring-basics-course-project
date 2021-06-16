package com.yet.spring.core;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.loggers.EventLogger;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public static void main(String[] args) {
        var app = new App();

        app.logEvent("Some event for user 1");
    }

    public void logEvent(String msg) {
        var message = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        eventLogger.logEvent(message);
    }
}
