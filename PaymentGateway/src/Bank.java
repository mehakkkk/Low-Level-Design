import java.util.concurrent.ThreadLocalRandom;

public class Bank {
    private int bankId;
    private String bankName;

    public Bank(int bankId, String bankName) {
        this.bankId = bankId;
        this.bankName = bankName;
    }

    public boolean executeTransaction(Transaction txn) {
        boolean success = ThreadLocalRandom.current().nextBoolean();
        txn.setStatus(success ? TransactionStatus.SUCCESS : TransactionStatus.FAILURE);
        return success;
    }
}
