public class LocationUtility {
    public static double getDistance(Location l1,Location l2)
    {
        return Math.sqrt(Math.pow(l2.getLatitude()-l1.getLatitude(),2) + Math.pow(l2.getLongitude()-l1.getLongitude(),2));
    }

}
