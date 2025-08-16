import java.util.*;
import java.util.stream.Collectors;

public class BookingService {
    private Map<String, List<Seat>> seatsByShow;  // Keyed by show ID
    private Map<String, Booking> bookings;  // Keyed by booking ID

    public BookingService() {
        seatsByShow = new HashMap<>();
        bookings = new HashMap<>();

    }

    public void updateBookingShowSeats(Show show){
        Screen screen = show.getScreen();
        seatsByShow.put(show.getId(),screen.getSeats());
    }

    // Add seats to a show
    private void addSeatsToShow(Show show, List<Seat> seats) {
        seatsByShow.put(show.getId(), seats);
    }

    // Book seats for a show
    public Booking bookSeats(Show show, List<String> seatNumbers, String userId) {
        List<Seat> availableSeats = seatsByShow.get(show.getId());
        if (availableSeats == null) {
            throw new IllegalArgumentException("Show not found.");
        }

        List<Seat> seatsToBook = availableSeats.stream()
                .filter(seat -> seatNumbers.contains(seat.getSeatId()) && !seat.isBooked())
                .collect(Collectors.toList());

        if (seatsToBook.size() != seatNumbers.size()) {
            throw new IllegalArgumentException("Some seats are not available.");
        }

        // Book the seats
        for (Seat seat : seatsToBook) {
            seat.book();
        }

        String bookingId = UUID.randomUUID().toString();  // Generate a unique ID for the booking
        Booking booking = new Booking(bookingId, show, seatsToBook, userId);
        bookings.put(bookingId, booking);

        return booking;
    }

    // Cancel a booking
    public void cancelBooking(String bookingId) {
        Booking booking = bookings.remove(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found.");
        }

        for (Seat seat : booking.getSeats()) {
            seat.cancel();
        }
    }

    // Get booking details
    public Booking getBooking(String bookingId) {
        return bookings.get(bookingId);
    }
}
