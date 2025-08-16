import model.Task;
import model.TaskStatus;
import model.User;
import service.TaskService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskManager {
    private static TaskManager instance;
    private TaskService taskService;
    private UserService userService;

    // Private constructor to prevent instantiation from outside
    private TaskManager() {
        taskService = new TaskService();
        userService = new UserService();
    }

    // Get the Singleton instance of TaskManager
    public static TaskManager getInstance() {
        if (instance == null) {
            synchronized (TaskManager.class) {
                if (instance == null) {
                    instance = new TaskManager();
                }
            }
        }
        return instance;
    }

    // Delegate task creation to TaskService
    public boolean createTask(Task task) {
        return taskService.createTask(task) && userService.addTask(task.getUser(),task);
    }

    // Delegate task update to TaskService
    public boolean updateTask(Task task) {
        return taskService.updateTask(task);
    }

    // Delegate task removal to TaskService
    public boolean removeTask(String taskId,int expVersion) {
        Optional<Task> task = taskService.removeTask(taskId,expVersion);
        return task.isPresent() && userService.removeUserTask(taskId,task.get().getUser().getEmail());
    }

    // Delegate user creation to UserService
    public boolean addUser(String email, String name) {
        return userService.addUser(email, name);
    }

    // Delegate to UserService to get user details by email
    public User getUserDetails(String email) {
        return userService.getUserDetails(email);
    }

    // Delegate to UserService to remove user by email
    public User removeUser(String email) {
        return userService.removeUser(email);
    }

    // Delegate to UserService to add task to a user
    public boolean addTaskToUser(User user, Task task) {
        if(taskService.updateTask(task))
            return userService.addTask(user, task) ;

        return false;
    }
    public List<Task> getUserTaskByStatus(String userEmail, TaskStatus status)
    {
        if(userEmail==null || userEmail.length()==0||!userService.userExists(userEmail))
            return new ArrayList<>();

        return userService.getUserAllTask(userEmail).stream().filter(task->task.getTaskStatus().equals(status)).toList();

    }

    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
}
