public class Player {
    int id;
    String name;
    int position;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        position=0;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
