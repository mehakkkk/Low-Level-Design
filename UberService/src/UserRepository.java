import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {

    private Map<UUID,User> userMap;
    private static UserRepository instance;

    private UserRepository()
    {
        userMap = new ConcurrentHashMap<>();
    }

    public static UserRepository getInstance()
    {
        if(instance == null)
        {
            synchronized (UserRepository.class)
            {
                if(instance == null)
                {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public boolean addUser(User user)
    {
        userMap.put(user.getId(),user);
        return true;
    }

    public boolean deleteUser(String userId)
    {
        userMap.remove(UUID.fromString(userId));
        return true;
    }

    public boolean userExists(String userId)
    {
        return userMap.containsKey(UUID.fromString(userId));
    }

    public User getUser(String userId)
    {
        return userMap.get(UUID.fromString(userId));
    }

    public boolean updateUser(User user)
    {
        return addUser(user);
    }

}
