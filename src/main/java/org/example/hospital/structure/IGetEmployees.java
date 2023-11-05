package org.example.hospital.structure;

import org.example.hospital.people.Employee;

import java.util.ArrayList;

public interface IGetEmployees {
    ArrayList<Employee> getEmployeesBySpecialistClass(int specialistClass);
}
