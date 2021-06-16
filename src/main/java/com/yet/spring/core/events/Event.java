package com.yet.spring.core.events;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    private final int id = new Random().nextInt();
    private String msg;
    private final Date date;
    private final DateFormat df;

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
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
                ", date=" + df.format(date) +
                '}';
    }
}
