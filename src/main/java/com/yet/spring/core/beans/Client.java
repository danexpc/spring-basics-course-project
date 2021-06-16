package com.yet.spring.core.beans;

import java.util.Objects;

public class Client {
    private int id;
    private String fullName;
    private String greeting;

    public Client() {}

    public Client(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var client = (Client) o;
        return id == client.id && Objects.equals(fullName, client.fullName) && Objects.equals(greeting, client.greeting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, greeting);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", greeting='" + greeting + '\'' +
                '}';
    }
}
