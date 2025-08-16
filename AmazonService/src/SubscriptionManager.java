import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SubscriptionManager {
    private Map<Product,List<User>> subscribeUser;

    public SubscriptionManager()
    {
        subscribeUser = new ConcurrentHashMap<>();
    }

    public void subscribe(User user, Product product)
    {
        //if key present
        subscribeUser.computeIfPresent(product,(p1,userList)->{
            userList.add(user);
            return userList;
        });
        //if not present
        subscribeUser.computeIfAbsent(product,(p1)->new CopyOnWriteArrayList<>()).add(user);
        user.subscribe(product);
    }

    public void unsubscribe(User user, Product product)
    {
        subscribeUser.computeIfPresent(product,(p1,userList)->{
            userList.remove(user);
            return userList;
        });
        user.unsubscribe(product);
    }

    public void notifyAvailability(Product product)
    {
        subscribeUser.get(product).forEach(user->user.notify(product));
    }

}
