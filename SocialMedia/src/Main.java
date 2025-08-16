import model.Post;
import model.User;
import exception.InputValidationException;
import exception.UserNotFoundException;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Create the Service object
        Service service = new Service();

        // Step 1: Create Accounts Sequentially
        System.out.println("Creating accounts...");
        service.createAccount("user1", "user1@example.com", "password123");
        service.createAccount("user2", "user2@example.com", "password456");
        service.createAccount("user3", "user3@example.com", "password789");
        System.out.println("All accounts created.\n");

        // Sign in
        try {
            System.out.println("user1" + " signing in...");
            User user = service.userSignIn("user1@example.com", "password123"); // Using email for sign-in
            System.out.println("user1" + " signed in as: " + user.getUserName());

            System.out.println("user2" + " signing in...");
            user = service.userSignIn("user2@example.com", "password456"); // Using email for sign-in
            System.out.println("user2" + " signed in as: " + user.getUserName());

            System.out.println("user3" + " signing in...");
            user = service.userSignIn("user3@example.com", "password789"); // Using email for sign-in
            System.out.println("user3" + " signed in as: " + user.getUserName());
        }
        catch(Exception exception)
        {

        }

        // Step 2: Perform User Operations Sequentially
        performUserOperations(service, "user1", "user1@example.com", "password123");
        performUserOperations(service, "user2", "user2@example.com", "password456");
        performUserOperations(service, "user3", "user3@example.com", "password789");

        System.out.println("\nAll user operations completed.");
    }

    private static void performUserOperations(Service service, String userName, String email, String password) {
        try {

            // Get user posts
            System.out.println(userName + " fetching posts...");
            List<Post> posts = service.getMyPosts(email); // Fetch posts for the signed-in user
            if (posts != null && !posts.isEmpty()) {
                System.out.println(userName + "'s posts:");
                posts.forEach(post -> System.out.println(post.getPostId() + ": " + post.getUrl()));
            } else {
                System.out.println(userName + " has no posts.");
            }

            // Sending friend request (simulating with other users)
            String toUser = "user2"; // Simulating sending request to user2
            if (!userName.equals(toUser)) {
                System.out.println(userName + " sending friend request to " + toUser);
                boolean friendRequestSent = service.sendRequest(userName, toUser);
                System.out.println(userName + " friend request sent to " + toUser + ": " + friendRequestSent);
            }

            // Adding a post
            Post post = new Post(userName, List.of("This is a test post."), "http://someurl.com");
            System.out.println(userName + " adding a post...");
            Post addedPost = service.addPost(email, post); // Using email to add post
            System.out.println(userName + " added post with ID: " + addedPost.getPostId());

        } catch (InputValidationException | UserNotFoundException e) {
            System.err.println(userName + " encountered an error: " + e.getMessage());
        }
    }
}
