import java.util.HashMap;
import java.util.Map;

public class TransactionRepository {
    Map<String,Transaction> transactions;

    public TransactionRepository()
    {
        this.transactions = new HashMap<>();
    }

    public boolean addUpdateTransaction(Transaction transaction)
    {
        try {
            transactions.put(transaction.getTransactionId(), transaction);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
