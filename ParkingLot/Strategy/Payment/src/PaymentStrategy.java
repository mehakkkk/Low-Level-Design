public interface PaymentStrategy {
    public void pay(long amount) throws PaymentValidationException;
}
