public class EmployeeProxy implements IEmployee {
    private IDCard card;
    private Employee realEmployee;

    public EmployeeProxy(IDCard card) {
        this.card = card;
    }

    @Override
    public String getName() {
        return getEmployee().getName();
    }

    @Override
    public String getNumber() {
        return getEmployee().getNumber();
    }

    @Override
    public String getBloodGroup() {
        return getEmployee().getBloodGroup();
    }

    @Override
    public long getSalary() {
        return getEmployee().getSalary();
    }

    // Private method to handle employee retrieval and validation
    private Employee getEmployee() {
        if (realEmployee == null) {
            if (isValidEmployee(card)) {
                // Retrieve or create the real employee data
                realEmployee = new Employee("John Doe", "12345", "O+", 50000); // Example data
            } else {
                throw new RuntimeException("Not a valid employee");
            }
        }
        return realEmployee;
    }

    private boolean isValidEmployee(IDCard card) {
        return card.getEmpKey().startsWith("MI");
    }
}
