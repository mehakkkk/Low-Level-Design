public class Slot {
    private String id;
    private VehicleType type;
    private Vehicle vehicle;
    private int price;
    private boolean isFree;

    public Slot(String id, VehicleType type, int price) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.isFree = true;
    }

    public String getId() {
        return id;
    }

    public void assignCar(Vehicle vehicle)
    {
            this.vehicle = vehicle;
            this.isFree = false;

    }

    public Vehicle removeCar()
    {
        Vehicle retVehicle = this.vehicle;
        this.vehicle = null;
        this.isFree = true;
        return retVehicle;
    }

    public boolean isSlotFree()
    {
        return isFree;
    }

    public int getSlotPrice(){
        return price;
    }

    public VehicleType getSlotType(){
        return type;
    }

    public Vehicle getSlotVehicle()
    {
        return vehicle;
    }
}
