package org.example.hospital.structure;

import org.example.hospital.people.Employee;
import org.example.hospital.people.Patient;

public final class Accounting {
    private static final double BASE_SALARY;
    private static final double TAX;

    static {
        BASE_SALARY = 1503.5;
        TAX = 0.2;
    }

    public static double calculateEmployeeSalary(Employee employee) {
        double serviceAllowance = (employee.getServicesPrice() + employee.getVipServicesPrice()) / 6;
        double specialistClass = employee.getPosition().getSpecialistClass();
        double categoryCoefficient = categoryCoefficient(employee.getPosition().getCategory());
        double salaryWithoutTax = (BASE_SALARY + serviceAllowance) * specialistClass * categoryCoefficient;
        return salaryWithoutTax - salaryWithoutTax * TAX;
    }

    public static double calculateServicesPrice(Patient patient) {
        double servicesPrice = 0;
        double serviceCoefficient = patient.getTherapist().getPosition().getServiceCoefficient();
        for (Service service: patient.getServices()) {
            servicesPrice += service.getPrice() * serviceCoefficient;
        }
        return servicesPrice;
    }

    // Overloading:
    public static double calculateServicesPrice(Employee employee) {
        double servicesPrice = 0;
        double serviceCoefficient = employee.getPosition().getServiceCoefficient();
        for (Service service: employee.getServices()) {
            servicesPrice += service.getPrice() * serviceCoefficient;
        }
        return servicesPrice;
    }

    public static double calculateVipServicesPrice(Patient patient) {
        double vipServicesPrice = 0;
        double serviceCoefficient = patient.getTherapist().getPosition().getServiceCoefficient();
        for (VipService vipService: patient.getVipServices()) {
            vipServicesPrice += vipService.getPrice() * serviceCoefficient;
        }
        return vipServicesPrice;
    }

    // Overloading:
    public static double calculateVipServicesPrice(Employee employee) {
        double vipServicesPrice = 0;
        double serviceCoefficient = employee.getPosition().getServiceCoefficient();
        for (VipService vipService: employee.getVipServices()) {
            vipServicesPrice += vipService.getPrice() * serviceCoefficient;
        }
        return vipServicesPrice;
    }

    private static double categoryCoefficient(int category) {
        return switch (category) {
            case (1) -> 1.3;
            case (2) -> 1.6;
            default -> 1.9;
        };
    }
}
