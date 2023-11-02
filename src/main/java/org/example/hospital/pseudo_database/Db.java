package org.example.hospital.pseudo_database;

import org.example.hospital.people.*;
import org.example.hospital.structure.*;

/**
 * Class for creating pseudo database
 */
public class Db {
    public static Hospital hospital;
    public static Department emergencyDept;
    public static Department therapeuticDept;
    public static Department surgeryDept;
    public static Department xRayDept;
    public static Department[] departments;
    public static Employee johnSmith;
    public static Employee alexWhite;
    public static Employee samGray;
    public static Employee jackBlack;
    public static Employee emmaGreet;
    public static Employee bobTorn;
    public static Employee jamesBrown;
    public static Employee karinaGold;
    public static Employee helgaMoon;
    public static Employee steeveHawk;
    public static Employee jennyJoy;
    public static Employee kellyHorn;
    public static Employee[] hospitalEmployees;
    public static Employee[] therapeuticDeptEmployees;
    public static Employee[] surgeryDeptEmployees;
    public static Employee[] emergencyDeptEmployees;
    public static Employee[] xRayDeptEmployees;


    public static void createDb() {
        // Hospital:
        hospital = new Hospital("City Hospital");

        // Departments:
        emergencyDept = new Department("Emergency department", 102);
        therapeuticDept = new Department("Therapeutic department", 103);
        surgeryDept = new Department("Surgery department", 202);
        xRayDept = new Department("X-Ray department", 203);

        // Department list:
        departments = new Department[]{emergencyDept, therapeuticDept, surgeryDept, xRayDept};
        hospital.setDepartments(departments);

        // Employees:
        johnSmith = new Employee("John", "Smith", 55,
                new Address("Minsk", "Old str.", 5, 100),
                therapeuticDept, Position.DEPT_HEAD, Schedule.BEFORE_NOON_EVEN_DAYS);
        alexWhite = new Employee("Alex", "White", 51,
                new Address("Minsk", "New str.", 10, 215),
                surgeryDept, Position.DEPT_HEAD, Schedule.BEFORE_NOON_ODD_DAYS);
        samGray = new Employee("Sam", "Gray", 45,
                new Address("Minsk", "Old str.", 12, 33),
                emergencyDept, Position.DEPT_HEAD, Schedule.AFTERNOON_EVEN_DAYS);
        jackBlack = new Employee("Jack", "Black", 40,
                new Address("Minsk", "Green str.", 22, 45),
                xRayDept, Position.DEPT_HEAD, Schedule.AFTERNOON_ODD_DAYS);
        emmaGreet = new Employee("Emma", "Greet", 29,
                new Address("Minsk", "Yellow str.", 1, 1),
                therapeuticDept, Position.DOCTOR, Schedule.AFTERNOON_EVEN_DAYS);
        bobTorn = new Employee("Bob", "Torn", 31,
                new Address("Minsk", "Green str.", 31, 75),
                surgeryDept, Position.DOCTOR, Schedule.AFTERNOON_ODD_DAYS);
        jamesBrown = new Employee("James", "Brown", 30,
                new Address("Minsk", "New str.", 15, 42),
                emergencyDept, Position.DOCTOR, Schedule.BEFORE_NOON_EVEN_DAYS);
        karinaGold = new Employee("Karina", "Gold", 33,
                new Address("Minsk", "Red str.", 11, 23),
                xRayDept, Position.DOCTOR, Schedule.BEFORE_NOON_ODD_DAYS);
        helgaMoon = new Employee("Helga", "Moon", 25,
                new Address("Minsk", "Green str.", 32, 56),
                therapeuticDept, Position.NURSE, Schedule.BEFORE_NOON_ODD_DAYS);
        steeveHawk = new Employee("Steeve", "Hawk", 24,
                new Address("Minsk", "Red str.", 2, 7),
                surgeryDept, Position.NURSE, Schedule.BEFORE_NOON_EVEN_DAYS);
        jennyJoy = new Employee("Jenny", "Joy", 27,
                new Address("Minsk", "Yellow str.", 15, 12),
                emergencyDept, Position.NURSE, Schedule.AFTERNOON_ODD_DAYS);
        kellyHorn = new Employee("Kelly", "Horn", 25,
                new Address("Minsk", "Black str.", 12, 66),
                xRayDept, Position.NURSE, Schedule.AFTERNOON_EVEN_DAYS);

        // Employee lists:
        hospitalEmployees = new Employee[]{johnSmith, alexWhite, samGray, jackBlack, emmaGreet, bobTorn,
                jamesBrown, karinaGold, helgaMoon, steeveHawk, jennyJoy, kellyHorn};
        hospital.setEmployees(hospitalEmployees);
        therapeuticDeptEmployees = new Employee[]{johnSmith, emmaGreet, helgaMoon};
        therapeuticDept.setEmployees(therapeuticDeptEmployees);
        surgeryDeptEmployees = new Employee[]{alexWhite, bobTorn, steeveHawk};
        surgeryDept.setEmployees(surgeryDeptEmployees);
        emergencyDeptEmployees = new Employee[]{samGray, jamesBrown, jennyJoy};
        emergencyDept.setEmployees(emergencyDeptEmployees);
        xRayDeptEmployees = new Employee[]{jackBlack, karinaGold, kellyHorn};
        xRayDept.setEmployees(xRayDeptEmployees);
    }
}
