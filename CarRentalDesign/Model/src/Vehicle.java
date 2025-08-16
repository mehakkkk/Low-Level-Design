import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    String vehicleNumber;
    String chasisNumber;
    String brand;
    String model;
    String modelYear;
    long kmDriven;
    int hourlyRent;
    int dailyRent;
    VehicleStatus status;
    List<Reservation> history;

    public Vehicle(String vehicleNumber, String chasisNumber, String brand, String model, String modelYear, long kmDriven, int hourlyRent, int dailyRent, VehicleStatus status) {
        this.vehicleNumber = vehicleNumber;
        this.chasisNumber = chasisNumber;
        this.brand = brand;
        this.model = model;
        this.modelYear = modelYear;
        this.kmDriven = kmDriven;
        this.hourlyRent = hourlyRent;
        this.dailyRent = dailyRent;
        this.status = status;
        this.history = new ArrayList<>();
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void addReservation(Reservation reservation)
    {
        history.add(reservation);
    }

    public List<Reservation> getAllReservations()
    {
        return history;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "dailyRent=" + dailyRent +
                ", hourlyRent=" + hourlyRent +
                ", kmDriven=" + kmDriven +
                ", modelYear='" + modelYear + '\'' +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                '}';
    }
}
