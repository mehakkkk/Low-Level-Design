import drink.CoffeeFactory;
import drink.HotDrink;
import drink.HotDrinkFactory;
import drink.TeaFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AbstractFactory {
    private Map<String,HotDrinkFactory> map;
    public AbstractFactory()
    {
        //get all classes
        map = new HashMap<>(Map.ofEntries(Map.entry("Tea",new TeaFactory()),Map.entry("Coffee",new CoffeeFactory())));
    }

    public void prepareDrink()
    {
        System.out.println("Available drinks");
        map.forEach((s,f)->{
            System.out.println(s);
        });

        while(true)
        {
            Scanner in = new Scanner(System.in);
            String opt = in.nextLine();
            int amount=0;
            if(opt != null && map.containsKey(opt))
            {
                System.out.println("Specify amount");
                if((amount = in.nextInt()) >0)
                {
                   HotDrink drink = map.get(opt).prepare(amount);
                   drink.consume();
                }
            }
            else if(opt.equalsIgnoreCase("exit"))
                return;
            else
                System.out.println("Please select a valid drink");
        }
    }
    public static void main(String[] args) {

        AbstractFactory drinkMachine= new AbstractFactory();
        drinkMachine.prepareDrink();
    }
}


