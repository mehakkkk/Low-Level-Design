package model;

import Utility.Util;

import java.sql.Timestamp;

// TaskBuilder Class
public class TaskBuilder {
    public String id;
    private String name;
    private String desc;
    private int points;
    private User user;

    public String getId() {
        return id;
    }

    private void setId() {
        this.id = Util.generateId();
    }

    public TaskBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TaskBuilder setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public TaskBuilder setPoints(int points) {
        this.points = points;
        return this;
    }

    public TaskBuilder setUser(User user) {
        this.user = user;
        return this;
    }


    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getPoints() {
        return points;
    }

    public User getUser() {
        return user;
    }

    // Build method to create the Task object
    public Task build() {
        setId();
        return new Task(this);
    }
}