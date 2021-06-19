package com.yet.spring.core;

import com.yet.spring.core.aspects.StatisticsAspect;
import com.yet.spring.core.beans.Client;
import com.yet.spring.core.beans.Event;
import com.yet.spring.core.beans.EventType;
import com.yet.spring.core.loggers.EventLogger;
import com.yet.spring.core.spring.AppConfig;
import com.yet.spring.core.spring.LoggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class App {

    private Client client;

    @Value("#{ T(com.yet.spring.core.beans.Event).isDay(8, 17) ? "
            + "cacheEventLogger : consoleEventLogger }")
    private EventLogger defaultLogger;

    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggers;

    @Value("#{'Hello user ' + "
            + "(systemProperties['os.name'].contains('Windows') ? "
            + "systemEnvironment['USERNAME'] : systemEnvironment['USER'])}")
    private String startupMessage;

    private StatisticsAspect statisticsAspect;

    public App() {}

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    @Autowired
    public App(Client client, StatisticsAspect statisticsAspect) {
        this.client = client;
        this.statisticsAspect = statisticsAspect;
    }

    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class, LoggerConfig.class);

        var app = (App) ctx.getBean("app");

        System.out.println(app.startupMessage);

        var client = ctx.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

        var event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        app.outputLoggingCounter();

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

    private void outputLoggingCounter() {
        if (statisticsAspect != null) {
            System.out.println("Loggers statistics. Number of calls: ");
            for (Map.Entry<Class<?>, Integer> entry: statisticsAspect.getCounter().entrySet()) {
                System.out.println("    " + entry.getKey().getSimpleName() + ": " + entry.getValue());
            }
        }
    }

    public EventLogger getDefaultLogger() {
        return defaultLogger;
    }
}
