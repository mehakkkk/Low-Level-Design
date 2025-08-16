import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;

public class ParkingLotManager {
    private final ParkingLot parkingLot;


    public ParkingLotManager(ParkingLot parkingLot, ParkingSlotManager slotManager) {
        this.parkingLot = parkingLot;
    }

    Ticket parkVehicle(Vehicle vehicle) throws SlotNotAvailableException {
        Slot slot = parkingLot.getAvailableSlot(vehicle.getType());
        parkingLot.parkVehicle(slot,vehicle);
        //generate ticket because vehicle is successfully parked without exception
        Ticket ticket = new Ticket(slot.getId(),vehicle.getNumber());
        return ticket;
    }

    public boolean validateTicketOnExit(Ticket ticket) {
        //get the slot where vehicle is parked on the basis of ticket
        if(ticket.getOutDateTime()!= null && ticket.getOutDateTime().isBefore(LocalDateTime.now())) // if already unpark/outtime updated
            return false;
        return true;

    }

    public long calculateFare(Ticket ticket) throws SlotNotAvailableException {
        Slot slot = parkingLot.getSlot(ticket.getSlotId());
        if (slot == null) {
            throw new SlotNotAvailableException("Slot not found with ID: " + ticket.getSlotId());
        }
        Duration duration = Duration.between(ticket.getInDateTime(), ticket.getOutDateTime());
        long minutes = duration.toMinutes();
        return minutes;
    }

    public void pay(PaymentStrategy paymentStrategy,long amount) throws PaymentValidationException {
        //calculate amount
        paymentStrategy.pay(amount);
    }

    public Vehicle unparkVehicle(Ticket ticket) throws SlotNotAvailableException {
        Slot slot = parkingLot.getSlot(ticket.getSlotId());
        if (slot == null) {
            throw new SlotNotAvailableException("Slot not found with ID: " + ticket.getSlotId());
        }
        Vehicle vehicle = parkingLot.unParkVehicle(slot);
        if (vehicle == null) {
            throw new SlotNotAvailableException("No vehicle found for slot ID: " + ticket.getSlotId());
        }
        return vehicle;
    }

}
