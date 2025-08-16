public class CashProcessor_100 extends CashProcessor{
    public CashProcessor_100(int cashLeft, CashProcessor cashProcessor)
    {
        super(cashLeft,cashProcessor);
        NOTE_DENOMINATION = 100;
    }
}
