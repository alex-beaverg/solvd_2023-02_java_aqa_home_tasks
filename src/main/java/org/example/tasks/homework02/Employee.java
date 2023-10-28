package org.example.tasks.homework02;

public class Employee {
    private String firstName;
    private String lastName;
    private Address address;
    private Department department;
    private Position position;
    private Schedule schedule;

    public Employee(String firstName, String lastName, Address address, Department department, Position position,
                    Schedule schedule) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.department = department;
        this.position = position;
        this.schedule = schedule;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
