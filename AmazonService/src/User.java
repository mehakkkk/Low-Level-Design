import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User implements IObserver {
    private UUID id;
    private String name;
    private String phone;
    private String email;
    private List<Order> orders;

    public User(String name,String phone,String email)
    {
        this.name = name;
        this.phone =  phone;
        this.email = email;
        this.id = UUID.randomUUID();
        orders = new ArrayList<>();
    }


    public UUID getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public void subscribe(Object obj) {

    }

    @Override
    public void unsubscribe(Object obj) {

    }

    @Override
    public void notify(Object obj) {

    }
}
