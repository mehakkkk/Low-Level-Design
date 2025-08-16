public class UpiMerchant extends PaymentMode {
    private String upiId;
    private String upiPin;

    public UpiMerchant(String upiId, String upiPin, Bank bank) {
        super(bank);
        this.upiId = upiId;
        this.upiPin = upiPin;
    }

    public String getUpiId() {
        return upiId;
    }

    public String getUpiPin() {
        return upiPin;
    }

    @Override
    public String getIdentifier() {
        return upiId;
    }
}
