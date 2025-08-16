import java.util.concurrent.ThreadLocalRandom;

public class PaymentGatewayUtil {
    public static String generateTransactionId() {
        // Generate a random number
        long randomNumber = ThreadLocalRandom.current().nextLong(1_000_000_000L, 9_999_999_999L);

        // Include a timestamp for uniqueness
        long timestamp = System.currentTimeMillis();

        // Combine the random number and timestamp
        return "TXN-" + timestamp + "-" + randomNumber;
    }
}
