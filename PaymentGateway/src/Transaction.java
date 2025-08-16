public class Transaction {
    String transactionId;
    Client client;
    long amountTobePaid;
    PaymentMode paymentMode;
    TransactionStatus transactionStatus;
    String userId;

    public Transaction(String transactionId, Client client, long amountTobePaid, PaymentMode paymentMode,String userId, TransactionStatus transactionStatus) {
        this.transactionId = transactionId;
        this.client = client;
        this.amountTobePaid = amountTobePaid;
        this.paymentMode = paymentMode;
        this.userId = userId;
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Client getClient() {
        return client;
    }

    public long getAmountTobePaid() {
        return amountTobePaid;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", client=" + client +
                ", amountTobePaid=" + amountTobePaid +
                ", paymentMode=" + paymentMode +
                ", transactionStatus=" + transactionStatus +
                ", userId='" + userId + '\'' +
                '}';
    }
}
