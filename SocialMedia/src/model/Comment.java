package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Comment {
    private String userId;
    private String postId;
    private String content;
    private Timestamp timestamp;

    public Comment(String userId,String content,String postId)
    {
        this.userId = userId;
        this.content = content;
        this.postId = postId;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
