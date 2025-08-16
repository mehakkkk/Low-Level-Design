import java.time.LocalDateTime;

public interface ReservationStrategy {
    public long calculateReservationAmount(LocalDateTime to, LocalDateTime from, int fare);
}
