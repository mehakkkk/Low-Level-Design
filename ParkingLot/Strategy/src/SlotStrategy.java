public interface SlotStrategy {
    public void addSlot(Slot slot);
    public void removeSlot(Slot slot);
    public Slot getNextSlot(VehicleType type);
}
