public enum VehicleType {
    PRIME(40),
    SEDAN(90),
    PREMIUM(110),
    XUV(160);
    int value;
    VehicleType(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }

}
