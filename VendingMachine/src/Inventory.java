import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Inventory {

    private ConcurrentHashMap<Integer,Product> productMap;

    public Inventory() {
        this.productMap = new ConcurrentHashMap<>();
    }

    private int generateId()
    {
        //generate random id
        int id;
        do
        {
            id = ThreadLocalRandom.current().nextInt(0,productMap.size()+1);
        }while(productMap.containsKey(id));
        return id;
    }
    public Product addProduct(String name,int qty,int price) {

        int id = generateId();
        Product product = new Product(id,name,qty,price);
        this.productMap.put(product.getId(),product);
        return  product;
    }

    public Product deleteProduct(int id)
    {
        if(!productMap.containsKey(id))
            return null;
        return productMap.remove(id);
    }

    public void updateProduct(int id,int qty)
    {
        Product product = productMap.get(id);
        product.setQty(product.getQty()-qty);
        productMap.put(id,product);
    }
    public boolean isAvailable(Product product)
    {
        return this.productMap.get(product.getId()).getQty()>=product.getQty();
    }

    public List<Product> getAllProducts()
    {
        return new ArrayList<>(productMap.values());
    }

    public Product getProduct(int id)
    {
        return productMap.get(id);
    }
}
