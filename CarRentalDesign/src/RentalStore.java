import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.Temporal;

public class RentalStore {
    VehicleService vehicleService;
    ReservationService reservationService;

    public RentalStore()
    {
        vehicleService = new VehicleService();
        reservationService = new ReservationService();
    }

    public void addVehicle(Vehicle... vehicle)
    {
        vehicleService.addVehicle(vehicle);
    }

    public void displayVehicles()
    {
        vehicleService.displayActiveVehicles();
    }
    public void removeVehicle(String vehicleNumber)
    {
        vehicleService.removeVehicle(vehicleNumber);
    }

    public boolean selectVehicleForRent(String vehicleNumber,LocalDateTime from,LocalDateTime till)
    {
        return vehicleService.isVehicleAvailable(vehicleNumber,from,till);

    }

    public void makeReservation(User user,String vehicleNumber, LocalDateTime from,LocalDateTime till,ReservationType type)
    {
        Vehicle vehicle = vehicleService.getVehicle(vehicleNumber);
        Reservation reservation = reservationService.createReservation(user,vehicle,from,till,type);
    }

    public void pay(User user,String vehicleNumber)
    {
        //from the reservation list find if there's a reservation with the userId
        Reservation reservation = reservationService.completePayment(user.id, vehicleNumber);
        vehicleService.reserveVehicle(reservation,vehicleNumber);
    }

    public static void main(String[] args) {
        RentalStore rentalStore = new RentalStore();

        //display all available vehicles
        rentalStore.displayVehicles();

        //create a user
        User user1 = new User("Mehak","Cdhjeo21","1");

        rentalStore.selectVehicleForRent("ABC123",LocalDateTime.now(),LocalDateTime.of(2024, Month.AUGUST, 24, 14, 33, 48, 123456789));
        rentalStore.makeReservation(user1,"ABC123",LocalDateTime.now(),LocalDateTime.of(2024, Month.AUGUST, 24, 14, 33, 48, 123456789),ReservationType.DAILY);
        rentalStore.pay(user1,"ABC123");

        rentalStore.selectVehicleForRent("ABC123",LocalDateTime.now(),LocalDateTime.of(2024, Month.AUGUST, 24, 14, 33, 48, 123456789));




    }
}
