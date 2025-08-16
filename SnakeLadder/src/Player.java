public class Player {
    private int id;
    private int currentPosition;

    public int getCurrentPosition()
    {
        return currentPosition;
    }

    public void updateCurrentPosition(int position)
    {
        this.currentPosition = position;
    }

}
