import java.util.*;

public class MovieService {
    private List<Movie> movies;
    private Map<String,List<Movie>> moviesByCity;

    public MovieService(){
        movies = new ArrayList<>();
        moviesByCity = new HashMap<>();
    }

    public void addMovie(Movie movie,String... city)
    {
        movies.add(movie);
        moviesByCity.getOrDefault(city,new ArrayList<>()).add(movie);
    }

    public Optional<Movie> getMovieByName(String name)
    {
        return movies.stream().filter(m->m.getName().equals(name)).findFirst();
    }

    public List<Movie> getMoviesByCity(String city)
    {
        return moviesByCity.get(city);
    }
}
