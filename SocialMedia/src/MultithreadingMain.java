import model.*;
import repository.*;
import exception.*;
import java.util.*;
import java.util.concurrent.*;

public class MultithreadingMain {

    public static void main(String[] args) {
        // Create a service instance to manage account and user-related operations
        Service service = new Service();

        // Executor to manage threads
        ExecutorService executorService = Executors.newFixedThreadPool(5); // Thread pool for handling different tasks

        // --- Task 1: Account Creation ---
        Runnable createAccountTask1 = () -> {
            try {
                boolean isCreated = service.createAccount("user1", "user1@example.com", "password123");
                if (isCreated) {
                    System.out.println("User 1 account created successfully.");
                } else {
                    System.out.println("User 1 account creation failed (email already exists).");
                }
            } catch (Exception e) {
                System.out.println("Error creating User 1: " + e.getMessage());
            }
        };

        Runnable createAccountTask2 = () -> {
            try {
                boolean isCreated = service.createAccount("user2", "user2@example.com", "password123");
                if (isCreated) {
                    System.out.println("User 2 account created successfully.");
                } else {
                    System.out.println("User 2 account creation failed (email already exists).");
                }
            } catch (Exception e) {
                System.out.println("Error creating User 2: " + e.getMessage());
            }
        };

        // --- Task 2: User Sign-In ---
        Runnable userSignInTask1 = () -> {
            try {
                User user = service.userSignIn("user1@example.com", "password123");
                System.out.println("User 1 signed in successfully: " + user.getUserName());
            } catch (InputValidationException | UserNotFoundException e) {
                System.out.println("Error signing in User 1: " + e.getMessage());
            }
        };

        Runnable userSignInTask2 = () -> {
            try {
                User user = service.userSignIn("user2@example.com", "password123");
                System.out.println("User 2 signed in successfully: " + user.getUserName());
            } catch (InputValidationException | UserNotFoundException e) {
                System.out.println("Error signing in User 2: " + e.getMessage());
            }
        };

        // --- Task 3: Adding Posts ---
        Runnable addPostTask1 = () -> {
            try {
                List<String> postUrls = new ArrayList<>();
                postUrls.add("https://example.com/image1.jpg");
                Post post = new Post("User 1's first post", postUrls, "user1");
                service.addPost("user1@example.com", post);
                System.out.println("Post by User 1 added successfully.");
            } catch (InputValidationException e) {
                System.out.println("Error adding post for User 1: " + e.getMessage());
            }
        };

        Runnable addPostTask2 = () -> {
            try {
                List<String> postUrls = new ArrayList<>();
                postUrls.add("https://example.com/image2.jpg");
                Post post = new Post("User 2's first post", postUrls, "user2");
                service.addPost("user2@example.com", post);
                System.out.println("Post by User 2 added successfully.");
            } catch (InputValidationException e) {
                System.out.println("Error adding post for User 2: " + e.getMessage());
            }
        };

        // --- Task 4: Sending Friend Requests ---
        Runnable sendFriendRequestTask = () -> {
            try {
                boolean isAccepted = service.sendRequest("user1", "user2");
                if (isAccepted) {
                    System.out.println("User 1 and User 2 are now friends.");
                } else {
                    System.out.println("Error sending friend request from User 1 to User 2.");
                }
            } catch (UserNotFoundException e) {
                System.out.println("Error sending friend request: " + e.getMessage());
            }
        };

        // --- Task 5: Fetching User Posts ---
        Runnable fetchUserPostsTask1 = () -> {
            List<Post> posts = service.getMyPosts("user1");
            if (posts != null) {
                System.out.println("User 1's posts: ");
                posts.forEach(post -> System.out.println(post.getDesc()));
            } else {
                System.out.println("No posts found for User 1.");
            }
        };

        Runnable fetchUserPostsTask2 = () -> {
            List<Post> posts = service.getMyPosts("user2");
            if (posts != null) {
                System.out.println("User 2's posts: ");
                posts.forEach(post -> System.out.println(post.getDesc()));
            } else {
                System.out.println("No posts found for User 2.");
            }
        };

        // Submit tasks to executor
        executorService.submit(createAccountTask1);
        executorService.submit(createAccountTask2);
        executorService.submit(userSignInTask1);
        executorService.submit(userSignInTask2);
        executorService.submit(addPostTask1);
        executorService.submit(addPostTask2);
        executorService.submit(sendFriendRequestTask);
        executorService.submit(fetchUserPostsTask1);
        executorService.submit(fetchUserPostsTask2);

        // Shut down the executor service gracefully after all tasks are completed
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        System.out.println("All tasks completed.");
    }
}
