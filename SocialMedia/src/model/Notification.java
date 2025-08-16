package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {
    private String fromUserId;
    private String toUserId;
    private Timestamp timestamp;
    private NotificationType notificationType;
    private String content;
    private String id;

    public Notification(String fromUserId,String toUserId,NotificationType notificationType,String content)
    {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.notificationType = notificationType;
        this.content = content;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.id = UUID.randomUUID().toString();
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
