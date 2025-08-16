import java.util.Scanner;

public class Game {
    private User user1;
    private User user2;
    private User currentPlayer;
    Board board;
    Scanner in;

    public Game(User user1,User user2)
    {
        this.user1 = user1;
        this.user2 = user2;
        board = new Board(4);
        in = new Scanner(System.in);
        currentPlayer = user1;
    }

    public void playGame()
    {
        while(true)
        {
            board.displayBoard();
            System.out.println("\n-------Player playing: "+currentPlayer.name+"--------");
            System.out.println("Enter row and column");
            int row = in.nextInt();
            int col = in.nextInt();
            if(board.makeMove(row,col,currentPlayer.playingPiece))
            {
                System.out.println("********WINNER: "+currentPlayer.name+"*******");
                return;
            }
            this.currentPlayer = currentPlayer == user1?user2:user1;
        }
    }

    public static void main(String[] args) {
        Game game = new Game(new User("M",1,PlayingPiece.X),new User("G",2,PlayingPiece.O));
        game.playGame();
    }
}
