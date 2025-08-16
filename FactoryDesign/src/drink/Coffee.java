package drink;

public class Coffee implements HotDrink{
    @Override
    public void consume() {
        System.out.println("Enjoyed the coffee");
    }
}
