public class Main {
    public static void main(String[] args) {
        // Initialize PaymentGatewayService
        PaymentGatewayService paymentService = new PaymentGatewayService(new PaymentGateway(138920,"RAZORPAY"));

        // Create a Bank
        Bank bank = new Bank(101, "HDFC Bank");

        // Create a Client
        Client client = new Client("C1001", "Amazon", bank);
        paymentService.addClient(client); // Add client via PaymentGatewayService

        // Create a User
        User user = new User("John Doe", "U12345");

        // Add Payment Methods
        user.addPaymentMode(new Card("1234567890123456", "1234", bank));
        user.addPaymentMode(new NetBanking("john123", "password", bank));
        user.addPaymentMode(new UpiMerchant("john@upi", "1234",bank));

        paymentService.addUser(user); // Add user via PaymentGatewayService

        paymentService.displayAllUserTransaction(user.getUserId());
        // Process Payment
        System.out.println("\nInitiating payment...");
        Transaction paymentStatus = paymentService.makePayment(user.getUserId(), client, 5000L);


        paymentService.displayAllUserTransaction(user.getUserId());

    }
}
