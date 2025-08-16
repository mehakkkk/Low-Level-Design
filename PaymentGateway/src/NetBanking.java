public class NetBanking extends PaymentMode {
    private String userId;
    private String userPass;

    public NetBanking(String userId, String userPass, Bank bank) {
        super(bank);
        this.userId = userId;
        this.userPass = userPass;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPass() {
        return userPass;
    }

    @Override
    public String getIdentifier() {
        return userId;
    }
}
