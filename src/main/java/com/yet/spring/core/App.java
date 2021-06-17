package com.yet.spring.core;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.beans.Event;
import com.yet.spring.core.beans.EventType;
import com.yet.spring.core.loggers.EventLogger;
import com.yet.spring.core.spring.AppConfig;
import com.yet.spring.core.spring.LoggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class App {

    @Autowired
    private final Client client;

    @Resource(name = "defaultLogger")
    private final EventLogger defaultLogger;

    @Resource(name = "loggerMap")
    private final Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class, LoggerConfig.class);

        var app = (App) ctx.getBean("app");

        var client = ctx.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

        var event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        ctx.close();
    }

    public void logEvent(EventType type, Event event, String msg) {
        var message = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        event.setMsg(message);

        var logger = loggers.get(type);

        if (logger == null) {
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }
}
