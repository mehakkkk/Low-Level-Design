import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Movie {
    private String name;
    private String Director;
    private List<String> actors;
    private Genre genre;
    private LocalDate releaseDate;

    public Movie(String name, String director, List<String> actors, Genre genre, LocalDate releaseDate) {
        this.name = name;
        Director = director;
        this.actors = actors;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return Director;
    }

    public List<String> getActors() {
        return actors;
    }

    public Genre getGenre() {
        return genre;
    }
}
