package org.example.hospital.structure;

import org.example.hospital.people.Employee;

import java.util.List;

public interface IGetEmployeesBySomething {
    List<Employee> getEmployeesBySpecialistClass(int specialistClass);
}
