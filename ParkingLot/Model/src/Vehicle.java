public class Vehicle {
    String number;
    String ownerName;
    String colour;
    VehicleType type;

    public Vehicle(String number, String ownerName, String colour, VehicleType type) {
        this.number = number;
        this.ownerName = ownerName;
        this.colour = colour;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getColour() {
        return colour;
    }

    public VehicleType getType() {
        return type;
    }
}
