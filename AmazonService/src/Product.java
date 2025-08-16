import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    private UUID id;
    private String name;
    private String desc;
    private int price;
    private AtomicInteger quantity;

    public Product()
    {

    }
    public Product(String name, String desc, int price, int quantity) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.quantity = new AtomicInteger(quantity);
        this.id = UUID.randomUUID();
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.incrementAndGet();
    }
}
