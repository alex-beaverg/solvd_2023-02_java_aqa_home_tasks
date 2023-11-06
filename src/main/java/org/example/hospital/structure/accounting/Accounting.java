package org.example.hospital.structure.accounting;

import org.example.hospital.people.Employee;
import org.example.hospital.people.Patient;
import org.example.hospital.structure.Service;
import org.example.hospital.structure.VipService;

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
        double categoryCoefficient = getCategoryCoefficient(employee.getPosition().getCategory());
        double salaryWithoutTax = (BASE_SALARY + serviceAllowance) * specialistClass * categoryCoefficient;
        return salaryWithoutTax - salaryWithoutTax * TAX;
    }

    public static double calculateServicesPrice(Patient patient) {
        double servicesPrice = 0;
        int specialistClass = patient.getDoctor().getPosition().getSpecialistClass();
        int category = patient.getDoctor().getPosition().getCategory();
        double serviceCoefficient = getServiceCoefficient(specialistClass, category);
        for (Service service: patient.getServices()) {
            servicesPrice += service.getPrice() * serviceCoefficient;
        }
        return servicesPrice;
    }

    // Overloading:
    public static double calculateServicesPrice(Employee employee) {
        double servicesPrice = 0;
        int specialistClass = employee.getPosition().getSpecialistClass();
        int category = employee.getPosition().getCategory();
        double serviceCoefficient = getServiceCoefficient(specialistClass, category);
        for (Service service: employee.getServices()) {
            servicesPrice += service.getPrice() * serviceCoefficient;
        }
        return servicesPrice;
    }

    public static double calculateVipServicesPrice(Patient patient) {
        double vipServicesPrice = 0;
        int specialistClass = patient.getDoctor().getPosition().getSpecialistClass();
        int category = patient.getDoctor().getPosition().getCategory();
        double serviceCoefficient = getServiceCoefficient(specialistClass, category);
        for (VipService vipService: patient.getVipServices()) {
            vipServicesPrice += vipService.getPrice() * serviceCoefficient;
        }
        return vipServicesPrice;
    }

    // Overloading:
    public static double calculateVipServicesPrice(Employee employee) {
        double vipServicesPrice = 0;
        int specialistClass = employee.getPosition().getSpecialistClass();
        int category = employee.getPosition().getCategory();
        double serviceCoefficient = getServiceCoefficient(specialistClass, category);
        for (VipService vipService: employee.getVipServices()) {
            vipServicesPrice += vipService.getPrice() * serviceCoefficient;
        }
        return vipServicesPrice;
    }

    private static double getCategoryCoefficient(int category) {
        return switch (category) {
            case (1) -> CategoryCoefficient.CAT1_COEF.getValue();
            case (2) -> CategoryCoefficient.CAT2_COEF.getValue();
            default -> CategoryCoefficient.CAT3_COEF.getValue();
        };
    }

    private static double getServiceCoefficient(int specialistClass, int category) {
        double serviceCoefficient;
        if (specialistClass == 1) {
            switch (category) {
                case (1) -> serviceCoefficient = ServiceCoefficient.SC1_CAT1_COEF.getValue();
                case (2) -> serviceCoefficient = ServiceCoefficient.SC1_CAT2_COEF.getValue();
                default -> serviceCoefficient = ServiceCoefficient.SC1_CAT3_COEF.getValue();
            }
        } else if (specialistClass == 2) {
            switch (category) {
                case (1) -> serviceCoefficient = ServiceCoefficient.SC2_CAT1_COEF.getValue();
                case (2) -> serviceCoefficient = ServiceCoefficient.SC2_CAT2_COEF.getValue();
                default -> serviceCoefficient = ServiceCoefficient.SC2_CAT3_COEF.getValue();
            }
        } else {
            switch (category) {
                case (1) -> serviceCoefficient = ServiceCoefficient.SC3_CAT1_COEF.getValue();
                case (2) -> serviceCoefficient = ServiceCoefficient.SC3_CAT2_COEF.getValue();
                default -> serviceCoefficient = ServiceCoefficient.SC3_CAT3_COEF.getValue();
            }
        }
        return serviceCoefficient;
    }
}
