package repository;

import model.Task;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TaskRepository {
    //concurrent hashmap is chosen if modification in any of the task
    //should not lock up the other modification
    private ConcurrentHashMap<String, Task> taskMap = new ConcurrentHashMap<>();
    private static TaskRepository instance;


    private TaskRepository()
    {

    }

    public static TaskRepository getInstance(){
        if(instance == null)
        {
            synchronized (TaskRepository.class)
            {
                if (instance == null)
                    instance = new TaskRepository();
            }
        }
        return instance;
    }

    public Boolean addTask(Task task) {
        return taskMap.putIfAbsent(task.getId(), task) == null;
    }


    // Get a task by its ID
    public Optional<Task> getTaskById(String taskId) {
        return Optional.ofNullable(taskMap.get(taskId)); // Returns null if task is not found
    }

    // Delete a task by its ID
    public Boolean deleteTask(String taskId) {
        taskMap.remove(taskId); // Removes the task by its ID
        return true;
    }

    //optimistic lockinng update

    public boolean updateTask(Task task) {
        return taskMap.computeIfPresent(task.getId(), (id, existingTask) -> {
            if (existingTask.getVersion() != task.getVersion()) {
                System.out.println("Version mismatch! Update failed.");
                return existingTask; // Return old value, no update.
            }
            task.updateVersion(); // Increment version safely.
            return task;
        }) == task;

    }


    public List<Task> getAllTasks() {
       return taskMap.values().stream().toList();
    }
}
