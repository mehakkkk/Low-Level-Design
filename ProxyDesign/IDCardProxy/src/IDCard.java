public class IDCard {
    private String name;
    private String number;
    private String empKey;

    public IDCard(String name,String number,String empKey)
    {
        this.name = name;
        this.number = number;
        this.empKey = empKey;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getEmpKey() {
        return empKey;
    }
}
