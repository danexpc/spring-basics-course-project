package com.yet.spring.core.events;

import java.util.Date;
import java.util.Random;

public class Event {
    private final int id = new Random().nextInt();
    private String msg;
    private final Date date;

    public Event() {
        this.date = new Date();
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

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + date +
                '}';
    }
}
