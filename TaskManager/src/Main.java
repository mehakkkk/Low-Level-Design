import model.Task;
import model.TaskBuilder;
import model.TaskStatus;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        // Create an ExecutorService with a fixed thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(7);

        TaskManager taskManager = TaskManager.getInstance();

        // Create users
        taskManager.addUser("meh123@gmail.com", "mehak");
        taskManager.addUser("gouri19@gmail.com", "gouri");

        // Create tasks
        Task task1 = new TaskBuilder().setName("One-Msg integration").build();
        Task task2 = new TaskBuilder().setName("Raid ETL").build();
        Task task3 = new TaskBuilder().setName("CNF images").build();

        // Create tasks via TaskManager
        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createTask(task3);

        // Create a list of Futures to monitor task completion
        List<Future<?>> futures = new ArrayList<>();

        // Submit thread 1: Assign task1 to user1 (Mehak)
        futures.add(executorService.submit(() -> {
            User user1 = taskManager.getUserDetails("meh123@gmail.com");
            System.out.println("user1:: "+user1);
            Task editTask = task1.clone();
            editTask.setUser(user1);
            taskManager.addTaskToUser(user1, editTask);
            System.out.println("Thread 1: Task 1 assigned to Mehak");
        }));

        // Submit thread 2: Update task1's description
        futures.add(executorService.submit(() -> {
            Task editTask = task1.clone();
            editTask.setDesc("Updated description for One-Msg integration");
            taskManager.updateTask(editTask);
            System.out.println("Thread 2: Task 1 description updated");
        }));

        // Submit thread 3: Update task2 to user2 (Gouri)
        futures.add(executorService.submit(() -> {
            User user2 = taskManager.getUserDetails("gouri19@gmail.com");
            taskManager.addTaskToUser(user2, task2);
            System.out.println("Thread 3: Task 2 assigned to Gouri");
        }));


        // Submit thread 4: Display all tasks of user1 (Mehak)
        futures.add(executorService.submit(() -> {
            System.out.println("Thread 4: Tasks assigned to Mehak: " +
                    taskManager.getUserTaskByStatus("meh123@gmail.com", TaskStatus.OPEN));
        }));


        // Submit thread 5: Update task2 to completed
        futures.add(executorService.submit(() -> {
            task2.setTaskStatus(TaskStatus.COMPLETED);
            taskManager.updateTask(task2);
            System.out.println("Thread 5: Task 2 updated to COMPLETED");
        }));

        // Submit thread 6: Delete task1
        futures.add(executorService.submit(() -> {

            System.out.println("Thread 6: Trying to delete Task 1");
            taskManager.removeTask(task1.getId(),task1.getVersion());
            System.out.println("Thread 6: completed");
        }));

        // Submit thread 7: Remove user2 (Gouri)
        futures.add(executorService.submit(() -> {
            taskManager.removeUser("gouri19@gmail.com");
            System.out.println("Thread 7: User Gouri removed");
        }));

        //thread 8
        futures.add(executorService.submit(() -> {
            Task editTask = task2.clone();
            editTask.setDesc("Updated description for ETL");
            taskManager.updateTask(editTask);
            System.out.println("Thread 8: Task 2 description updated");
        }));
        // Gracefully shut down the ExecutorService after all tasks have been submitted
        executorService.shutdown();

        // Wait for all tasks to complete (we don't use join, but monitor with Future)
        try {
            // Await the completion of all tasks
            if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("Timeout reached before all threads finished!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // After all tasks are completed, display all tasks in the system
        System.out.println("All tasks after all threads completed:");
        taskManager.getAllTasks().forEach(System.out::println);
    }
}
