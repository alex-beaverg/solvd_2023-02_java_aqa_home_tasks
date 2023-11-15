package com.solvd.hospital_project.hospital.structure;

import com.solvd.hospital_project.hospital.people.Employee;

import java.util.List;

public interface IGetEmployeesBySomething {
    List<Employee> getEmployeesBySpecialistClass(int specialistClass);
}
