import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleInventory {
    List<Vehicle> vehicleList;

    //default contructors to insert default values to list
    public VehicleInventory()
    {
        Vehicle vehicle1 = new Vehicle("ABC123", "CH123456", "Toyota", "Corolla", "2020", 15000, 50, 300, VehicleStatus.ACTIVE);
        Vehicle vehicle2 = new Vehicle("XYZ789", "CH789012", "Honda", "Civic", "2019", 20000, 60, 350, VehicleStatus.ACTIVE);
        Vehicle vehicle3 = new Vehicle("LMN456", "CH345678", "Ford", "Focus", "2021", 10000, 55, 320, VehicleStatus.INACTIVE);
        Vehicle vehicle4 = new Vehicle("PQR321", "CH901234", "Chevrolet", "Malibu", "2018", 25000, 65, 370, VehicleStatus.ACTIVE);
        Vehicle vehicle5 = new Vehicle("STU654", "CH567890", "Nissan", "Altima", "2022", 5000, 70, 400, VehicleStatus.ACTIVE);

        vehicleList = new ArrayList<>(List.of(vehicle1, vehicle2, vehicle3, vehicle4, vehicle5));

    }

    public void addVehicle(Vehicle vehicle) throws VehicleAlreadyAddedException {
        //check if vehicles already present or not
        if( getVehicle(vehicle).orElseGet(null) == vehicle)
            throw new VehicleAlreadyAddedException("Vehicle already in the inventory");
        vehicleList.add(vehicle);


    }

    public void removeVehicle(String vehicleNumber) throws NoVehicleFoundException {
        Vehicle vehicle = getVehicleById(vehicleNumber).orElseGet(null);
        if(vehicle == null)
            throw new NoVehicleFoundException("No vehicle found with vehicle number "+vehicleNumber);
    }

    public List<Reservation> getVehicleReservations(String vehicleNumber) throws NoVehicleFoundException {
        Vehicle vehicle = getVehicleById(vehicleNumber).orElseGet(null);
        if(vehicle == null)
            throw new NoVehicleFoundException("No vehicle found with vehicle number "+vehicleNumber);
        return vehicle.getAllReservations();
    }

    protected Optional<Vehicle> getVehicleById(String vehicleNumber)
    {
        return vehicleList.stream()
                .filter(v->v.getVehicleNumber() == vehicleNumber).findAny();
    }
    private Optional<Vehicle> getVehicle(Vehicle vehicle)
    {
        return vehicleList.stream()
                .filter(v->v == vehicle).findAny();
    }

    public List<Vehicle> getVehicles() {
        return vehicleList;
    }

}
