import java.util.UUID;

public class OrderItem {
    private UUID productId;
    private int quantity;
    private int price;

    public OrderItem(UUID id, int quantity, int price) {
        this.productId = id;
        this.quantity = quantity;
        this.price = price;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
}
