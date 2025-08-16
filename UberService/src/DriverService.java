import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DriverService {
    private DriverRepository driverRepository;

    public DriverService()
    {
        driverRepository = DriverRepository.getInstance();
    }

    public String addDriver(String name, String number, String email, String vehicleNumber, VehicleType type, String license) throws InvalidUserData {
        // Validate inputs in a concise manner
        if (name == null || name.isEmpty()) throw new InvalidUserData("Name is required.");
        if (number == null || number.isEmpty() || number.length() != 10) throw new InvalidUserData("Phone number must be 10 digits.");
        if (email == null || email.isEmpty() || !email.contains("@") || !email.contains(".")) throw new InvalidUserData("Invalid email format.");
        if (vehicleNumber == null || vehicleNumber.isEmpty()) throw new InvalidUserData("Vehicle number is required.");
        if (type == null) throw new InvalidUserData("Vehicle type is required.");
        if (license == null || license.isEmpty()) throw new InvalidUserData("License is required.");

        // Create and add the driver
        Driver driver = new Driver(name, number, email, vehicleNumber, type, license);
        driverRepository.addDriver(driver);

        return driver.getId().toString();
    }


    public boolean removeDriver(String driverId) throws InvalidUserData, UserNotFoundException {
        if(driverId == null || driverId.isEmpty())
            throw new InvalidUserData("Invalid driverId for deletion");
        if(!driverRepository.userExists(driverId))
            throw new UserNotFoundException("Driver not found with id "+driverId);

        boolean isRemoved;
        synchronized (UserDriverLockManager.acquireLock(driverId))
        {
            isRemoved = driverRepository.deleteUser(driverId);
            if(!isRemoved)
                throw new InvalidUserData("Deletion for driver failed");
        }
        UserDriverLockManager.releaseLock(driverId);
        return true;
    }

    public boolean updateDriverLocation(String userId, Location location) throws InvalidUserData, UserNotFoundException {
        if (location == null) {
            throw new InvalidUserData("Location cannot be null");
        }


        boolean isUpdated;
        synchronized (UserDriverLockManager.acquireLock(userId)) {
            Driver driver = driverRepository.getUser(userId);
            driver.setLocation(location);
            isUpdated = driverRepository.updateUser(driver);
            UserDriverLockManager.releaseLock(userId);
            if (!isUpdated) {
                throw new InvalidUserData("Failed to update driver location in the database.");
            }
        }
        return true;
    }

    public List<Driver> getDriversWithinProximity(Location location)
    {
        //get Drivers within 3 km
        List<Driver> drivers = driverRepository.getDrivers();
        return drivers.stream()
                .filter(driver->LocationUtility.getDistance(location,driver.getLocation())<3).collect(Collectors.toList());
    }

    public boolean acceptRide(String driverId, Ride ride) throws InvalidUserData {
        if(driverId == null || ride == null )
            throw new InvalidUserData("Either driver Id or ride is null");

        synchronized(UserDriverLockManager.acquireLock(ride.getId().toString()))
        {
            if(ride.getDriverId() == null)
            {
                ride.setStatus(RideStatus.ACCEPT);
                ride.setDriverId(UUID.fromString(driverId));
            }
            else {
                System.out.println("Ride already booked by another driver");
                return false;
            }

        }
        UserDriverLockManager.acquireLock(ride.getId().toString());
        return true;

    }
    public boolean cancelRide(String rideId) throws InvalidUserData {
        if(rideId == null || rideId == null )
            throw new InvalidUserData("Either driver Id or ride is null");

        //logic to implement cancelation
        return true;

    }

    public Driver getDriver(String driverId){
        return driverRepository.getUser(driverId);
    }
}

