public class CardVerificationStrategy implements IPaymentVerificationStrategy {
    @Override
    public boolean verify(Transaction transaction) {
        Card card = (Card) transaction.getPaymentMode();
        // Simple validation: PIN length check (example)
        return card.getCardPin() != null && card.getCardPin().length() == 4;
    }
}
