package com.webtech.todo.pojo;

public class Todo {
    private int id;
    private String name;
    private String urgency;

    public Todo() {
    }

    public Todo(int id, String name, String urgency) {
        this.id = id;
        this.name = name;
        this.urgency = urgency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }
}
