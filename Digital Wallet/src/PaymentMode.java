
public abstract class PaymentMode {

    public PaymentType paymentType;
    String userId;

    public PaymentMode(PaymentType paymentType,String userId)
    {
        this.paymentType = paymentType;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public abstract boolean processPayment(int amount, String toUser, String fromUser, Currency currency);

}
