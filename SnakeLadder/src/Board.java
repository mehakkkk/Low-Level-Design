import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private Cell[][] cells;


    public Board(int boardSize,int numOfSnakes,int numOfLadders)
    {
        cells = new Cell[boardSize][boardSize];
        //initialize cells
        for(int i =0;i<boardSize;i++)
        {
            for(int j =0;j<boardSize;j++)
            {
                cells[i][j] = new Cell();
            }
        }

        initializeSnakesAndLadders(numOfSnakes,numOfLadders);
    }

    private void initializeSnakesAndLadders(int numOfSnakes,int numOfLadders)
    {
        //initialize snakes
        while(numOfSnakes>0)
        {
            int snakeHead = ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);

            if(snakeHead<snakeTail)
                continue;

            SnakeLadder snake = new SnakeLadder(snakeHead,snakeTail);
            Cell cell = getCell(snakeHead);
            cell.snakeLadder = snake;
            numOfSnakes--;
        }

        //initialize ladder
        while(numOfLadders>0)
        {
            int ladderHead = ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);
            int ladderTail = ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);

            if(ladderHead<ladderTail)
                continue;

            SnakeLadder ladder = new SnakeLadder(ladderHead,ladderTail);
            Cell cell = getCell(ladderHead);
            cell.snakeLadder = ladder;

            numOfLadders--;

        }
    }

    private Cell getCell(int playerPosition) {
        int boardRow = playerPosition / cells.length;
        int boardColumn = (playerPosition % cells.length);
        return cells[boardRow][boardColumn];
    }

}
