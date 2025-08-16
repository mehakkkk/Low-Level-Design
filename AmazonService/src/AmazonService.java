import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AmazonService {
    private Map<UUID,User> userMap;
    private Map<UUID,Order> userOrderMap;
    private InventoryRepository inventoryRepository;
    private SubscriptionManager subscriptionManager;
    private static AmazonService instance;

    private AmazonService()
    {
        userMap = new ConcurrentHashMap<>();
        userOrderMap = new ConcurrentHashMap<>();
        subscriptionManager = new SubscriptionManager();
        inventoryRepository = InventoryRepository.getInstance();
    }

    public static AmazonService getInstance()
    {
        if(instance == null)
        {
            synchronized (AmazonService.class)
            {
                if(instance == null)
                    instance = new AmazonService();
            }
        }
        return instance;
    }

    public User registerUser(String name,String phone,String email)
    {
        if(name == null || name.isEmpty())
            throw new InvalidUserData("User name cannot be empty");
        if(email == null || email.isEmpty())
            throw new InvalidUserData("User email cannot be empty");
        if(phone == null || phone.isEmpty())
            throw new InvalidUserData("User phone cannot be empty");
        User user = new User(name,phone,email);
        userMap.put(user.getId(),user);
        userOrderMap.put(user.getId(),new Order());
        return user;
    }

    public String addProductToInventory(String name,int price,String desc,int quantity)
    {
        if(name == null || name.isEmpty())
            throw new InvalidProductData("Product price cannot be empty");
        if(price <= 0)
            throw new InvalidProductData("Price needs to be greater than 0");
        if(quantity<=0)
            throw new InvalidProductData("quantity needs to be greater than 0");
        Product product = new Product(name,desc,price,quantity);
        boolean isAdded = inventoryRepository.addProduct(product);
        if(!isAdded)
            return null;
        return product.getId().toString();
    }

    public boolean removeProductFromInventory(String id)
    {
        if(id == null || id.isEmpty())
            throw new InvalidProductData("Product id required for deletion");
        return inventoryRepository.removeProduct(UUID.fromString(id));
    }

    public boolean addProductToCart(User user,String productId,int quantity)
    {
        //check whether product is in stock
        Product product = inventoryRepository.getProduct(UUID.fromString(productId));
        if(product.getId() == null || product.getQuantity()<quantity)
            throw new ProductNotAvailable("Product not available");

        synchronized (user.getId()) {
            Order order = userOrderMap.get(user.getId());
            order.addItem(new OrderItem(UUID.fromString(productId), quantity, quantity * product.getPrice()));
            order.updateOrderStatus(OrderStatus.IN_CART);
        }
        return true;
    }

    public void subscriberToProduct(User user,String productId)
    {
        Product product = inventoryRepository.getProduct(UUID.fromString(productId));
        subscriptionManager.subscribe(user,product);
    }

    public boolean updateProductQuantity(String productId,int quantity)
    {
        Product product = inventoryRepository.getProduct(UUID.fromString(productId));
        if(product == null)
            throw new ProductNotAvailable("Product not found");
        product.setQuantity(quantity);
        subscriptionManager.notifyAvailability(product);
        return true;
    }

    public Optional<Payment> placeOrder(User user) {
        Payment payment = null;

        // Synchronize on the user ID to ensure exclusive access to the user's order
        synchronized (user.getId()) {
            // Now synchronize on the Order object to prevent concurrent modifications to the order's item list
            Order order = userOrderMap.get(user.getId());

            synchronized (order) { // Locking the order to ensure exclusive access to its items
                List<OrderItem> orderItemList = order.getItems();
                if (orderItemList.size() == 0) {
                    throw new CartException("Cart is empty");
                }

                // Now before checkout, remove products not in stock
                for (OrderItem orderItem : orderItemList) {
                    Product product = inventoryRepository.getProduct(orderItem.getProductId());
                    if (product == null || product.getQuantity() < orderItem.getQuantity()) {
                        System.out.println("Removing product " + product.getName() + " from user " + user.getName() + "'s cart");
                        order.removeItem(orderItem);  // Modify the order item list safely within the synchronized block
                    } else {
                        product.setQuantity(-1 * orderItem.getQuantity()); // Decrease product quantity safely
                    }
                }
                payment = new Payment(user.getId().toString(), order.getTotalAmount());
            }
        }

        return Optional.ofNullable(payment);
    }



}
