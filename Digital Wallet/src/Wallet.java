import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Wallet extends PaymentMode{
    private AtomicInteger balance;

    public Wallet(String userId) {
        super(PaymentType.WALLET,userId);
        this.balance = new AtomicInteger();
    }

    @Override
    public boolean processPayment(int amount, String toUser, String fromUser, Currency currency) throws IllegalArgumentException {
        //to user
        if(userId == fromUser)
        {
            if(getBalance()<amount)
                throw new InsufficentFundException("Not sufficient balance to complete the transaction");
            balance.updateAndGet((val)-> balance.get()-val);
        }
        else{
            balance.updateAndGet((val)-> balance.get()+val);
        }

        return true;
    }

    public int updateBalance(int amount)
    {
        //update value and return total balance in wallet
        return balance.addAndGet(amount);
    }
    private int getBalance()
    {
        return balance.get();
    }
}
