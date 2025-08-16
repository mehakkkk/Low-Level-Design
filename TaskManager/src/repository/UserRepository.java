package repository;

import model.Task;
import model.User;

import java.util.*;

public class UserRepository {
    private static UserRepository instance;
    private Map<User, List<Task>> userMap;

    private UserRepository() {
        userMap = new HashMap<>();
    }

    public static UserRepository getInstance(){
        if(instance == null)
        {
            synchronized (UserRepository.class)
            {
                if (instance == null)
                    instance = new UserRepository();
            }
        }
        return instance;
    }

    // Adds a new user to the repository
    public boolean addUser(User user) {

        // Add the user to the map with email as the key
        userMap.put(user,new ArrayList<>());
        System.out.println("keyset::"+userMap.keySet());
        return true; // Successfully added the user
    }

    // Method to retrieve a user by email
    public Optional<User> getUserByEmail(String email) {
        return userMap.keySet().stream().filter(u->u.getEmail().equals(email)).findFirst(); // This automatically handles null cases
    }

    public boolean addTaskAgainstUser(User user, Task task)
    {
        userMap.get(user).add(task);
        return true;
    }

    public List<Task> getAllTaskForUser(String email)
    {
        User user = getUserByEmail(email).get();
        return userMap.get(user);
    }

    public boolean userExists(String email)
    {
        return getUserByEmail(email).isPresent();
    }

    // Deletes a user based on their email
    public Optional<User> deleteUser(String email) {
        Optional<User> userOpt = this.getUserByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            //nullify all tasks
            System.out.println("user:: "+user+" user task:: "+userMap.get(user));
            userMap.get(user).stream().forEach(task->task.setUser(null));
            this.userMap.remove(user);
        }

        return userOpt;
    }

    public boolean removeTaskAgainstUser(String taskId,String email)
    {
        List<Task> tasks = getAllTaskForUser(email);
        Optional<Task> taskToRemove = tasks.stream()
                .filter(task -> task.getId().equals(taskId)) // Find task by taskId
                .findFirst();

        // If the task is found, remove it
        if (taskToRemove.isPresent()) {
            tasks.remove(taskToRemove.get());
            return true;
        }

        return false;
    }
}
