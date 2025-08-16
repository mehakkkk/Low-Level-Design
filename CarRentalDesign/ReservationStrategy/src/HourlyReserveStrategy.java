import java.time.Duration;
import java.time.LocalDateTime;

public class HourlyReserveStrategy implements ReservationStrategy{
    @Override
    public long calculateReservationAmount(LocalDateTime to, LocalDateTime from, int fare) {

        //get the number of hours
        return Duration.between(to, from).toHours() * fare;
    }
}
