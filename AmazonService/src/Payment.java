import java.util.UUID;

public class Payment {
    UUID id;
    String userId;
    int toalAmount;

    public Payment(String userId, int toalAmount) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.toalAmount = toalAmount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", toalAmount=" + toalAmount +
                '}';
    }
}
