import java.util.*;

public class User {
    private String name;
    private String userId;
    private Map<String, PaymentMode> paymentModes;
    private List<String> transactionIdList;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
        this.paymentModes = new HashMap<>();
        transactionIdList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public List<String> getTransactionList() {
        return transactionIdList;
    }

    public void addTransaction(String txn)
    {
        System.out.println("adding transaction:: "+txn);
        transactionIdList.add(txn);
    }

    public Map<String, PaymentMode> getPaymentModes() {
        return paymentModes;
    }

    public void addPaymentMode(PaymentMode paymentMode) {
        paymentModes.put(paymentMode.getIdentifier(), paymentMode);
    }

    public PaymentMode getPaymentMode(String identifier) {
        return paymentModes.get(identifier);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", paymentModes=" + paymentModes +
                ", transactionIdList=" + transactionIdList +
                '}';
    }
}
