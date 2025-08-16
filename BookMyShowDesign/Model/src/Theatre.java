import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Theatre {
    private List<Show> shows;
    private String city;
    private String name;
    private int id;

    public Theatre(String city, String name,int id) {
        this.city = city;
        this.name = name;
        shows = new ArrayList<>();
        this.id = id;
    }

    public void addShows(Show...shows)
    {
        this.shows.addAll(Arrays.asList(shows));
    }

    public List<Show> getShows() {
        return shows;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
