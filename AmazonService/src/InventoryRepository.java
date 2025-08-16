import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryRepository {
    private Map<UUID,Product> productMap;
    private static InventoryRepository instance;
    private InventoryRepository()
    {
        productMap = new ConcurrentHashMap<>();
    }

    public static InventoryRepository getInstance()
    {
        if(instance == null)
        {
            synchronized (InventoryRepository.class)
            {
                if(instance == null)
                    instance = new InventoryRepository();
            }
        }
        return instance;
    }

    public boolean addProduct(Product product)
    {
        productMap.put(product.getId(),product);
        return true;
    }

    public boolean removeProduct(UUID id)
    {
        productMap.remove(id);
        return true;
    }

//    public void updateQuantity(UUID id,int quantity)
//    {
//        productMap.computeIfPresent(id,(productId,product)->{
//            product.setQuantity(quantity);
//            return product;
//        });
//    }

    public Product getProduct(UUID id)
    {
        return productMap.getOrDefault(id,new Product());
    }

    public List<Product> getProductList()
    {
        return new ArrayList<Product>(productMap.values().stream().toList());
    }

}
