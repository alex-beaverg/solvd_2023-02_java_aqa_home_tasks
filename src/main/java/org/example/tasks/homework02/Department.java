package org.example.tasks.homework02;

public class Department {
    private String departmentTitle;
    private OfficeRoom officeRoom;
    private Employee[] employees;
    private Patient[] patients;

    public Department(String departmentTitle, OfficeRoom officeRoom) {
        this.departmentTitle = departmentTitle;
        this.officeRoom = officeRoom;
    }

    public String getDepartmentTitle() {
        return departmentTitle;
    }

    public void setDepartmentTitle(String departmentTitle) {
        this.departmentTitle = departmentTitle;
    }

    public OfficeRoom getOfficeRoom() {
        return officeRoom;
    }

    public void setOfficeRoom(OfficeRoom officeRoom) {
        this.officeRoom = officeRoom;
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
