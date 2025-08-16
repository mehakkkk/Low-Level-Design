public interface IPaymentVerificationStrategy {
    boolean verify(Transaction transaction);
}
