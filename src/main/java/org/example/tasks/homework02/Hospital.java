package org.example.tasks.homework02;

public class Hospital {
    private String hospitalTitle;
    private Department[] departments;
    private Employee[] employees;
    private Patient[] patients;

    public Hospital(String hospitalTitle) {
        this.hospitalTitle = hospitalTitle;
    }

    public String getHospitalTitle() {
        return hospitalTitle;
    }

    public void setHospitalTitle(String hospitalTitle) {
        this.hospitalTitle = hospitalTitle;
    }

    public Department[] getDepartments() {
        return departments;
    }

    public void setDepartments(Department[] departments) {
        this.departments = departments;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public Patient[] getPatients() {
        return patients;
    }

    public void setPatients(Patient[] patients) {
        this.patients = patients;
    }
}
