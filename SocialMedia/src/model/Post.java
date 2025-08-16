package model;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Post {
    private String desc;
    private List<String> url; //can hold 1 or multiple photos/videos
    private Timestamp timestamp;
    private List<String> likes; //hold list of userid
    private List<Comment> comments;
    private String userId;
    private String postId;

    public Post(String desc, List<String> url,String userId) {
        this.desc = desc;
        this.url = url;
        this.timestamp = timestamp;
        this.likes = new CopyOnWriteArrayList<>();
        this.comments = new CopyOnWriteArrayList<>();
        this.userId = userId;
        this.postId = UUID.randomUUID().toString();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
}
