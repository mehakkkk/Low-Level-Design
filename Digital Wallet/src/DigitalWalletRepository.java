import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class DigitalWalletRepository {

    private Map<String,DigitalWallet> userDigitalWallet;
    private static DigitalWalletRepository instance;

    private DigitalWalletRepository(){
        userDigitalWallet = new ConcurrentHashMap<>();
    }

    public static DigitalWalletRepository getInstance()
    {
        if(instance == null)
        {
            synchronized (DigitalWalletRepository.class)
            {
                if(instance == null)
                {
                    instance = new DigitalWalletRepository();
                }
            }
        }
        return instance;
    }

    public DigitalWallet getDigitalWallet(String userId)
    {
        return userDigitalWallet.get(userId);
    }

    public String addUser(DigitalWallet digitalWallet)
    {
        userDigitalWallet.put(digitalWallet.getUser().getId(),digitalWallet);
        System.out.println(userDigitalWallet);
        return digitalWallet.getUser().getId();
    }

    public boolean addUserPaymentMethod(String userId,PaymentMode paymentMode)
    {
        return userDigitalWallet.computeIfPresent(userId,(id,digitalWallet)->{
            digitalWallet.updatePaymentMode(paymentMode);
            return digitalWallet;
        })!=null;
    }

    public List<PaymentMode> getUserPaymentModes(String userId)
    {
        List<PaymentMode> paymentModeList = userDigitalWallet.get(userId).getPaymentModeList();
        return new ArrayList<>(paymentModeList); // return copy of list and not actual list for safety
    }


    public TransactionStatus processPayment(int amount, String fromUser, String toUser, PaymentMode paymentMode, Currency inputCurrency) throws CloneNotSupportedException, InsufficentFundException {
        // Fetch the user's Digital Wallets
        DigitalWallet fromWallet = userDigitalWallet.get(fromUser);
        DigitalWallet toWallet = userDigitalWallet.get(toUser);

        // If either wallet is null, return PENDING or fail the transaction (customize this behavior as needed)
        if (fromWallet == null || toWallet == null) {
            return TransactionStatus.PENDING; // Or other status like FAILED
        }

        // User's default currency
        Currency userCurrency = fromWallet.getCurrency();

        // Convert the amount from the input currency to the user's default currency
        double convertedAmount = inputCurrency.getConversionValue(userCurrency) * amount;

        // Create a transaction with IN_PROCESS status
        Transaction transaction = new Transaction(toUser, fromUser, paymentMode.paymentType, (int) convertedAmount, inputCurrency);
        transaction.updateTransactionStatus(transaction.getId(), TransactionStatus.IN_PROCESS); // Set status to IN_PROCESS initially

        // Locks to ensure atomic updates to both wallets (no deadlocks)
        ReentrantLock fromWalletLock = new ReentrantLock();
        ReentrantLock toWalletLock = new ReentrantLock();

        // Lock the wallets to ensure thread-safety while updating transaction history
        fromWalletLock.lock();
        try {
            Transaction transaction1 = (Transaction) transaction.clone();
            transaction1.setTransferType(TransferType.CREDIT);
            fromWallet.updateTransaction(transaction1); // Add transaction to fromWallet history
        } finally {
            fromWalletLock.unlock(); // Always unlock in a finally block
        }

        toWalletLock.lock();
        try {
            Transaction transaction1 = (Transaction) transaction.clone();
            transaction1.setTransferType(TransferType.DEBIT);
            toWallet.updateTransaction(transaction1); // Add transaction to toWallet history
        } finally {
            toWalletLock.unlock(); // Always unlock in a finally block
        }

        // Delegate the payment processing to the PaymentMode (Balance check happens inside PaymentMode)
        boolean paymentCredit = paymentMode.processPayment((int) convertedAmount, toUser, fromUser, userCurrency);
        boolean paymentDebit = true;
        if(paymentMode.paymentType == PaymentType.WALLET)
        {
            paymentMode = userDigitalWallet.get(toUser).getWallet();
            paymentDebit = paymentMode.processPayment((int) convertedAmount, toUser, fromUser, userCurrency);
        }

        // Update transaction status after payment
        if (paymentCredit && paymentDebit) {
            transaction.updateTransactionStatus(transaction.getId(), TransactionStatus.COMPLETED);

        } else {
            transaction.updateTransactionStatus(transaction.getId(), TransactionStatus.PENDING); // Or FAILED
        }

        fromWalletLock.lock();
        try{

           fromWallet.getTransaction(transaction.getId()).updateTransactionStatus(transaction.getId(), transaction.getStatus());
        }
        finally {
            fromWalletLock.unlock();
        }

        toWalletLock.lock();
        try{

            toWallet.getTransaction(transaction.getId()).updateTransactionStatus(transaction.getId(), transaction.getStatus());
        }
        finally {
            toWalletLock.unlock();
        }
        // Return the final status of the transaction
        return transaction.getStatus();
    }



    public List<Transaction> getUserTransactionHistory(String userId)
    {
        List<Transaction> transactions = userDigitalWallet.get(userId).getTransactions();
        return new ArrayList<>(transactions); // return copy of list and not actual list for safety
    }

    public boolean userExists(String userId)
    {
        return userDigitalWallet.containsKey(userId);
    }
}
