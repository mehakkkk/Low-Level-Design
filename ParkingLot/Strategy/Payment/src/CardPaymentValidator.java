public class CardPaymentValidator implements PaymentValidatorStrategy{
    @Override
    public boolean validate(PaymentContext paymentContext) {
        return false;
    }
}
