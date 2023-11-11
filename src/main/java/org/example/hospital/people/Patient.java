package org.example.hospital.people;

import org.example.hospital.structure.accounting.Accounting;
import org.example.hospital.structure.Service;
import org.example.hospital.structure.Department;
import org.example.hospital.structure.VipService;

import java.util.*;

public class Patient extends Person implements IAddServices {
    private Diagnosis diagnosis;
    private Department department;
    private Employee doctor;
    private Employee nurse;
    private final List<Service> services;
    private final List<VipService> vipServices;
    private double servicesPrice;
    private double vipServicesPrice;

    {
        services = new ArrayList<>();
        vipServices = new ArrayList<>();
    }

    public Patient() {
        super();
    }

    // Overloading:
    public Patient(String firstName,
                   String lastName,
                   int age,
                   Address address,
                   Diagnosis diagnosis,
                   Department department) {
        super(firstName, lastName, age, address);
        this.diagnosis = diagnosis;
        this.department = department;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public List<Service> getServices() {
        return services;
    }

    public List<VipService> getVipServices() {
        return vipServices;
    }

    @Override
    public void addService(Service service) {
        services.add(service);
        servicesPrice = Accounting.calculateServicesPrice(this);
    }

    @Override
    public void addVipService(VipService vipService) {
        vipServices.add(vipService);
        vipServicesPrice = Accounting.calculateVipServicesPrice(this);
    }

    public void deleteVipService(VipService vipService) {
        vipServices.remove(vipService);
        vipServicesPrice = Accounting.calculateVipServicesPrice(this);
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee getDoctor() {
        return doctor;
    }

    public void setDoctor(Employee doctor) {
        this.doctor = doctor;
        servicesPrice = Accounting.calculateServicesPrice(this);
        vipServicesPrice = Accounting.calculateVipServicesPrice(this);
    }

    public void setNurse(Employee nurse) {
        this.nurse = nurse;
    }

    @Override
    public String getPersonToPrintInList() {
        return firstName + " " + lastName + " (" + diagnosis.getTitle() + ", " + department.getTitle() + ")";
    }

    private StringBuilder combineServices() {
        StringBuilder combiningServices = new StringBuilder();
        for (Service service: services) {
            combiningServices.append("[").append(service.getTitle()).append("] ");
        }
        return combiningServices;
    }

    private StringBuilder combineVipServices() {
        StringBuilder combiningVipServices = new StringBuilder();
        for (VipService vipService: vipServices) {
            combiningVipServices.append("[").append(vipService.getTitle()).append("] ");
        }
        return combiningVipServices;
    }

    @Override
    public String getRole() {
        return "Receiving treatment in hospital";
    }

    @Override
    public int hashCode() {
        int result = firstName == null ? 0 : firstName.hashCode();
        result = 31 * result + (lastName == null ? 0 : lastName.hashCode());
        result = 31 * result + age;
        result = 31 * result + (address == null ? 0 : address.hashCode());
        result = 31 * result + (department == null ? 0 : department.hashCode());
        result = 31 * result + (diagnosis == null ? 0 : diagnosis.hashCode());
        result = 31 * result + services.hashCode();
        result = 31 * result + vipServices.hashCode();
        result = 31 * result + (int) servicesPrice;
        result = 31 * result + (int) vipServicesPrice;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Patient that = (Patient) obj;
        if (!Objects.equals(firstName, that.firstName)) return false;
        if (!Objects.equals(lastName, that.lastName)) return false;
        if (!Objects.equals(address, that.address)) return false;
        if (!Objects.equals(department, that.department)) return false;
        if (!Objects.equals(diagnosis, that.diagnosis)) return false;
        if (!Objects.equals(services, that.services)) return false;
        if (!Objects.equals(vipServices, that.vipServices)) return false;
        if ((int) servicesPrice != (int) that.servicesPrice) return false;
        if ((int) vipServicesPrice != (int) that.vipServicesPrice) return false;
        return age == that.age;
    }

    @Override
    public String toString() {
        return "Patient: (" + getRole() + "): " +
                super.toString() +
                "\n\tDiagnosis: " + diagnosis.getTitle() +
                "\n\tDepartment: " + department.getTitle() +
                "\n\tOffice: " + department.getOfficeNumber() +
                "\n\tDoctor: " + (doctor != null ? doctor.getFullName() + " (" + doctor.getPosition().getTitle() + ")" : null) +
                "\n\tNurse: " + (nurse != null ? nurse.getFullName() : null) +
                "\n\tServices: " + combineServices() +
                "\n\tPrice: " + Math.ceil(servicesPrice * 100) / 100 + " BYN" +
                "\n\tVIP services: " + combineVipServices() +
                "\n\tVIP price: " + Math.ceil(vipServicesPrice * 100) / 100 + " BYN" +
                "\n\t------" +
                "\n\tTotal price: " + Math.ceil((servicesPrice + vipServicesPrice) * 100) / 100 + " BYN";
    }
}
