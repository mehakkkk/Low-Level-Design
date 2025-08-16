public final class VacationPackage {

    private String flight;
    private String hotel;
    private String carRental;
    private String meals;
    private String attractions;

    public VacationPackage(String flight, String hotel, String carRental, String meals, String attractions) {
        this.flight = flight;
        this.hotel = hotel;
        this.carRental = carRental;
        this.meals = meals;
        this.attractions = attractions;
    }

    @Override
    public String toString() {
        return "VacationPackage{" +
                "flight='" + flight + '\'' +
                ", hotel='" + hotel + '\'' +
                ", carRental='" + carRental + '\'' +
                ", meals='" + meals + '\'' +
                ", attractions='" + attractions + '\'' +
                '}';
    }

    public String getFlight() {
        return flight;
    }

    public String getHotel() {
        return hotel;
    }

    public String getCarRental() {
        return carRental;
    }

    public String getMeals() {
        return meals;
    }

    public String getAttractions() {
        return attractions;
    }

    public static VacationPackageBuilder getVactationBuilder()
    {
        return new VacationPackageBuilder();
    }
}
