import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    private int id;
    private String name;
    private AtomicInteger qty;
    private int price;

    public Product(int id, String name, int qty,int price) {
        this.id = id;
        this.name = name;
        this.qty = new AtomicInteger(qty);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty.get();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQty(int qty) {
        this.qty = new AtomicInteger(qty);
    }

    public boolean decrementQuantity(int qty) {
        while (true) {
            int currentQty = this.qty.get();
            if (currentQty < qty) {
                return false; // Not enough stock
            }
            if (this.qty.compareAndSet(currentQty, currentQty - qty)) {
                return true; // Successfully decremented
            }
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}


