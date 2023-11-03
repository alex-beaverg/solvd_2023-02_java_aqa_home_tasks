package org.example.hospital.people;

import org.example.hospital.structure.Accounting;
import org.example.hospital.structure.Department;

import java.util.Objects;

public class Employee extends Person {
    private Department department;
    private Position position;
    private Schedule schedule;
    private double salary;
    private double costOfServices;

    public Employee(String firstName,
                    String lastName,
                    int age,
                    Address address,
                    Department department,
                    Position position,
                    Schedule schedule) {
        super(firstName, lastName, age, address);
        this.department = department;
        this.position = position;
        this.schedule = schedule;
        costOfServices = 0;
        salary = Accounting.calculateSalary(this.position, costOfServices);
    }

    public void increaseCostOfServices(double costOfService) {
        costOfServices += costOfService;
        salary = Accounting.calculateSalary(this.position, costOfServices);
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String getRole() {
        return "Works in a hospital";
    }

    @Override
    public int hashCode() {
        int result = firstName == null ? 0 : firstName.hashCode();
        result = 31 * result + (lastName == null ? 0 : lastName.hashCode());
        result = 31 * result + age;
        result = 31 * result + (address == null ? 0 : address.hashCode());
        result = 31 * result + (department == null ? 0 : department.hashCode());
        result = 31 * result + (position == null ? 0 : position.hashCode());
        result = 31 * result + (schedule == null ? 0 : schedule.hashCode());
        result = 31 * result + (int) salary;
        result = 31 * result + (int) costOfServices;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee that = (Employee) obj;
        if (!Objects.equals(firstName, that.firstName)) return false;
        if (!Objects.equals(lastName, that.lastName)) return false;
        if (!Objects.equals(address, that.address)) return false;
        if (!Objects.equals(department, that.department)) return false;
        if (!Objects.equals(position, that.position)) return false;
        if (!Objects.equals(schedule, that.schedule)) return false;
        if ((int) salary != (int) that.salary) return false;
        if ((int) costOfServices != (int) that.costOfServices) return false;
        return age == that.age;
    }

    @Override
    public String toString() {
        return "\nEmployee (" + this.getRole() + "): " +
                super.toString() +
                "\n\tDepartment: " + department.getDepartmentTitle() +
                "\n\tPosition: " + position +
                "\n\tSchedule: " + schedule +
                "\n\tSalary: " + Math.ceil(salary) + " BYN";
    }
}
