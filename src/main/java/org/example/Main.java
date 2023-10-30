package org.example;

import org.example.tasks.HomeworksExecutor;
import org.example.tasks.homework02.*;

/**
 * Entry point
 */
public class Main {
    public static void main(String[] args) {

        // Homework 01 (31.10.2023):
        HomeworksExecutor.executeHW01();

        // Homework 02 (31.10.2023):
        // Hospital:
        Hospital hospital = new Hospital("City Hospital");
        // Floors:
        Floor firstFloor = new Floor(1);
        Floor secondFloor = new Floor(2);
        // Office rooms:
        OfficeRoom emergencyRoom = new OfficeRoom(102, firstFloor);
        OfficeRoom therapeuticRoom = new OfficeRoom(103, firstFloor);
        OfficeRoom surgeryRoom = new OfficeRoom(202, secondFloor);
        OfficeRoom xRayRoom = new OfficeRoom(203, secondFloor);
        // Office room lists:
        OfficeRoom[] firstFloorOfficeRooms = new OfficeRoom[] {emergencyRoom, therapeuticRoom};
        firstFloor.setOfficeRooms(firstFloorOfficeRooms);
        OfficeRoom[] secondFloorOfficeRooms = new OfficeRoom[] {surgeryRoom, xRayRoom};
        secondFloor.setOfficeRooms(secondFloorOfficeRooms);
        // Departments:
        Department emergencyDepartment = new Department("Emergency department", emergencyRoom);
        Department therapeuticDepartment = new Department("Therapeutic department", therapeuticRoom);
        Department surgeryDepartment = new Department("Surgery department", surgeryRoom);
        Department xRayDepartment = new Department("X-Ray department", xRayRoom);
        // Department list:
        Department[] departments = new Department[] {emergencyDepartment, therapeuticDepartment,
                surgeryDepartment, xRayDepartment};
        hospital.setDepartments(departments);
        // Positions:
        Position departmentHead = new Position("Department head");
        Position doctor = new Position("Doctor");
        Position nurse = new Position("Nurse");
        // Schedules:
        Schedule beforeNoonEven = new Schedule("Before noon", "Even days");
        Schedule beforeNoonOdd = new Schedule("Before noon", "Odd days");
        Schedule afternoonEven = new Schedule("Afternoon", "Even days");
        Schedule afternoonOdd = new Schedule("Afternoon", "Odd days");
        // Employees:
        Employee johnSmith = new Employee("John", "Smith",
                new Address("Minsk", "Old str.", 5, 100),
                therapeuticDepartment, departmentHead, beforeNoonEven);
        Employee alexWhite = new Employee("Alex", "White",
                new Address("Minsk", "New str.", 10, 215),
                surgeryDepartment, departmentHead, beforeNoonOdd);
        Employee samGray = new Employee("Sam", "Gray",
                new Address("Minsk", "Old str.", 12, 33),
                emergencyDepartment, departmentHead, afternoonEven);
        Employee jackBlack = new Employee("Jack", "Black",
                new Address("Minsk", "Green str.", 22, 45),
                xRayDepartment, departmentHead, afternoonOdd);
        Employee emmaGreet = new Employee("Emma", "Greet",
                new Address("Minsk", "Yellow str.", 1, 1),
                therapeuticDepartment, doctor, afternoonEven);
        Employee bobTorn = new Employee("Bob", "Torn",
                new Address("Minsk", "Green str.", 31, 75),
                surgeryDepartment, doctor, afternoonOdd);
        Employee jamesBrown = new Employee("James", "Brown",
                new Address("Minsk", "New str.", 15, 42),
                emergencyDepartment, doctor, beforeNoonEven);
        Employee karinaGold = new Employee("Karina", "Gold",
                new Address("Minsk", "Red str.", 11, 23),
                xRayDepartment, doctor, beforeNoonOdd);
        Employee helgaMoon = new Employee("Helga", "Moon",
                new Address("Minsk", "Green str.", 32, 56),
                therapeuticDepartment, nurse, beforeNoonOdd);
        Employee steeveHawk = new Employee("Steeve", "Hawk",
                new Address("Minsk", "Red str.", 2, 7),
                surgeryDepartment, nurse, beforeNoonEven);
        Employee jennyJoy = new Employee("Jenny", "Joy",
                new Address("Minsk", "Yellow str.", 15, 12),
                emergencyDepartment, nurse, afternoonOdd);
        Employee kellyHorn = new Employee("Kelly", "Horn",
                new Address("Minsk", "Black str.", 12, 66),
                xRayDepartment, nurse, afternoonEven);
        // Employee lists:
        Employee[] hospitalEmployees = new Employee[] {johnSmith, alexWhite, samGray, jackBlack, emmaGreet, bobTorn,
                jamesBrown, karinaGold, helgaMoon, steeveHawk, jennyJoy, kellyHorn};
        hospital.setEmployees(hospitalEmployees);
        Employee[] therapeuticDepartmentEmployees = new Employee[] {johnSmith, emmaGreet, helgaMoon};
        therapeuticDepartment.setEmployees(therapeuticDepartmentEmployees);
        Employee[] surgeryDepartmentEmployees = new Employee[] {alexWhite, bobTorn, steeveHawk};
        surgeryDepartment.setEmployees(surgeryDepartmentEmployees);
        Employee[] emergencyDepartmentEmployees = new Employee[] {samGray, jamesBrown, jennyJoy};
        emergencyDepartment.setEmployees(emergencyDepartmentEmployees);
        Employee[] xRayDepartmentEmployees = new Employee[] {jackBlack, karinaGold, kellyHorn};
        xRayDepartment.setEmployees(xRayDepartmentEmployees);
        // Equipment:
        Equipment computer = new Equipment("Computer");
        Equipment scales = new Equipment("Scales");
        Equipment stethoscope = new Equipment("Stethoscope");
        Equipment xRayMachine = new Equipment("X-Ray machine");
        Equipment surgeryTable = new Equipment("Surgery table");
        // Equipment lists:
        Equipment[] generalEquipment = new Equipment[] {computer, scales, stethoscope};
        therapeuticRoom.setListOfEquipment(generalEquipment);
        emergencyRoom.setListOfEquipment(generalEquipment);
        Equipment[] surgeryRoomEquipment = new Equipment[] {computer, scales, stethoscope, surgeryTable};
        surgeryRoom.setListOfEquipment(surgeryRoomEquipment);
        Equipment[] xRayRoomEquipment = new Equipment[] {computer, scales, stethoscope, xRayMachine};
        xRayRoom.setListOfEquipment(xRayRoomEquipment);
        // Patients:
        Patient andyBond = new Patient(emergencyDepartment);
        andyBond.setFirstName("Andy");
        andyBond.setLastName("Bond");
        andyBond.setAddress(new Address("Minsk", "Old str.", 89, 17));
        Patient deanJordan = new Patient(emergencyDepartment);
        deanJordan.setFirstName("Dean");
        deanJordan.setLastName("Jordan");
        deanJordan.setAddress(new Address("Warsaw", "First str.", 2, 21));
        Patient jerryGordon = new Patient("Jerry", "Gordon",
                new Address("Brest", "West str.", 13, 99), therapeuticDepartment);
        Patient miaCandy = new Patient("Mia", "Candy",
                new Address("Minsk", "Green str.", 19, 49), therapeuticDepartment);
        Patient lisaChristy = new Patient("Lisa", "Christy",
                new Address("Vilnius", "Main str.", 25, 84), surgeryDepartment);
        Patient donGordon = new Patient("Don", "Gordon",
                new Address("Brest", "East str.", 33, 36), surgeryDepartment);
        Patient georgeHolland = new Patient("George", "Holland",
                new Address("Warsaw", "Second str.", 77, 24), xRayDepartment);
        Patient milaHunt = new Patient("Mila", "Hunt",
                new Address("Minsk", "Red str.", 8, 47), xRayDepartment);
        // Patient lists:
        Patient[] hospitalPatients = new Patient[] {jerryGordon, miaCandy, lisaChristy, donGordon, georgeHolland,
                milaHunt, andyBond, deanJordan};
        hospital.setPatients(hospitalPatients);
        Patient[] emergencyDepartmentPatients = new Patient[] {andyBond, deanJordan};
        emergencyDepartment.setPatients(emergencyDepartmentPatients);
        Patient[] therapeuticDepartmentPatients = new Patient[] {jerryGordon,  miaCandy};
        therapeuticDepartment.setPatients(therapeuticDepartmentPatients);
        Patient[] surgeryDepartmentPatients = new Patient[] {lisaChristy, donGordon};
        surgeryDepartment.setPatients(surgeryDepartmentPatients);
        Patient[] xRayDepartmentPatients = new Patient[] {georgeHolland, milaHunt};
        xRayDepartment.setPatients(xRayDepartmentPatients);
    }
}