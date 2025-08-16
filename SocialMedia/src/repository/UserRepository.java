package repository;

import model.Post;
import model.User;
import utility.UserNameBasedProfileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class UserRepository {

    private static UserRepository instance;
    private final Map<String, User> userMap = new ConcurrentHashMap<>();
    private final ReentrantLock lock = new ReentrantLock(); // Reentrant lock for synchronization

    private UserRepository() { // only one instance of the database repository
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                instance = new UserRepository();
            }
        }
        return instance;
    }

    // Method to create a new user account
    public boolean userCreation(User user) {
        lock.lock(); // Acquire lock before modifying the userMap
        try {
            String email = UserNameBasedProfileUtil.getInstance().getUserEmail(user.getUserName());
            userMap.put(email, user);
            return true;
        } finally {
            lock.unlock(); // Always release the lock in the finally block
        }
    }

    // Method to update an existing user profile
    public boolean updateUser(String profile, User user) {
        lock.lock(); // Acquire lock before modifying user data
        try {
            Optional<User> oldUser = getUser(profile); // Get the old user from userMap
            if (oldUser.isPresent()) {
                String email = UserNameBasedProfileUtil.getInstance().getUserEmail(oldUser.get().getUserName());
                userMap.put(email, user); // Update the user data
                return true;
            }
            return false;
        } finally {
            lock.unlock(); // Always release the lock in the finally block
        }
    }

    // Method to fetch a user by their profile (email or username)
    public Optional<User> getUser(String profile) {
        lock.lock(); // Acquire lock before reading from the userMap
        try {
            Optional<User> user = Optional.ofNullable(userMap.get(profile)); // Look for the user by profile
            System.out.println("checking usermap:: " + userMap);
            if (user.isEmpty()) {
                // If user is not found by profile, check by email
                String email = UserNameBasedProfileUtil.getInstance().getUserEmail(profile);
                user = Optional.ofNullable(userMap.get(email));
            }
            return user;
        } finally {
            lock.unlock(); // Always release the lock in the finally block
        }
    }

    // Method to get the posts of a user
    public List<Post> getUserPosts(String profile) {
        Optional<User> user = getUser(profile); // Fetch the user
        if (user.isEmpty()) {
            return null; // Return null if the user is not found
        }

        lock.lock(); // Acquire lock before accessing posts
        try {
            List<Post> posts = user.get().getPosts();
            return posts != null ? new ArrayList<>(posts) : new ArrayList<>(); // Return a copy of the posts
        } finally {
            lock.unlock(); // Always release the lock in the finally block
        }
    }

    // Method to get the friends of a user
    public List<User> getUserFriends(String profile) {
        Optional<User> user = getUser(profile); // Fetch the user
        if (user.isEmpty()) {
            return null; // Return null if the user is not found
        }

        lock.lock(); // Acquire lock before accessing the friends
        try {
            return new ArrayList<>(user.get().getFriends()); // Return a copy of the friends list
        } finally {
            lock.unlock(); // Always release the lock in the finally block
        }
    }

    // Method to accept a friend request between two users
    public boolean acceptFriendRequest(String fromUser, String toUser) {
        Optional<User> fromUserObj = getUser(fromUser); // Get from user
        Optional<User> toUserObj = getUser(toUser); // Get to user

        if (fromUserObj.isPresent() && toUserObj.isPresent()) {
            lock.lock(); // Acquire lock before modifying the users' friendship status
            try {
                fromUserObj.get().setFriend(toUserObj.get()); // Set each user as a friend of the other
                toUserObj.get().setFriend(fromUserObj.get());

                // Update both users in the user map
                updateUser(fromUserObj.get().getUserName(), fromUserObj.get());
                updateUser(toUserObj.get().getUserName(), toUserObj.get());

                return true;
            } finally {
                lock.unlock(); // Always release the lock in the finally block
            }
        }
        return false; // Return false if either user is not found
    }

    // Method to add a post for a user
    public Post putUpAPost(String email, Post post) {
        Optional<User> userPosting = getUser(email); // Get the user who is posting
        if (userPosting.isPresent()) {
            lock.lock(); // Acquire lock before modifying the user's post
            try {
                userPosting.get().setPost(post); // Set the post for the user
                return post; // Return the created post
            } finally {
                lock.unlock(); // Always release the lock in the finally block
            }
        }
        return null; // Return null if the user is not found
    }
}
