package com.solvd.hospital_project.hospital.structure.accounting;

import com.solvd.hospital_project.hospital.people.Employee;
import com.solvd.hospital_project.hospital.people.Patient;
import com.solvd.hospital_project.hospital.structure.Service;
import com.solvd.hospital_project.hospital.structure.VipService;

import java.util.function.BiFunction;

public final class Accounting {
    private static final double BASE_SALARY;
    private static final double TAX;

    static {
        BASE_SALARY = 1503.5;
        TAX = 0.2;
    }

    public static double calculateEmployeeSalary(Employee employee) {
        BiFunction<Double, Double, Double> calculateServiceAllowance = (sP, vipSP) -> (sP + vipSP) / 6;
        double serviceAllowance = calculateServiceAllowance.apply(employee.getServicesPrice(), employee.getVipServicesPrice());
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

    public static String getPayslip(Employee employee) {
        int specialistClass = employee.getPosition().getSpecialistClass();
        int category = employee.getPosition().getCategory();
        double serviceCoefficient = getServiceCoefficient(specialistClass, category);
        double serviceAllowance = employee.getServicesPrice() / 6;
        double vipServiceAllowance = employee.getVipServicesPrice() / 6;
        double categoryCoefficient = getCategoryCoefficient(employee.getPosition().getCategory());
        double salaryWithoutTax = (BASE_SALARY + serviceAllowance + vipServiceAllowance) * specialistClass * categoryCoefficient;
        return "Doctor's payslip:" +
                "\n\tFull name: " + employee.getFullName() +
                "\n\tPosition: " + employee.getPosition().getTitle() +
                "\n\tSpecialist class: " + employee.getPosition().getSpecialistClass() +
                "\n\tCategory: " + employee.getPosition().getCategory() +
                "\n\tBase salary: " + BASE_SALARY + " BYN" +
                "\n\tNumber of patients: " + employee.getPatients().size() +
                "\n\tService coefficient: " + serviceCoefficient +
                "\n\tServices allowance: " + Math.ceil(serviceAllowance * 100) / 100 + " BYN" +
                "\n\tVIP services allowance: " + Math.ceil(vipServiceAllowance * 100) / 100 + " BYN" +
                "\n\tTotal services allowance: " + Math.ceil((serviceAllowance + vipServiceAllowance) * 100) / 100 + " BYN" +
                "\n\tCategory coefficient: " + categoryCoefficient +
                "\n\tSalary without tax: " + Math.ceil(salaryWithoutTax * 100) / 100 + " BYN" +
                "\n\tTax: " + (TAX * 100) + " %" +
                "\n\t------" +
                "\n\tSalary: " + Math.ceil(calculateEmployeeSalary(employee) * 100) / 100 + " BYN";
    }
}
