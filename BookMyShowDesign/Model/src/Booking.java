import java.util.List;

public class Booking {
    private String id;  // Unique identifier for the booking
    private Show show;
    private List<Seat> seats;
    private String userId;  // ID of the user making the booking

    public Booking(String id, Show show, List<Seat> seats, String userId) {
        this.id = id;
        this.show = show;
        this.seats = seats;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getUserId() {
        return userId;
    }
}
