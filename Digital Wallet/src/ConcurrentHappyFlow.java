import java.sql.Date;
import java.util.concurrent.*;

public class ConcurrentHappyFlow {

    public static void main(String[] args) throws UserNotFoundException, CloneNotSupportedException {
        // Create an instance of DigitalWalletService
        DigitalWalletService digitalWalletService = new DigitalWalletService();

        // Creating two users
        String userId1 = digitalWalletService.createUser("mehakgupta160@gmail.com", "123456", Date.valueOf("2000-03-20"), Currency.INR);
        String userId2 = digitalWalletService.createUser("gga160@gmail.com", "123456", Date.valueOf("2000-03-21"), Currency.INR);

        // Add payment method (Card) to user 1
        digitalWalletService.addUserPaymentMode(userId1, new Card("092-293-029", 234, Date.valueOf("2029-04-20"), userId1));
        System.out.println("payment modes for userid1 "+digitalWalletService.getUserAllPaymentModes(userId1));

        // Update wallet balance for user 1
        int totalBalance = digitalWalletService.updateWalletBalance(userId1, 7000);
        System.out.println("Total balance for user " + userId1 + ": " + totalBalance);

        // Set up ExecutorService to manage concurrent tasks
        ExecutorService executorService = Executors.newFixedThreadPool(5);  // You can adjust the thread pool size

        // Use ExecutorService to run payment processing concurrently
        Callable<TransactionStatus> paymentTask1 = () -> {
            System.out.println("Processing payment from " + userId1 + " to " + userId2);
            return digitalWalletService.processPayment(userId2, userId1, 500);
        };

        Callable<TransactionStatus> paymentTask2 = () -> {
            System.out.println("Processing another payment from " + userId1 + " to " + userId2);
            return digitalWalletService.processPayment(userId2, userId1,300,digitalWalletService.getUserAllPaymentModes(userId1).get(0),Currency.USD);
        };

        // Submit tasks to executor service
        Future<TransactionStatus> futurePayment1 = executorService.submit(paymentTask1);
        Future<TransactionStatus> futurePayment2 = executorService.submit(paymentTask2);

        // Wait for the results (payments)
        try {
            TransactionStatus paymentStatus1 = futurePayment1.get();  // This blocks until the payment completes
            System.out.println("Transaction status for " + userId1 + " to " + userId2 + ": " + paymentStatus1);

            TransactionStatus paymentStatus2 = futurePayment2.get();  // This blocks until the payment completes
            System.out.println("Transaction status for " + userId1 + " to " + userId2 + ": " + paymentStatus2);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Get the transaction history for both users
        System.out.println("Transactions done by " + userId1 + ":");
        System.out.println(digitalWalletService.getUserTransactionHistory(userId1));

        System.out.println("Transactions done by " + userId2 + ":");
        System.out.println(digitalWalletService.getUserTransactionHistory(userId2));

        // Shut down the executor service to clean up resources
        executorService.shutdown();
    }
}
