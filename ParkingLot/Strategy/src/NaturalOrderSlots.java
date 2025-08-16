import java.util.TreeSet;

public class NaturalOrderSlots implements SlotStrategy{
    private TreeSet<Slot> slots;

    public NaturalOrderSlots()
    {
        slots = new TreeSet<>();
    }

    @Override
    public void addSlot(Slot slot) {
        this.slots.add(slot);

    }

    @Override
    public void removeSlot(Slot slot) {
        this.slots.remove(slot);
    }

    @Override
    public Slot getNextSlot(VehicleType type) {
        return this.slots.pollFirst();

    }
}
