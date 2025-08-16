package service;

import Utility.Util;
import model.Task;
import model.User;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService {
    private UserRepository userRepository;

    public UserService()
    {
        userRepository = UserRepository.getInstance();
    }

    public boolean addUser(String email,String name)
    {
        if (email == null || name == null || userRepository.getUserByEmail(email).isPresent()) {
            return false; // If the user is null or the email already exists, return false
        }
        String userId = Util.generateId();
        return userRepository.addUser(new User(userId,name,email));
    }

    public User getUserDetails(String email){
        if(userExists(email))
            return userRepository.getUserByEmail(email).get();

        return null;

    }

    public User removeUser(String email)
    {
        return userRepository
                .deleteUser(email).orElse(new User(null,null,null));

    }

    public boolean userExists(String email)
    {
        if(email==null || email.length()==0)
            return false;
        return userRepository.userExists(email);
    }

    public List<Task> getUserAllTask(String email)
    {
        if (userExists(email))
         return userRepository.getAllTaskForUser(email);
        return new ArrayList<>();
    }

    public boolean addTask(User user, Task task)
    {

        if(user == null || user.getEmail() == null || task == null || !userRepository.userExists(user.getEmail()))
            return false;
        System.out.println("User set before:: "+user);
        userRepository.addTaskAgainstUser(user,task);
        return true;
    }

    public boolean removeUserTask(String taskId,String userId)
    {
        if(taskId == null || userId == null || userRepository.getUserByEmail(userId).isEmpty())
            return false;

        return userRepository.removeTaskAgainstUser(taskId,userId);
    }


}
