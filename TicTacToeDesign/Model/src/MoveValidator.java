public class MoveValidator {

    public boolean isValidMove(int row,int col,Board playingBoard)
    {
        if(row<0 || col<0 || row>=playingBoard.getSize() || col>playingBoard.getSize() || playingBoard.getPiece(row,col) != null)
            return false;

        return true;
    }
}
