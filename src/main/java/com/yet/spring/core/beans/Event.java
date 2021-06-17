package com.yet.spring.core.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;

@Component
@Scope("prototype")
public class Event {
    private final int id = new Random().nextInt();
    private String msg;

    @Autowired
    @Qualifier("newDate")
    private final Date date;

    @Autowired
    private final DateFormat dateFormat;

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.dateFormat = df;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public static boolean isDay(int start, int end) {
        var time = LocalTime.now();
        return time.getHour() >= start && time.getHour() < end;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + (dateFormat != null ? dateFormat.format(date) : date) +
                '}';
    }
}
