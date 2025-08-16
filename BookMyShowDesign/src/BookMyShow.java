import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

public class BookMyShow {
    private MovieService movieService;
    private TheatreService theatreService;
    private BookingService bookingService;
    private Scanner scanner;

    public BookMyShow() {
        movieService = new MovieService();
        theatreService = new TheatreService();
        scanner = new Scanner(System.in);
        bookingService = new BookingService();  // Initialize with empty shows
    }

    public void start() {
        while (true) {
            System.out.println("Welcome to BookMyShow");
            System.out.println("1. Add Movie");
            System.out.println("2. Add Theatre");
            System.out.println("3. Add Show to Theatre");
            System.out.println("4. Add Screen to Show");
            System.out.println("5. Book Seats");
            System.out.println("6. View Bookings");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    addTheatre();
                    break;
                case 3:
                    addShowToTheatre();
                    break;
                case 4:
                    bookSeats();
                    break;
                case 5:
                    viewBookings();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addMovie() {
        System.out.println("Enter movie name:");
        String name = scanner.nextLine();

        System.out.println("Enter director:");
        String director = scanner.nextLine();

        System.out.println("Enter cast (comma-separated):");
        String castInput = scanner.nextLine();
        List<String> cast = List.of(castInput.split(","));

        System.out.println("Enter genre (ACTION, THRILLER, COMEDY, ROMANCE, MYSTERY):");
        Genre genre = Genre.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Enter release date (YYYY-MM-DD):");
        LocalDate releaseDate = LocalDate.parse(scanner.nextLine());

        Movie movie = new Movie(name, director, cast, genre, releaseDate);
        movieService.addMovie(movie, "Nashik", "Pune", "Agra");
        System.out.println("Movie added successfully.");
    }

    private void addTheatre() {
        System.out.println("Enter theatre city:");
        String city = scanner.nextLine();

        System.out.println("Enter theatre name:");
        String name = scanner.nextLine();

        System.out.println("Enter theatre ID:");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Theatre theatre = new Theatre(city, name, id);
        theatreService.addTheatre(theatre);
        System.out.println("Theatre added successfully.");
    }

    private void addShowToTheatre() {
        System.out.println("Enter theatre name:");
        String theatreName = scanner.nextLine();

        System.out.println("Enter movie name for the show:");
        String movieName = scanner.nextLine();

        Movie movie = movieService.getMovieByName(movieName)
                .orElseThrow(() -> new IllegalArgumentException("Movie with name " + movieName + " not found"));

        System.out.println("Enter show date time(dd-mm-yyyy) AM/PM:");
        String showDate = scanner.nextLine();

        System.out.println("Enter new screen ID:");
        String screenId = scanner.nextLine();

        System.out.println("Enter number of seats for this new screen:");
        int seatCount = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        DateTimeFormatter inputFormatter = new DateTimeFormatterBuilder()
                .appendPattern("dd-MM-yyyy ")
                .appendValue(ChronoField.HOUR_OF_AMPM, 1)
                .appendLiteral(' ')
                .appendPattern("a")
                .toFormatter();

        Show show = new Show(movie,LocalDateTime.parse(showDate, inputFormatter),screenId);

        theatreService.addShowsToTheatre(theatreName, show);
        bookingService.updateBookingShowSeats(show);  // Update BookingService with new show
        System.out.println("Show added to theatre successfully.");
    }


    private void bookSeats() {

        System.out.println("Enter theatre name:");
        String theatreName = scanner.nextLine();

        System.out.println("Enter show ID:");
        String showId = scanner.nextLine();

        System.out.println("Enter seat IDs (comma-separated):");
        String seatInput = scanner.nextLine();
        List<String> seatIds = List.of(seatInput.split(","));

        System.out.println("Enter user ID:");
        String userId = scanner.nextLine();

        try {
            Show show = theatreService.getTheatreByName(theatreName).getShows().stream()
                    .filter(s -> s.getId().equals(showId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Show not found"));

            Booking booking = bookingService.bookSeats(show, seatIds, userId);
            System.out.println("Booking successful. Booking ID: " + booking.getId());
        } catch (Exception e) {
            System.out.println("Error while booking seats: " + e.getMessage());
        }
    }

    private void viewBookings() {
        System.out.println("Enter booking ID:");
        String bookingId = scanner.nextLine();

        Booking booking = bookingService.getBooking(bookingId);
        if (booking != null) {
            System.out.println("Booking ID: " + booking.getId());
            System.out.println("Show: " + booking.getShow().getMovie().getName());
            System.out.println("Seats: " + booking.getSeats().stream()
                    .map(Seat::getSeatId)
                    .collect(Collectors.toList()));
            System.out.println("User ID: " + booking.getUserId());
        } else {
            System.out.println("Booking not found.");
        }
    }

    public static void main(String[] args) {
        BookMyShow console = new BookMyShow();
        console.start();
    }
}
