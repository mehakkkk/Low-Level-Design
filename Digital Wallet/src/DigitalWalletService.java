import java.util.Date;
import java.util.List;

public class DigitalWalletService {
    private DigitalWalletRepository digitalWalletRepository;

    public DigitalWalletService()
    {
        digitalWalletRepository = DigitalWalletRepository.getInstance();
    }

    public String createUser(String email, String password, Date dob, Currency currency){
        return digitalWalletRepository.addUser(new DigitalWallet(new User(dob,email,password),currency));
    }

    public boolean addUserPaymentMode(String userId,PaymentMode paymentMode){
        return digitalWalletRepository.addUserPaymentMethod(userId,paymentMode);
    }

    public List<PaymentMode> getUserAllPaymentModes(String userId) throws UserNotFoundException {
        if(!digitalWalletRepository.userExists(userId))
            throw new UserNotFoundException("User Not found for id "+userId);
        return digitalWalletRepository.getUserPaymentModes(userId);
    }

    public TransactionStatus processPayment(String toUser,String fromUser,int amount,PaymentMode paymentMode,Currency currency) throws CloneNotSupportedException {
        System.out.println("payment type:: "+paymentMode.paymentType);
        return digitalWalletRepository.processPayment(amount,fromUser,toUser,paymentMode,currency);
    }

    public TransactionStatus processPayment(String toUser,String fromUser,int amount,PaymentMode paymentMode) throws CloneNotSupportedException {
        return digitalWalletRepository.processPayment(amount,fromUser,toUser,paymentMode,digitalWalletRepository.getDigitalWallet(fromUser).getCurrency());
    }

    public TransactionStatus processPayment(String toUser,String fromUser,int amount) throws CloneNotSupportedException {
        return digitalWalletRepository.processPayment(amount,fromUser,toUser,digitalWalletRepository.getDigitalWallet(toUser).getWallet(),digitalWalletRepository.getDigitalWallet(fromUser).getCurrency());
    }
    public List<Transaction> getUserTransactionHistory(String userId) throws UserNotFoundException {
        if(!digitalWalletRepository.userExists(userId))
            throw new UserNotFoundException("User Not found for id "+userId);
        return digitalWalletRepository.getUserTransactionHistory(userId);
    }

    public int updateWalletBalance(String userId, int amount) throws UserNotFoundException {
        if(!digitalWalletRepository.userExists(userId))
            throw new UserNotFoundException("User Not found for id "+userId);
        return digitalWalletRepository.getDigitalWallet(userId).getWallet().updateBalance(amount);
    }
}
