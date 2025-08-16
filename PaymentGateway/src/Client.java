public class Client {
    private String proprietaryId;
    private String name;
    private Bank bank;

    public Client(String proprietaryId, String name, Bank bank) {
        this.proprietaryId = proprietaryId;
        this.name = name;
        this.bank = bank;
    }

    public boolean execute(Transaction txn)
    {
        return bank.executeTransaction(txn);
    }
}
