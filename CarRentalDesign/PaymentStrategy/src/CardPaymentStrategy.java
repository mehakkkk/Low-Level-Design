import java.time.LocalDateTime;

public class CardPaymentStrategy implements PaymentStrategy{
    String cardNum;
    int cvv;
    LocalDateTime expiryDate;

    @Override
    public boolean pay(long amount) {
        //logic to execute payment
        return true;
    }
}
