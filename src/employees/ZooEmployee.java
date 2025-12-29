package employees;

public abstract class ZooEmployee implements Employee {
    protected String name;
    protected String position;
    protected String employeeId;

    public ZooEmployee(String name, String position, String employeeId) {
        this.name = name;
        this.position = position;
        this.employeeId = employeeId;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getPosition() { return position; }

    @Override
    public String getEmployeeId() { return employeeId; }

    public abstract void performDuties();
}