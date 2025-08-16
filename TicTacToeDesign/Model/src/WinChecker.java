public class WinChecker {
    private boolean checkRow(int row, PlayingPiece piece,Board playingBoard) {
        for (int col = 0; col < playingBoard.getSize(); col++) {
            if (playingBoard.getPiece(row,col) != piece) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int col, PlayingPiece piece,Board playingBoard) {
        for (int row = 0; row < playingBoard.getSize(); row++) {
            if (playingBoard.getPiece(row,col) != piece) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonals(PlayingPiece piece,Board playingBoard) {
        boolean leftDiagonal = true;
        boolean rightDiagonal = true;
        for (int i = 0; i < playingBoard.getSize(); i++) {
            if (playingBoard.getPiece(i,i) != piece) {
                leftDiagonal = false;
            }
            if (playingBoard.getPiece(i,playingBoard.getSize() - i - 1) != piece) {
                rightDiagonal = false;
            }
        }
        return leftDiagonal || rightDiagonal;
    }

    public boolean isWinner(int row,int col,PlayingPiece piece,Board board)
    {
        return checkRow(row,piece,board) || checkColumn(col,piece,board) || checkDiagonals(piece,board);
    }
}
