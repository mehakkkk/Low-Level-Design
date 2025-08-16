public enum Currency {
    USD(3),
    INR(1.2),
    JPY(0.8),
    DONG(0.5);

    double value;
    Currency(double value)
    {
        this.value = value;
    }

    public double getConversionValue(Currency toCurrency)
    {
        return this.value/toCurrency.value;
    }
}
