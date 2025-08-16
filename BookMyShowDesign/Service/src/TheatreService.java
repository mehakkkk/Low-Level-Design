import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreService {
    List<Theatre> theatres;
    Map<String,List<Theatre>> theatreByCity;

    public TheatreService(){
        theatres = new ArrayList<>();
        theatreByCity = new HashMap<>();
    }

    public void addTheatre(Theatre... theatresList)
    {
        for(Theatre theatre:theatresList) {
            theatres.add(theatre);
            theatreByCity.getOrDefault(theatre.getCity(), new ArrayList<>()).add(theatre);
        }
    }

    public Theatre getTheatreByName(String theatreName)
    {
        Theatre theatre = theatres.stream().filter(t->t.getName().equals(theatreName))
                .findFirst().orElse(null);
        if(theatre == null)
            throw new TypeNotPresentException("Theatre with name "+theatreName+" not found",null);

        return theatre;
    }

    public void addShowsToTheatre(String theatreName, Show... show)
    {
        Theatre theatre = getTheatreByName(theatreName);

        theatre.addShows(show);
    }

    public void addScreenToShow(String theatreName,String showId,Screen screen)
    {
        Theatre theatre = getTheatreByName(theatreName);

        Show show = theatre.getShows().stream().filter(s->s.getId()==showId).findFirst().orElse(null);

        if(show == null)
            throw new TypeNotPresentException("Show with id "+showId+" not found",null);

        show.updateScreen(screen);
    }

    public void addMovieToTheatre(String theatreName,String showId,Movie movie)
    {
        Theatre theatre = getTheatreByName(theatreName);

        Show show = theatre.getShows().stream().filter(s->s.getId()==showId).findFirst().orElse(null);

        if(show == null)
            throw new TypeNotPresentException("Show with id "+showId+" not found",null);

        show.updateMovie(movie);
    }

}
