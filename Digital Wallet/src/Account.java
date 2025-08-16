public class Account extends PaymentMode{
    String number;
    public Account(String number,String userId)
    {
        super(PaymentType.ACCOUNT,userId);
        this.number = number;
    }
    @Override
    public boolean processPayment(int amount, String toUser, String fromUser, Currency currency) {
        //logic to transfer movey from toUser bank account
        return true;
    }
}
