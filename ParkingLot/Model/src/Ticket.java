import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ticket {
    String slotId;
    String vehicleNumber;
    LocalDateTime inDateTime;
    LocalDateTime outDateTime;

    public Ticket(String slotId, String vehicleNumber) {
        this.slotId = slotId;
        this.vehicleNumber = vehicleNumber;
        this.inDateTime = LocalDateTime.now();
    }

    public String getSlotId() {
        return slotId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public LocalDateTime getInDateTime() {
        return inDateTime;
    }

    public void updateOutTime()
    {
        outDateTime = LocalDateTime.now();
    }

    public LocalDateTime getOutDateTime()
    {
        return outDateTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "slotId='" + slotId + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", inDateTime=" + inDateTime +
                '}';
    }
}
