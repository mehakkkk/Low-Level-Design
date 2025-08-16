public class Board {
    private int size;
    private PlayingPiece[][] playingBoard;
    private MoveValidator moveValidator;
    private WinChecker winChecker;

    public Board(int size)
    {
        this.size = size;
        playingBoard = new PlayingPiece[size][size];
        moveValidator = new MoveValidator();
        winChecker = new WinChecker();
    }
    public boolean makeMove(int row,int col,PlayingPiece piece)
    {
        if(moveValidator.isValidMove(row,col,this))
        {
            playingBoard[row][col] = piece;
            return winChecker.isWinner(row,col,piece,this);
        }

        System.out.println("Not a valid move");
        return false;

    }
    public void displayBoard()
    {
        for(int i = 0;i<size;i++)
        {
            if(i==0)
                System.out.print("     ");
            System.out.print(i+"      ");
        }
        System.out.println();
        for(int i = 0;i<size;i++)
        {
            System.out.print("----------");
        }
        for(int i = 0;i<size;i++)
        {
            for(int j = 0;j<size;j++)
            {
                if(j==0)
                    System.out.print("\n"+i+" | ");
                System.out.print(playingBoard[i][j]==null?"":playingBoard[i][j]+" | ");
            }

        }
    }


    public PlayingPiece getPiece(int row,int col)
    {
        return playingBoard[row][col];
    }

    public int getSize()
    {
        return size;
    }
}
