public class Main {
    public static void main(String[] args) {
        RideManagerService rideManagerService = new RideManagerService();

        //create user
        String userId = rideManagerService.addUser("Mehak","me2@gmail.com","9823456718").get();

        //create driver
        String driverId = rideManagerService.addDriver("Suresh","8978675634","suresh@gmail.com","MH12HG6789",VehicleType.PRIME,"29038201").get();

        //update user location to current location
        rideManagerService.updateUserLocation(userId,new Location(40.7128, -74.0060));

        //update driver location to get rides
        rideManagerService.updateDriverLocation(driverId,new Location(40.7328, -74.0260));

        //user creates a ride
        Ride ride = rideManagerService.createRide(null,new Location(50.590,-102.2),userId,VehicleType.PRIME).get();

        //user request a ride
        driverId = rideManagerService.requestRide(ride).get();

        //driver accepts the ride
        rideManagerService.acceptRide(driverId,ride);

        //start ride
        rideManagerService.startRide(ride);

        //complete ride
        Payment payment = rideManagerService.completeRide(ride);

        System.out.println(payment);
    }
}
