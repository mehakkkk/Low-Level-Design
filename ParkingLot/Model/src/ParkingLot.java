
/*

Singleton class, to hold parking slots
***/

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    static private ParkingLot INSTANCE;
    private final ParkingSlotManager slotManager;

    private int capacity;
    Map<String,Slot> slotMap;

    private ParkingLot(int capacity,ParkingSlotManager slotManager)
    {
        slotMap = new HashMap<>();
        this.capacity = capacity;
        this.slotManager = slotManager;
    }

    public static ParkingLot getParkingLot(int capacity,ParkingSlotManager slotManager)
    {
        if(INSTANCE == null)
        {
            synchronized (ParkingLot.class)
            {
                if(INSTANCE == null)
                    INSTANCE = new ParkingLot(capacity,slotManager);
            }
        }
        return INSTANCE;
    }

    public void parkVehicle(Slot slot,Vehicle vehicle) throws SlotNotAvailableException {
        if(!slotMap.containsKey(slot.getId()))
        {
            //add the slot to parkinglot with vehicle
            //before that check whether slot is still available
            if(slot.isSlotFree())
            {
                slot.assignCar(vehicle);
                slotMap.put(slot.getId(),slot);
            }
            else
                throw new SlotNotAvailableException("Slot already occupied");

        }
    }

    public Vehicle unParkVehicle(Slot slot) throws SlotNotAvailableException {
        //if slot is already empty
        if(slot.isSlotFree())
        {
            throw new SlotNotAvailableException("slot is already empty");
        }
        slotMap.remove(slot.getId());
        return slot.removeCar();

    }

    public Slot getSlot(String slotId) throws SlotNotAvailableException {
        if(slotMap.containsKey(slotId))
            return slotMap.get(slotId);

        throw new SlotNotAvailableException("No slot found with id "+slotId);
    }

    public Slot getAvailableSlot(VehicleType type) throws SlotNotAvailableException {
        Slot slot = slotManager.getAvailableSlot(type);
        if(slot == null)
            throw new SlotNotAvailableException("All slots full");
        return slot;
    }
}
