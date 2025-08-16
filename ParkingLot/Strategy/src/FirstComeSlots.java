import java.util.LinkedList;
import java.util.Queue;

public class FirstComeSlots implements SlotStrategy{
    private Queue<Slot> slots;

    public FirstComeSlots()
    {
        slots = new LinkedList<>();
    }

    @Override
    public void addSlot(Slot slot) {
        slots.add(slot);
    }

    @Override
    public void removeSlot(Slot slot) {
        slots.remove(slot);
    }

    @Override
    public Slot getNextSlot(VehicleType type) {
        return this.slots.poll();
    }
}
