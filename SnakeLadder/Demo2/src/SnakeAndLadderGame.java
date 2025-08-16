import java.util.List;

public class SnakeAndLadderGame {
    private Board board;
    List<Player> playerList;
    private Dice dice;
    int currentPlayerIndex = 0;

    public SnakeAndLadderGame(int boardSize,List<Player> playerList,int numOfDice,int numOfSnakes,int numOfLadder)
    {
        board = new Board(boardSize,numOfSnakes,numOfLadder);
        dice = new Dice(numOfDice);
        this.playerList = playerList;
    }

    public void play()
    {
        while(!isGameOver())
        {
            Player currentPlayer = playerList.get(currentPlayerIndex);
            int diceRoll = dice.rollDice();
            int newPosition = currentPlayer.getPosition() + diceRoll;
            if (newPosition < board.getBoardSize()) {
                currentPlayer.setPosition(board.getNewPositionAfterSnakeOrLadder(newPosition));
                System.out.println(currentPlayer.getName() + " rolled a " + diceRoll +
                        " and moved to position " + currentPlayer.getPosition());
            }
            if (newPosition >= board.getBoardSize()) {
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % playerList.size();
        }
    }

    private boolean isGameOver() {
        for (Player player : playerList) {
            if (player.getPosition() == board.getBoardSize()) {
                return true;
            }
        }
        return false;
    }
}
