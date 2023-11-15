package com.solvd.hospital_project.hospital.people;

import com.solvd.hospital_project.hospital.structure.accounting.Accounting;
import com.solvd.hospital_project.hospital.structure.Service;
import com.solvd.hospital_project.hospital.structure.Department;
import com.solvd.hospital_project.hospital.structure.VipService;
import com.solvd.hospital_project.hospital.util.my_linked_list.MyLinkedList;

import java.util.*;

public class Patient extends Person implements IAddServices {
    private List<Diagnosis> diagnoses;
    private Department department;
    private Employee doctor;
    private Employee nurse;
    private final Set<Service> services;
    private final MyLinkedList<VipService> vipServices;
    private double servicesPrice;
    private double vipServicesPrice;

    {
        diagnoses = new ArrayList<>();
        services = new LinkedHashSet<>();
        vipServices = new MyLinkedList<>();
    }

    public Patient() {
        super();
    }

    // Overloading:
    public Patient(String firstName,
                   String lastName,
                   int age,
                   Address address,
                   List<Diagnosis> diagnoses,
                   Department department) {
        super(firstName, lastName, age, address);
        this.diagnoses = diagnoses;
        this.department = department;
    }

    public List<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public Set<Service> getServices() {
        return services;
    }

    public MyLinkedList<VipService> getVipServices() {
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

    public void addDiagnosis(Diagnosis diagnosis) {
        if (!diagnoses.contains(diagnosis)) {
            diagnoses.add(diagnosis);
        }
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
        return firstName + " " + lastName + " (" + combineDiagnoses() + ", " + department.getTitle() + ")";
    }

    private String combineDiagnoses() {
        StringBuilder combiningDiagnoses = new StringBuilder();
        for (Diagnosis diagnosis: diagnoses) {
            combiningDiagnoses.append("[").append(diagnosis.getTitle()).append("] ");
        }
        return combiningDiagnoses.toString().trim();
    }

    private String combineServices() {
        StringBuilder combiningServices = new StringBuilder();
        for (Service service: services) {
            combiningServices.append("[").append(service.getTitle()).append("] ");
        }
        return combiningServices.toString().trim();
    }

    private String combineVipServices() {
        StringBuilder combiningVipServices = new StringBuilder();
        for (VipService vipService: vipServices) {
            combiningVipServices.append("[").append(vipService.getTitle()).append("] ");
        }
        return combiningVipServices.toString().trim();
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
        if ((int) servicesPrice != (int) that.servicesPrice) return false;
        if ((int) vipServicesPrice != (int) that.vipServicesPrice) return false;
        return age == that.age;
    }

    @Override
    public String toString() {
        return "Patient: (" + getRole() + "): " +
                super.toString() +
                "\n\tDiagnoses: " + combineDiagnoses() +
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
