public class Location {

    private double longitude;
    private double latitude;

    public Location(double lon,double lat)
    {
        this.longitude = lon;
        this.latitude = lat;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
