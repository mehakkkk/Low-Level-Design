package drink;

public class CoffeeFactory implements HotDrinkFactory{
    @Override
    public HotDrink prepare(int amount) {
        System.out.println("Grind some beam, add "+amount+" ml of hot water, brew it hot");
        return new Coffee();
    }
}
