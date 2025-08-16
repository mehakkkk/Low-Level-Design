public class Driver extends User {
    private String vehicleNumber;
    private VehicleType type;
    private String license;

    public Driver(String name, String number, String email,String vehicleNumber,VehicleType vehicleType,String license){
        super(name,number,email);
        this.vehicleNumber = vehicleNumber;
        this.type = vehicleType;
        this.license = license;

    }

    public boolean equals(Object driver)
    {
        return this.license == ((Driver) driver).getLicense() && this.getId() == ((Driver) driver).getId();
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
