import java.time.LocalDate;

public class CardPaymentStrategy implements PaymentStrategy{
    private PaymentValidatorStrategy paymentValidator;
    private String cardNumber;
    private int cvv;
    private LocalDate expiryDate;

    public CardPaymentStrategy()
    {
        paymentValidator = new CardPaymentValidator();
    }
    @Override
    public void pay(long amount) throws PaymentValidationException {
        if(paymentValidator.validate(new PaymentContext(this.toString())))
        {
            //logic to process payment as validation is successful
        }
        throw new PaymentValidationException("Payment validation failed, try again later");

    }

    @Override
    public String toString() {
        return "CardPaymentStrategy{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cvv=" + cvv +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
