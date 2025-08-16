import java.time.LocalDate;
import java.time.LocalDateTime;

public class Show {
    Screen screen;
    Movie movie;
    LocalDateTime startDateTime;
    String id;

    public Show(Movie movie, LocalDateTime startDateTime, String id) {

        this.movie = movie;
        this.startDateTime = startDateTime;
        this.id = id;
    }

    public Screen getScreen() {
        return screen;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public String getId() {
        return id;
    }

    public void updateScreen(Screen screen)
    {
        this.screen = screen;
    }

    public void updateMovie(Movie movie)
    {
        this.movie = movie;
    }
}
