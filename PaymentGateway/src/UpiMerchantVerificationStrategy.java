public class UpiMerchantVerificationStrategy implements IPaymentVerificationStrategy {
    @Override
    public boolean verify(Transaction transaction) {
        UpiMerchant upi = (UpiMerchant) transaction.getPaymentMode();
        // Validate UPI ID and PIN
        return upi.getUpiId() != null && upi.getUpiId().matches("[a-zA-Z0-9._-]+@[a-zA-Z]+") &&
                upi.getUpiPin() != null && upi.getUpiPin().length() == 4;
    }
}
