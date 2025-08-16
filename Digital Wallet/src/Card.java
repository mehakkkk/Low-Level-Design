import java.util.Date;

public class Card extends PaymentMode{

    String number;
    int cvv;
    Date expiryDate;

    public Card(String number, int cvv, Date expiryDate,String userId) {
        super(PaymentType.CARD,userId);
        this.number = number;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean processPayment(int amount, String toUser, String fromUser, Currency currency) {
        //logic for payment gateway and process payment
        return true;
    }
}
