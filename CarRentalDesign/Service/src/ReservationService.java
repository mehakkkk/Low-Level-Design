import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationService {
    private Map<String,List<Reservation>> reservationList;

    public ReservationService(){
        reservationList = new HashMap<>();
    }

    public Reservation createReservation(User user, Vehicle vehicle, LocalDateTime till, LocalDateTime from, ReservationType type)
    {
        // Create a new Reservation object
        Reservation reservation = new Reservation(vehicle, user, from, till, type, ReservationStatus.IN_PROGRESS);

        // Get the existing list of reservations for the user, or create a new list if none exists
        List<Reservation> userReservations = reservationList.getOrDefault(user.id, new ArrayList<>());

        // Add the new reservation to the user's reservation list
        userReservations.add(reservation);

        // Update the map with the user's updated reservation list
        reservationList.put(user.id, userReservations);

        // Return the newly created reservation
        return reservation;
    }

    public void cancelReservation(int userId,String vehicleNumber)
    {
        //from the reservation map find if we have user with provided id,
    }

    Reservation completePayment(String userId,String vehicleNumber)
    {
        Reservation reservation = reservationList.get(userId).stream()
                .filter(r->r.getVehicle().getVehicleNumber().equals(vehicleNumber))
                .findFirst().orElseGet(null);

        reservation.updateStatus(ReservationStatus.UPCOMING_TRIP);
        return  reservation;
    }


}
