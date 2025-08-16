package drink;

public class TeaFactory implements HotDrinkFactory{
    @Override
    public HotDrink prepare(int amount) {
        System.out.println("add a te bag, pur "+amount+" ml of water, enjoy it hot");
        return new Tea();
    }
}
