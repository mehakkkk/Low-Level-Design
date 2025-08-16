import java.util.UUID;

public class Ride {
    private UUID id;
    private Location src;
    private Location dest;
    private UUID userId;
    private UUID driverId;
    private double fare;
    private RideStatus status;
    private VehicleType type;

    public Ride(Location src, Location dest, UUID userId,VehicleType type) {
        this.src = src;
        this.dest = dest;
        this.userId = userId;
        this.type = type;
        this.fare = LocationUtility.getDistance(src,dest)*type.getValue();
        this.id = UUID.randomUUID();
    }


    public Location getSrc() {
        return src;
    }

    public void setDriverId(UUID driverId) {
        this.driverId = driverId;
    }

    public Location getDest() {
        return dest;
    }


    public UUID getUserId() {
        return userId;
    }


    public UUID getDriverId() {
        return driverId;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", src=" + src +
                ", dest=" + dest +
                ", userId=" + userId +
                ", driverId=" + driverId +
                ", fare=" + fare +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
