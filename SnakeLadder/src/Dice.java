import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    private int numOfDice;

    public Dice(int numOfDice)
    {
        this.numOfDice = numOfDice;
    }

    public int rollDice()
    {
        int totalRolled = 0;
        int sum = 0;
        while(totalRolled<numOfDice)
        {
            sum += ThreadLocalRandom.current().nextInt(1, 6);
        }
        return sum;
    }

}
