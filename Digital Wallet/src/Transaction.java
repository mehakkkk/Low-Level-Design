import java.util.UUID;

public class Transaction implements Cloneable {

    private String userTo;
    private String userFrom;
    private PaymentType paymentType;
    private int amount;
    private Currency currency;
    private UUID id;
    private TransferType transferType;
    private TransactionStatus status;

    public Transaction(String userTo, String userFrom, PaymentType paymentType, int amount, Currency currency) {
        this.userTo = userTo;
        this.userFrom = userFrom;
        this.paymentType = paymentType;
        this.amount = amount;
        this.currency = currency;
        this.id = UUID.randomUUID();
        this.status = TransactionStatus.IN_PROCESS;
    }

    public String getId() {
        return id.toString();
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }

    public Transaction getTransaction()
    {
        return this;
    }

    public boolean updateTransactionStatus(String id, TransactionStatus transactionStatus)
    {
        this.status = transactionStatus;
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public TransactionStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "userTo='" + userTo + '\'' +
                ", userFrom='" + userFrom + '\'' +
                ", paymentType=" + paymentType +
                ", amount=" + amount +
                ", currency=" + currency +
                ", id=" + id +
                ", transferType=" + transferType +
                ", status=" + status +
                '}';
    }
}
