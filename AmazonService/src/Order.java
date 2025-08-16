import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private UUID id;
    private List<OrderItem> orderItemList;
    private OrderStatus status;
    private int totalAmount;

    public Order()
    {
        id = UUID.randomUUID();
        orderItemList = new ArrayList<>();
        this.status = OrderStatus.IN_CART;
    }

    public void addItem(OrderItem orderItem)
    {
        orderItemList.add(orderItem);
        synchronized (this)
        {
            totalAmount += orderItem.getPrice();
        }
    }

    public void removeItem(OrderItem orderItem)
    {
        orderItemList.remove(orderItem);
        synchronized (this)
        {
            totalAmount -= orderItem.getPrice();
        }
    }

    public OrderStatus updateOrderStatus(OrderStatus status)
    {
        this.status = status;
        return status;
    }

    public List<OrderItem> getItems()
    {
        return this.orderItemList;
    }

    public int getTotalAmount() {
        return totalAmount;
    }
}
