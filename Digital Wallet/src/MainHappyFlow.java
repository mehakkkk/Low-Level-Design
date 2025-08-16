import java.sql.Date;

public class MainHappyFlow {
    public static void main(String[] args) throws UserNotFoundException, CloneNotSupportedException {
        DigitalWalletService digitalWalletService = new DigitalWalletService();

        String userId1 = digitalWalletService.createUser("mehakgupta160@gmail.com","123456", Date.valueOf("2000-03-20"),Currency.INR);
        String userId2 = digitalWalletService.createUser("gga160@gmail.com","123456", Date.valueOf("2000-03-21"),Currency.INR);

        digitalWalletService.addUserPaymentMode(userId1, new Card("092-293-029",234,Date.valueOf("2029-04-20"),userId1));
        int totalBalance = digitalWalletService.updateWalletBalance(userId1,7000);
        System.out.println("total balance for user "+userId1+" "+totalBalance);
        System.out.println("user payment using wallet ");
        TransactionStatus paymentStatus = digitalWalletService.processPayment(userId2,userId1,500);
        System.out.println("Transaction status for "+userId1+" to "+userId2+" "+paymentStatus);

        System.out.println("Transactions done by "+userId1);
        System.out.println(digitalWalletService.getUserTransactionHistory(userId1));
        System.out.println("Transactions done by "+userId2);
        System.out.println(digitalWalletService.getUserTransactionHistory(userId2));

    }
}
