//Copy constructor allows deep cloning but the only drawback is for each every class
// the constructor needs to be added

public class CopyConstructor {
    public static void main(String[] args) {
        Employee e1 = new Employee("Gouri",new EmployeeAddress("FC Road","Pune",43302));
        Employee e2 = new Employee(e1);
        e2.name="Mehak";
        e2.address.city="Nashik";

        System.out.println(e1);
        System.out.println(e2);
    }
}


class Employee{
    String name;
    EmployeeAddress address;

    public Employee(String name, EmployeeAddress address) {
        this.name = name;
        this.address = address;
    }

    public Employee(Employee employee)
    {
        this(employee.name,new EmployeeAddress(employee.address));
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

class EmployeeAddress{
    String street;
    String city;
    int pincode;

    public EmployeeAddress(String street, String city, int pincode) {
        this.street = street;
        this.city = city;
        this.pincode = pincode;
    }

    public EmployeeAddress(EmployeeAddress employeeAddress)
    {
        this(employeeAddress.street,employeeAddress.city,employeeAddress.pincode);
    }

    @Override
    public String toString() {
        return "EmployeeAddress{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", pincode=" + pincode +
                '}';
    }
}