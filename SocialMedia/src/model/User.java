package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class User {
    private String dpUrl;
    private String bio;
    private String userName;
    List<User> friends;
    List<Post> posts;


    public User() {
    }

    public User(String userName) {
        this.userName = userName;
        this.friends = new CopyOnWriteArrayList<>();  // Thread-safe list
        this.posts = new CopyOnWriteArrayList<>();
    }

    public String getDpUrl() {
        return dpUrl;
    }

    public void setDpUrl(String dpUrl) {
        this.dpUrl = dpUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriend(User friend) {
        this.friends.add(friend);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPost(Post post) {
        this.posts.add(post);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
