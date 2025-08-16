public class VacationPackageBuilder {

    private String flight;
    private String hotel;
    private String carRental;
    private String meals;
    private String attractions;

    public VacationPackageBuilder setFlight(String flight)
    {
        this.flight = flight;
        return this;
    }

    public VacationPackageBuilder setHotel(String hotel)
    {
        this.hotel = hotel;
        return this;
    }

    public VacationPackageBuilder setCarRental(String carRental)
    {
        this.carRental = carRental;
        return this;
    }

    public VacationPackageBuilder setMeals(String meals)
    {
        this.meals = meals;
        return this;
    }

    public VacationPackageBuilder setAttractions(String attractions)
    {
        this.attractions = attractions;
        return this;
    }

    public VacationPackage build()
    {
        return new VacationPackage(flight,hotel,carRental,meals,attractions);
    }



}
