package model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Task implements Cloneable {
    private String id;
    private String name;
    private String desc;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int points;
    private User user;  // Reference to another object (shallow copy will copy the reference)
    private TaskStatus taskStatus;
    private AtomicInteger version;

    // Private constructor to enforce using the Builder pattern
    public Task(TaskBuilder builder) {
        this.id = builder.getId();
        this.name = builder.getName();
        this.desc = builder.getDesc();
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = this.createdAt;
        this.points = builder.getPoints();
        this.user = builder.getUser();  // Reference will be copied directly
        this.taskStatus = TaskStatus.OPEN;
        this.version = new AtomicInteger(1);
    }

    // Getter methods for fields
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public int getPoints() {
        return points;
    }

    public User getUser() {
        return user;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void updateVersion() {
        version.incrementAndGet();
        this.modifiedAt = LocalDateTime.now();
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public synchronized void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getVersion() {
        return version.get();
    }

    // Method to perform a shallow copy (using super.clone())
    @Override
    public Task clone() {
        try {
            return (Task) super.clone(); // Performs a shallow copy (references are copied as is)
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ", name='" + name + "', desc='" + desc + "', createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt + ", points=" + points + ", user=" + user + "}";
    }
}
