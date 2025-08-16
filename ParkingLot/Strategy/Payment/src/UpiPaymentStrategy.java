public class UpiPaymentStrategy implements PaymentStrategy{
    private PaymentValidatorStrategy paymentValidator;
    private String upiId;
    private String phone;

    public UpiPaymentStrategy()
    {
        paymentValidator = new UpiPaymentValidator();
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
        return "UpiPaymentStrategy{" +
                "upiId='" + upiId + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
