public class Employee implements IEmployee {
    private String name;
    private String number;
    private String bloodGroup;
    private long salary;

    public Employee(String name, String number, String bloodGroup, long salary) {
        this.name = name;
        this.number = number;
        this.bloodGroup = bloodGroup;
        this.salary = salary;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public String getBloodGroup() {
        return bloodGroup;
    }

    @Override
    public long getSalary() {
        return salary;
    }
}
