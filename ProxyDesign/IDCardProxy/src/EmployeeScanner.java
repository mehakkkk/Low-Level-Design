public class EmployeeScanner {
    public static void main(String[] args) {
        IEmployee employee1 = new EmployeeProxy(new IDCard("Mehak","92739202","MI145"));
        System.out.println(employee1.getName());

        IEmployee employee2 = new EmployeeProxy(new IDCard("Riya","3802810","MT145"));
        employee2.getName();

    }
}
