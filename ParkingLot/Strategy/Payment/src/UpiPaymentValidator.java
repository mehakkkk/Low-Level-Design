public class UpiPaymentValidator implements PaymentValidatorStrategy{
    @Override
    public boolean validate(PaymentContext paymentContext) {
        //logic to verify the payment is genuine
        return false;
    }
}
