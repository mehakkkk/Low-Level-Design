import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    Map<String,User> userList;

    public UserRepository()
    {
        userList = new HashMap<>();
    }

    public User getUser(String userId)
    {
        return userList.get(userId);
    }

    public boolean addUser(User user)
    {
        if(userList.containsKey(user.getUserId()))
            return false;
        userList.put(user.getUserId(), user);
        return true;
    }

    public boolean addPaymentMethod(String userId,PaymentMode paymentMode)
    {
        User user = userList.get(userId);
        user.addPaymentMode(paymentMode);
        return true;
    }

}
