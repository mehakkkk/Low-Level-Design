import java.time.LocalDateTime;

public class Reservation {
    Vehicle vehicle;
    User user;
    LocalDateTime bookedFrom;
    LocalDateTime bookedTill;
    ReservationType reservationType;
    ReservationStatus reservationStatus;

    public Reservation(Vehicle vehicle, User user, LocalDateTime bookedFrom,
                       LocalDateTime bookedTill, ReservationType reservationType,
                       ReservationStatus reservationStatus) {
        this.vehicle = vehicle;
        this.user = user;
        this.bookedFrom = bookedFrom;
        this.bookedTill = bookedTill;
        this.reservationType = reservationType;
        this.reservationStatus = reservationStatus;
    }

    public void updateStatus(ReservationStatus status)
    {
        reservationStatus = status;
    }

    public LocalDateTime getBookedTill() {
        return bookedTill;
    }

    public LocalDateTime getBookedFrom() {
        return bookedFrom;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
