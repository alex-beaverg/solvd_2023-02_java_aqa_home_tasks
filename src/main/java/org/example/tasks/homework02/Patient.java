package org.example.tasks.homework02;

public class Patient {
    private String firstName;
    private String lastName;
    private Address address;
    private Department department;

    public Patient(Department department) {
        this.department = department;
    }

    public Patient(String firstName, String lastName, Address address, Department department) {
        this(department);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
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
}
