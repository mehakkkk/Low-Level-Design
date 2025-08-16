package service;

import Utility.Util;
import model.Task;
import repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TaskService {
    private TaskRepository taskRepository;
    private final ReadWriteLock taskLock = new ReentrantReadWriteLock();

    public TaskService()
    {
        taskRepository = TaskRepository.getInstance();
    }

    public boolean createTask(Task task)
    {
        if (task == null || task.getName() == null || taskRepository.getTaskById(task.getId()).isPresent())
            return false;
        return taskRepository.addTask(task);

    }

    //optimistic concurrency method using versioning
        public boolean updateTask(Task task)
        {

            if (task == null || task.getId() == null)
                return false;

            Optional<Task> actualTask = taskRepository.getTaskById(task.getId());
            if (actualTask.isEmpty() || actualTask.get().getVersion() != task.getVersion()) {
                System.out.println("Version updated, current update failed!! " + Thread.currentThread().getName());
                return false;
            }
            taskLock.writeLock().lock();
            try {
                return taskRepository.updateTask(task);
            }finally {
                taskLock.writeLock().unlock();
            }
        }

    public Optional<Task> removeTask(String id, int expectedVersion) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(taskRepository.getTaskById(id)
                .filter(task -> task.getVersion() == expectedVersion)
                .map(task -> {
                    taskRepository.deleteTask(id);
                    return task;
                }).orElse(null));
    }



    public List<Task> getAllTasks()
    {
        return taskRepository.getAllTasks();
    }

}
