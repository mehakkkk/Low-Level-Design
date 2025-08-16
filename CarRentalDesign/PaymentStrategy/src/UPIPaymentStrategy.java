public class UPIPaymentStrategy implements PaymentStrategy{
    String upiId;
    String phone;
    @Override
    public boolean pay(long amount) {
        //logic to execute payment
        return true;
    }
}
