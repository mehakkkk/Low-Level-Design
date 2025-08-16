import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    int n;

    public Dice(int n) {
        this.n = n;
    }

    public int rollDice()
    {
        int roll = n;
        int sum = 0;
        while(roll>0) {
            sum+= ThreadLocalRandom.current().nextInt(1,6);
            roll--;
        }
        System.out.println("dicerolled:: "+sum);
        return sum;
    }
}
