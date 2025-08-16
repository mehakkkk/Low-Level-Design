import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParkingSlotManager {
    private SlotStrategy slotStrategy;

    public ParkingSlotManager(SlotStrategy slotStrategy) {
        this.slotStrategy = slotStrategy;
    }

    public void addSlots(int capacity)
    {
        //if even id then create 2W slot
        IntStream.rangeClosed(1,capacity)
                .mapToObj(id->{
                    if(id%2 == 0)
                        return new Slot(String.valueOf(id)+"2W",VehicleType.TWO_WHEELER,10);
                    else
                        return new Slot(String.valueOf(id)+"4W",VehicleType.FOUR_WHEELER,20);
                })
                .forEach(slotStrategy::addSlot);
    }

    public void removeSlot(Slot slot)
    {
        slotStrategy.removeSlot(slot);
    }

    public Slot getAvailableSlot(VehicleType type) {
        return slotStrategy.getNextSlot(type);
    }

    public boolean validateSlotVehicle(Vehicle vehicle,Slot slot)
    {
        return slot.getSlotVehicle() == vehicle;
    }


}
