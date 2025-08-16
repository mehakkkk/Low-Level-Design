import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RideManagerService {

    private UserService userService;
    private DriverService driverService;
    private List<Ride> activeRides; // To store active rides

    public RideManagerService() {
        this.userService = new UserService();
        this.driverService = new DriverService();
        this.activeRides = new ArrayList<>();
    }

    // Add a new user and return the user ID
    public Optional<String> addUser(String name, String email, String phone) {
        String id = null;
        try {
            id = userService.addUser(name, phone, email);
        } catch (InvalidUserData e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(id);
    }

    // Add a new driver and return the driver ID
    public Optional<String> addDriver(String name, String number, String email, String vehicleNumber, VehicleType type, String license) {
        String id = null;
        try {
            id = driverService.addDriver(name, number, email, vehicleNumber, type, license);
        } catch (InvalidUserData e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(id);
    }

    // Create a new ride with the user's and destination's locations
    public Optional<Ride> createRide(Location src, Location dest, String userId, VehicleType type) {
        Ride ride = null;
        try {
            if (dest == null)
                throw new InvalidUserData("Destination needs to be provided");

            if (src == null) {  // If the source is null, fetch the user's current location
                synchronized (UserDriverLockManager.acquireLock(userId)) {
                    User user = userService.getUser(userId);
                    ride = new Ride(user.getLocation(), dest, UUID.fromString(userId), type);
                }
                UserDriverLockManager.releaseLock(userId);
            } else {
                ride = new Ride(src, dest, UUID.fromString(userId), type);
            }
            activeRides.add(ride);  // Add the created ride to the list of active rides
        } catch (InvalidUserData | UserNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(ride);
    }

    // Request a ride by the user (this will check for available drivers and assign a driver to the ride)
    public Optional<String> requestRide(Ride ride) {
        List<Driver> availableDrivers = driverService.getDriversWithinProximity(ride.getSrc());

        if (availableDrivers.isEmpty()) {
            System.out.println("No driver available at the moment.");
            return Optional.empty();
        }

        System.out.println("Please wait, fetching you a driver");
        for (Driver driver : availableDrivers) {
            System.out.println("Income ride: " + ride.getSrc() + " to " + ride.getDest() + " " + driver.getId());
        }
        return Optional.ofNullable(availableDrivers.get(0).getId().toString());
    }

    // Accept a ride by the driver
    public boolean acceptRide(String driverId, Ride ride) {
        synchronized (UserDriverLockManager.acquireLock(ride.getId().toString())) {
            if (ride.getDriverId() != null) {
                System.out.println("Ride already booked by another driver");
                return false;
            }
            ride.setDriverId(UUID.fromString(driverId));
            ride.setStatus(RideStatus.ACCEPT);
        }
        UserDriverLockManager.releaseLock(ride.getId().toString());
        System.out.println("Ride accepted by driver " + driverId);
        return true;
    }

    // Start the ride
    public boolean startRide(Ride ride) {
        synchronized (UserDriverLockManager.acquireLock(ride.getId().toString())) {
            if (ride.getStatus() != RideStatus.ACCEPT) {
                System.out.println("Cannot start the ride. Ride must be accepted first.");
                return false;
            }
            ride.setStatus(RideStatus.IN_PROGRESS);
        }
        UserDriverLockManager.releaseLock(ride.getId().toString());
        System.out.println("Ride started.");
        return true;
    }

    // Complete the ride and generate the payment
    public Payment completeRide(Ride ride) {
        synchronized (UserDriverLockManager.acquireLock(ride.getId().toString())) {
            if (ride.getStatus() != RideStatus.IN_PROGRESS) {
                System.out.println("Cannot complete the ride. Ride is not in progress.");
                return null;
            }
            ride.setStatus(RideStatus.COMPLETE);
            UserDriverLockManager.acquireLock(ride.getId().toString());
        }

            // Add ride to user and driver history
            try {
                userService.getUser(ride.getUserId().toString()).addRide(ride);
            } catch (UserNotFoundException | InvalidUserData e) {
                e.printStackTrace();
            }
            driverService.getDriver(ride.getDriverId().toString()).addRide(ride);

            // Generate the payment
            Payment payment = new Payment(ride);
            System.out.println("Ride completed. Generating payment.");

            return payment;
    }

    // Update the user's location
    public boolean updateUserLocation(String userId, Location location) {
        boolean isUpdated = false;
        try {
            isUpdated = userService.updateUserLocation(userId, location);
        } catch (InvalidUserData | UserNotFoundException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    // Update the driver's location
    public boolean updateDriverLocation(String driverId, Location location) {
        boolean isUpdated = false;
        try {
            isUpdated = driverService.updateDriverLocation(driverId, location);
        } catch (InvalidUserData | UserNotFoundException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    // Get available rides (this can be used for checking if any rides are available for a driver to accept)
    public List<Ride> getAvailableRides(String driverId) {
        List<Ride> availableRides = new ArrayList<>();
        for (Ride ride : activeRides) {
            if (ride.getStatus() == RideStatus.REQUEST) {
                availableRides.add(ride);
            }
        }
        return availableRides;
    }
}
