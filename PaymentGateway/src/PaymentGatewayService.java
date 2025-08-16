import java.util.List;
import java.util.Scanner;

public class PaymentGatewayService {
    private PaymentGateway paymentGateway;

    public PaymentGatewayService(PaymentGateway paymentGateway)
    {
        this.paymentGateway = paymentGateway;
    }

    public void displayPaymentMethodsSupportedByPG()
    {
        System.out.println("displaying all the payment methods::");
        for(TransactionType type:TransactionType.values())
        {
            System.out.println(type);
        }
        System.out.println("========================================");
    }

    private void displayPaymentMethodsSupportByUser(String userId)
    {
        System.out.println("displaying all payment methods supported by user ");
        List<PaymentMode> paymentModes = paymentGateway.getUserPaymentModeList(userId);
        paymentModes.stream().forEach(paymentMode -> System.out.println(paymentMode));
        System.out.println("========================================");
    }

    public void addClient(Client client)
    {
        paymentGateway.addClient(client);
    }

    public void addUser(User user)
    {
        paymentGateway.addUser(user);
    }

    public Transaction makePayment(String userId,Client client,Long amount)
    {
        //first display user how exactly wants to pay
        displayPaymentMethodsSupportByUser(userId);
        System.out.println("Select the payment method");
        Scanner in = new Scanner(System.in);
        PaymentMode paymentMode = paymentGateway.getUserPaymentModeList(userId).get(in.nextInt());
        Transaction txn = null;
        if(paymentMode!=null) {
            //generate new transaction
            String txnId = PaymentGatewayUtil.generateTransactionId();
            txn = new Transaction(txnId, client, amount, paymentMode, userId, TransactionStatus.IN_PROGRESS);
            //now first verify this transaction
            if (PaymentVerificationFactory.getVerificationStrategy(paymentMode).verify(txn)) {
                //add the transaction
                paymentGateway.addTransaction(txn);
                if (client.execute(txn))
                    System.out.println("Transaction successful!");
                else
                    System.out.println("Transaction failed.");
            }
        }
        return txn;
    }

    public void displayAllUserTransaction(String userId)
    {
        System.out.println("===========all User transaction==========");
        System.out.println(userId+"/n"+paymentGateway.getUserTransactionList(userId));
        System.out.println("=========================================");
    }
}
