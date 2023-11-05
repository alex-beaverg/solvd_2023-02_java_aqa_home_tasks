package org.example.hospital.people;

import org.example.hospital.structure.Accounting;
import org.example.hospital.structure.Service;
import org.example.hospital.structure.Department;
import org.example.hospital.structure.VipService;

import java.util.ArrayList;
import java.util.Objects;

public class Patient extends Person implements ICombineServices, IAddServices {
    private Diagnosis diagnosis;
    private Department department;
    private Employee therapist;
    private Employee nurse;
    private final ArrayList<Service> services = new ArrayList<>();
    private final ArrayList<VipService> vipServices = new ArrayList<>();
    private double servicesPrice;
    private double vipServicesPrice;

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

    public ArrayList<Service> getServices() {
        return services;
    }

    public ArrayList<VipService> getVipServices() {
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

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee getTherapist() {
        return therapist;
    }

    public void setTherapist(Employee therapist) {
        this.therapist = therapist;

    }

    public void setNurse(Employee nurse) {
        this.nurse = nurse;
    }

    @Override
    public String getPersonToPrintInList() {
        return firstName + " " + lastName + " (" + diagnosis.getTitle() + ", " + department.getTitle() + ")";
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
        try {
            return "Patient: (" + getRole() + "): " +
                    super.toString() +
                    "\n\tDiagnosis: " + diagnosis.getTitle() +
                    "\n\tDepartment: " + department.getTitle() +
                    "\n\tOffice: " + department.getOfficeNumber() +
                    "\n\tTherapist: " + therapist.getFirstName() + " " + therapist.getLastName() + " (" + therapist.getPosition().getTitle() + ")" +
                    "\n\tNurse: " + nurse.getFirstName() + " " + nurse.getLastName() +
                    "\n\tServices: " + combineServices() +
                    "\n\tPrice: " + servicesPrice + " BYN" +
                    "\n\tVIP services: " + combineVipServices() +
                    "\n\tVIP price: " + vipServicesPrice + " BYN" +
                    "\n\t------" +
                    "\n\tTotal price: " + (servicesPrice + vipServicesPrice) + " BYN";
        } catch (NullPointerException e) {
            return "\nThe patient was not registered";
        }
    }
}
