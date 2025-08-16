import java.util.List;

public class Game {
    public static void main(String[] args) {
        SnakeAndLadderGame snakeAndLadderGame = new SnakeAndLadderGame(
                10,
                List.of(new Player(1,"mehak"),new Player(2,"Gouri")),
                1,
                4,
                5);
        snakeAndLadderGame.play();
    }
}
