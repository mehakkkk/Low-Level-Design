import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainConcurrent {

    public static void main(String[] args) {
        // Create the RideManagerService instance
        RideManagerService rideManagerService = new RideManagerService();

        // Number of users and drivers to simulate
        int numUsers = 5;
        int numDrivers = 3;

        // Map to keep track of user UUIDs by their name
        Map<String, UUID> userMap = new HashMap<>();

        // Create and add users sequentially
        for (int i = 1; i <= numUsers; i++) {
            final int userId = i;
            try {
                // Create user
                UUID userIdUUID = UUID.fromString(rideManagerService.addUser("User" + userId, "user" + userId + "@gmail.com", "982345671" + userId).get());  // Assuming addUser now returns the UUID

                // Store the user UUID in the map
                userMap.put("User" + userId, userIdUUID);

                // Update user location
                rideManagerService.updateUserLocation(userIdUUID.toString(), new Location(40.7128 + (userId * 0.01), -74.0060));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Create and add drivers concurrently after all users are created
        Thread[] driverThreads = new Thread[numDrivers];
        for (int i = 1; i <= numDrivers; i++) {
            final int driverId = i;
            driverThreads[i - 1] = new Thread(() -> {
                try {
                    // Create driver
                    String driverIdString = "Driver" + driverId;
                    String driverEmail = "driver" + driverId + "@gmail.com";
                    String driverPhone = "897867561" + driverId;
                    String driverLicense = "MH12HG678" + driverId;
                    VehicleType vehicleType = VehicleType.PRIME;
                    String vehicleId = "2903820" + driverId;

                    driverIdString = rideManagerService.addDriver(driverIdString, driverPhone, driverEmail, vehicleId, vehicleType, driverLicense).get();

                    // Update driver location
                    rideManagerService.updateDriverLocation(driverIdString, new Location(40.7328 + (driverId * 0.01), -74.0260));

                    // Now the driver needs to know which user to get a ride for
                    String userName = "User" + driverId;  // Assuming there's a matching user for each driver (you can improve this logic)
                    UUID userIdUUID = userMap.get(userName);

                    // Check if the user ID is valid
                    if (userIdUUID != null) {
                        // Create ride and accept it
                        Ride ride = rideManagerService.createRide(new Location(40.7128, -74.0060), new Location(50.590, -102.2), userIdUUID.toString(), VehicleType.PRIME).get();
                        rideManagerService.acceptRide(driverIdString, ride);

                        // Start ride
                        rideManagerService.startRide(ride);

                        // Complete the ride and get payment
                        Payment payment = rideManagerService.completeRide(ride);
                        System.out.println("Payment for driver " + driverId + ": " + payment);
                    } else {
                        System.out.println("User not found for driver " + driverId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            driverThreads[i - 1].start();
        }

        // Wait for all driver threads to complete
        for (Thread driverThread : driverThreads) {
            try {
                driverThread.join();  // Ensure driver thread finishes before ending the main program
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
