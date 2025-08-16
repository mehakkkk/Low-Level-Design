public class Main {
    public static void main(String[] args) {
        CashProcessor cash_100 = new CashProcessor_100(5,null);
        CashProcessor cash_200 = new CashProcessor_200(7,cash_100);
        CashProcessor cashProcessor = new CashProcessor_500(9,cash_200);
        cashProcessor.dispenseCash(250);
    }
}
