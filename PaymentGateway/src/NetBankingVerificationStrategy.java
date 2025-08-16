public class NetBankingVerificationStrategy implements IPaymentVerificationStrategy {
    @Override
    public boolean verify(Transaction transaction) {
        NetBanking netBanking = (NetBanking) transaction.getPaymentMode();
        // Simple validation: User credentials check
        return netBanking.getUserId() != null && !netBanking.getUserId().isEmpty() &&
                netBanking.getUserPass() != null && !netBanking.getUserPass().isEmpty();
    }
}
