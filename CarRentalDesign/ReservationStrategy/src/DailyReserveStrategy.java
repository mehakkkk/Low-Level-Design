import com.sun.jdi.request.DuplicateRequestException;

import java.time.Duration;
import java.time.LocalDateTime;

public class DailyReserveStrategy implements ReservationStrategy{
    @Override
    public long calculateReservationAmount(LocalDateTime to, LocalDateTime from, int fare) {
        return Duration.between(to,from).toDays()*fare;
    }
}
