package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class Account {
    private String email;
    private String password;
    private String accId;
    private User user;
    private Timestamp createdAt;

    public Account(String userName, String email, String password) {
        this.email = email;
        this.password = password;
        this.accId = UUID.randomUUID().toString();
        this.user = new User(userName);
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
