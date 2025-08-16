import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DigitalWallet {
    User user;
    List<PaymentMode> paymentModeList;
    Map<String,Transaction> transactions;
    Currency currency; //users default currency
    Wallet wallet;

    public DigitalWallet(User user,Currency currency)
    {
        this.user = user;
        this.currency = currency;
        this.paymentModeList = new ArrayList<>();
        this.transactions = new ConcurrentHashMap<>();
        this.wallet = new Wallet(user.getId().toString());
    }

    public boolean updatePaymentMode(PaymentMode paymentMode)
    {
        return paymentModeList.add(paymentMode);
    }

    public boolean updateTransaction(Transaction transaction)
    {
        transactions.put(String.valueOf(transaction.getId()),transaction);
        return true;
    }

    public User getUser() {
        return user;
    }

    public List<PaymentMode> getPaymentModeList() {
        return paymentModeList;
    }

    public Transaction getTransaction(String id)
    {
        return transactions.get(id);
    }
    public List<Transaction> getTransactions() {
        return transactions.values().stream().collect(Collectors.toList());
    }

    public Currency getCurrency() {
        return currency;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
