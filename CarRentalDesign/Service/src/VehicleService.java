import java.time.LocalDateTime;

public class VehicleService {
    VehicleInventory vehicleInventory;

    public VehicleService()
    {
        this.vehicleInventory = new VehicleInventory();
    }

    public void addVehicle(Vehicle... vehicles) {
        try{
            for(Vehicle vehicle:vehicles)
                vehicleInventory.addVehicle(vehicle);
        }
        catch(VehicleAlreadyAddedException e)
        {
            e.printStackTrace();
        }
    }

    public void removeVehicle(String vehicleNumber) {
        try{
            vehicleInventory.removeVehicle(vehicleNumber);
        }
        catch (NoVehicleFoundException e)
        {
            e.printStackTrace();
        }
    }

    public boolean isVehicleAvailable(String vehicleNumber, LocalDateTime from,LocalDateTime till)  {

        try{
            boolean result =  vehicleInventory.getVehicleReservations(vehicleNumber)
                    .stream().noneMatch(vehicle->
                            (till.isBefore(vehicle.getBookedFrom()) || from.isAfter(vehicle.getBookedTill())));

            if(result == false)
                throw new AlreadyBookedException("Vehicle already booked for the select Dates");

            return true;
        }
        catch (NoVehicleFoundException | AlreadyBookedException e)
        {
           System.out.println(e.getMessage());
        }
        return false;
    }

    public Vehicle getVehicle(String vehicleNumber)
    {
        return vehicleInventory.getVehicleById(vehicleNumber).get();
    }
    final void displayActiveVehicles()
    {
        //display only active vehicles
        vehicleInventory.getVehicles().stream().filter(v->v.getStatus().equals(VehicleStatus.ACTIVE)).forEach(System.out::println);
    }

    public void reserveVehicle(Reservation reservation,String vehicleNumber) {
        Vehicle vehicle = getVehicle(vehicleNumber);
        vehicle.addReservation(reservation);
    }
}
