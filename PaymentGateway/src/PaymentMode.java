public abstract class PaymentMode {
    private Bank bank;

    public PaymentMode(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    public abstract String getIdentifier(); // Abstract method for unique identifier
}
