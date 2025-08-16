public class Card extends PaymentMode {
    private String cardNumber;
    private String cardPin;

    public Card(String cardNumber, String cardPin, Bank bank) {
        super(bank);
        this.cardNumber = cardNumber;
        this.cardPin = cardPin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardPin() {
        return cardPin;
    }

    @Override
    public String getIdentifier() {
        return cardNumber;
    }
}
