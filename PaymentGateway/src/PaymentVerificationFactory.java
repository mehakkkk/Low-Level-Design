public class PaymentVerificationFactory {
    public static IPaymentVerificationStrategy getVerificationStrategy(PaymentMode mode) {
        if (mode instanceof Card) {
            return new CardVerificationStrategy();
        } else if (mode instanceof NetBanking) {
            return new NetBankingVerificationStrategy();
        } else if (mode instanceof UpiMerchant) {
            return new UpiMerchantVerificationStrategy();
        } else {
            throw new IllegalArgumentException("Unsupported payment mode");
        }
    }
}
