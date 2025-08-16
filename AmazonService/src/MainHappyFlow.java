public class MainHappyFlow {
    public static void main(String[] args) {

        AmazonService amazonService = AmazonService.getInstance();
        //add users
        User user1 = amazonService.registerUser("Mehak","9820930170","meh@gmail.com");
        User user2 = amazonService.registerUser("Gouri","9820930170","gri@gmail.com");

        //add products
        String p1 = amazonService.addProductToInventory("Pencil",15,"Nataraj Pencil",20);
        String p2 = amazonService.addProductToInventory("Eraser",20,"Nataraj Eraser",10);
        String p3 = amazonService.addProductToInventory("Sharpener",7,"Nataraj Sharpener",12);

        //add products to user1 cart
        amazonService.addProductToCart(user1,p1,3);
        amazonService.addProductToCart(user1,p3,5);
        //user1 placesorder
        Payment payment = amazonService.placeOrder(user1).get();

        //print payment
        System.out.println("User 1 order confirmed "+payment);


    }
}
