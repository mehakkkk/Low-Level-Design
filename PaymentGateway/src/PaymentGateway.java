import java.util.List;
import java.util.stream.Collectors;

public class PaymentGateway {
    int id;
    String name;
    private ClientRepository clientRepository;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    public PaymentGateway(int id, String name) {
        this.id = id;
        this.name = name;
        clientRepository = new ClientRepository();
        transactionRepository = new TransactionRepository();
        userRepository = new UserRepository();
    }

    public void addClient(Client client) {
        clientRepository.addClient(client);
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public void addTransaction(Transaction transaction)
    {
        transactionRepository.addUpdateTransaction(transaction);
        System.out.println(userRepository.getUser(transaction.userId).getTransactionList().contains(transaction.transactionId));
        if(!userRepository.getUser(transaction.userId).getTransactionList().contains(transaction.transactionId))
                userRepository.getUser(transaction.userId).addTransaction(transaction.getTransactionId());
    }
    public void addUserPaymentMethod(String userId,PaymentMode paymentMode)
    {
        userRepository.getUser(userId).addPaymentMode(paymentMode);
    }
    public List<String> getUserTransactionList(String userId)
    {
        return userRepository.getUser(userId).getTransactionList();
    }
    public List<PaymentMode> getUserPaymentModeList(String userId)
    {
        return userRepository.getUser(userId).getPaymentModes().values().stream().collect(Collectors.toList());
    }

}
