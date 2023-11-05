package org.example.hospital.people;

import org.example.hospital.structure.*;

import java.util.ArrayList;
import java.util.Objects;

public class Employee extends Person implements ICombineServices, IAddPatients, IAddServices {
    private final Department department;
    private final Position position;
    private final Schedule schedule;
    private double salary;
    private final ArrayList<Service> services = new ArrayList<>();
    private final ArrayList<VipService> vipServices = new ArrayList<>();
    private double servicesPrice;
    private double vipServicesPrice;
    private final ArrayList<Patient> patients = new ArrayList<>();

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
        servicesPrice = 0;
        vipServicesPrice = 0;
        salary = Accounting.calculateEmployeeSalary(this);
    }

    public Department getDepartment() {
        return department;
    }

    public double getServicesPrice() {
        return servicesPrice;
    }

    public double getVipServicesPrice() {
        return vipServicesPrice;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public ArrayList<VipService> getVipServices() {
        return vipServices;
    }

    @Override
    public void addService(Service service) {
        this.services.add(service);
        servicesPrice = Accounting.calculateServicesPrice(this);
        salary = Accounting.calculateEmployeeSalary(this);
    }

    @Override
    public void addVipService(VipService vipService) {
        this.vipServices.add(vipService);
        vipServicesPrice = Accounting.calculateVipServicesPrice(this);
        salary = Accounting.calculateEmployeeSalary(this);
    }

    @Override
    public void addPatient(Patient patient) {
        this.patients.add(patient);
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String getPersonToPrintInList() {
        return firstName + " " + lastName + " (" + position.getTitle() + ", " + department.getTitle() + ")";
    }

    private StringBuilder combinePatients() {
        StringBuilder combiningPatients = new StringBuilder();
        for (Patient patient: patients) {
            combiningPatients.append("[").append(patient.getFirstName()).append(" ").append(patient.getLastName()).append("] ");
        }
        return combiningPatients;
    }

    @Override
    public StringBuilder combineServices() {
        StringBuilder combiningServices = new StringBuilder();
        for (Service service: services) {
            combiningServices.append("[").append(service.getTitle()).append("] ");
        }
        return combiningServices;
    }

    @Override
    public StringBuilder combineVipServices() {
        StringBuilder combiningVipServices = new StringBuilder();
        for (VipService vipService: vipServices) {
            combiningVipServices.append("[").append(vipService.getTitle()).append("] ");
        }
        return combiningVipServices;
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
        result = 31 * result + (int) servicesPrice;
        result = 31 * result + (int) vipServicesPrice;
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
        if ((int) servicesPrice != (int) that.servicesPrice) return false;
        if ((int) vipServicesPrice != (int) that.vipServicesPrice) return false;
        return age == that.age;
    }

    @Override
    public String toString() {
        return "\nEmployee (" + getRole() + "): " +
                super.toString() +
                "\n\tDepartment: " + department.getTitle() +
                "\n\tPosition: " + position +
                "\n\tSchedule: " + schedule +
                "\n\tPatients: " + combinePatients() +
                "\n\tServices: " + combineServices() +
                "\n\tVIP services: " + combineVipServices() +
                "\n\tSalary: " + Math.ceil(salary) + " BYN";
    }
}
