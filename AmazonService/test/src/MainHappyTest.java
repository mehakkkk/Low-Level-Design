import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MainHappyTest {

    private AmazonService amazonService = AmazonService.getInstance();
    List<String> productId;
    public MainHappyTest()
    {
        productId = new ArrayList<>();
        String p1 = amazonService.addProductToInventory("Pencil",15,"Nataraj Pencil",20);
        String p2 = amazonService.addProductToInventory("Eraser",20,"Nataraj Eraser",10);
        String p3 = amazonService.addProductToInventory("Sharpener",7,"Nataraj Sharpener",12);

        productId.add(p1);
        productId.add(p2);
        productId.add(p3);
    }
    @Test
    public void testHappyFlow()
    {
        //add users
        User user1 = amazonService.registerUser("Mehak","9820930170","meh@gmail.com");
        //add products
        //add products to user1 cart
        amazonService.addProductToCart(user1,productId.get(0),3);
        amazonService.addProductToCart(user1,productId.get(2),5);
        //user1 placesorder
        Payment payment = amazonService.placeOrder(user1).get();

        Assert.assertEquals(80,payment.toalAmount);

    }

    @Test(expected = ProductNotAvailable.class)
    public void testHappyFlow1()
    {
        //add users
        User user1 = amazonService.registerUser("Gouri","9820930170","hgh@gmail.com");
        //add products
        //add products to user1 cart
        amazonService.addProductToCart(user1,productId.get(0),30);
        amazonService.addProductToCart(user1,productId.get(2),5);
        //user1 placesorder
        Payment payment = amazonService.placeOrder(user1).get();

        Assert.assertEquals(80,payment.toalAmount);

    }


}
