package utility;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UserNameBasedProfileUtil {

    private static UserNameBasedProfileUtil instance;
    private final ConcurrentHashMap<String, String> userNameEmailMap;
    private final ReadWriteLock lock;

    private UserNameBasedProfileUtil(){
        userNameEmailMap = new ConcurrentHashMap<>();
        lock = new ReentrantReadWriteLock();
    }

    public static UserNameBasedProfileUtil getInstance() {
        if(instance == null) {
            synchronized (UserNameBasedProfileUtil.class) {
                if (instance == null) {
                    instance = new UserNameBasedProfileUtil();
                }
            }
        }
        return instance;
    }

    // Get the email for a given username. Read lock ensures thread-safe access.
    public String getUserEmail(String userName) {
        System.out.println("line 31:: "+userNameEmailMap);
            return userNameEmailMap.get(userName);
    }

    // Update the email map with a new username and email. Write lock ensures thread-safe update.
    public boolean updateUserEmailMap(String userName, String email) {
        lock.writeLock().lock();  // acquire write lock
        try {

            System.out.println("Updating map: " + userName + " -> " + email);
            userNameEmailMap.put(userName, email);
            System.out.println("updated in mp:: "+userNameEmailMap);
            return true;
        } finally {
            lock.writeLock().unlock();  // release write lock
        }
    }
}
