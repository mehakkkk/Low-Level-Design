import java.time.LocalDateTime;

public class Payment {
    Ride ride;
    LocalDateTime doneAt;

    public Payment(Ride ride) {
        this.ride = ride;
        this.doneAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Payment{" +
                "ride=" + ride +
                ", doneAt=" + doneAt +
                '}';
    }
}
