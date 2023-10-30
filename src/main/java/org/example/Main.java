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
        Department emergencyDept = new Department("Emergency department", emergencyRoom);
        Department therapeuticDept = new Department("Therapeutic department", therapeuticRoom);
        Department surgeryDept = new Department("Surgery department", surgeryRoom);
        Department xRayDept = new Department("X-Ray department", xRayRoom);
        // Department list:
        Department[] departments = new Department[] {emergencyDept, therapeuticDept, surgeryDept, xRayDept};
        hospital.setDepartments(departments);
        // Positions:
        Position deptHead = new Position("Department head");
        Position doctor = new Position("Doctor");
        Position nurse = new Position("Nurse");
        // Schedules:
        Schedule beforeNoonEven = new Schedule("Before noon", "Even days");
        Schedule beforeNoonOdd = new Schedule("Before noon", "Odd days");
        Schedule afternoonEven = new Schedule("Afternoon", "Even days");
        Schedule afternoonOdd = new Schedule("Afternoon", "Odd days");
        // Employees:
        Employee johnSmith = new Employee("John", "Smith", 55,
                new Address("Minsk", "Old str.", 5, 100),
                therapeuticDept, deptHead, beforeNoonEven);
        Employee alexWhite = new Employee("Alex", "White", 51,
                new Address("Minsk", "New str.", 10, 215),
                surgeryDept, deptHead, beforeNoonOdd);
        Employee samGray = new Employee("Sam", "Gray", 45,
                new Address("Minsk", "Old str.", 12, 33),
                emergencyDept, deptHead, afternoonEven);
        Employee jackBlack = new Employee("Jack", "Black", 40,
                new Address("Minsk", "Green str.", 22, 45),
                xRayDept, deptHead, afternoonOdd);
        Employee emmaGreet = new Employee("Emma", "Greet", 29,
                new Address("Minsk", "Yellow str.", 1, 1),
                therapeuticDept, doctor, afternoonEven);
        Employee bobTorn = new Employee("Bob", "Torn", 31,
                new Address("Minsk", "Green str.", 31, 75),
                surgeryDept, doctor, afternoonOdd);
        Employee jamesBrown = new Employee("James", "Brown", 30,
                new Address("Minsk", "New str.", 15, 42),
                emergencyDept, doctor, beforeNoonEven);
        Employee karinaGold = new Employee("Karina", "Gold", 33,
                new Address("Minsk", "Red str.", 11, 23),
                xRayDept, doctor, beforeNoonOdd);
        Employee helgaMoon = new Employee("Helga", "Moon", 25,
                new Address("Minsk", "Green str.", 32, 56),
                therapeuticDept, nurse, beforeNoonOdd);
        Employee steeveHawk = new Employee("Steeve", "Hawk", 24,
                new Address("Minsk", "Red str.", 2, 7),
                surgeryDept, nurse, beforeNoonEven);
        Employee jennyJoy = new Employee("Jenny", "Joy", 27,
                new Address("Minsk", "Yellow str.", 15, 12),
                emergencyDept, nurse, afternoonOdd);
        Employee kellyHorn = new Employee("Kelly", "Horn", 25,
                new Address("Minsk", "Black str.", 12, 66),
                xRayDept, nurse, afternoonEven);
        // Employee lists:
        Employee[] hospitalEmployees = new Employee[] {johnSmith, alexWhite, samGray, jackBlack, emmaGreet, bobTorn,
                jamesBrown, karinaGold, helgaMoon, steeveHawk, jennyJoy, kellyHorn};
        hospital.setEmployees(hospitalEmployees);
        Employee[] therapeuticDeptEmployees = new Employee[] {johnSmith, emmaGreet, helgaMoon};
        therapeuticDept.setEmployees(therapeuticDeptEmployees);
        Employee[] surgeryDeptEmployees = new Employee[] {alexWhite, bobTorn, steeveHawk};
        surgeryDept.setEmployees(surgeryDeptEmployees);
        Employee[] emergencyDeptEmployees = new Employee[] {samGray, jamesBrown, jennyJoy};
        emergencyDept.setEmployees(emergencyDeptEmployees);
        Employee[] xRayDeptEmployees = new Employee[] {jackBlack, karinaGold, kellyHorn};
        xRayDept.setEmployees(xRayDeptEmployees);
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
        Patient andyBond = new Patient(emergencyDept);
        andyBond.setFirstName("Andy");
        andyBond.setLastName("Bond");
        andyBond.setAge(55);
        andyBond.setAddress(new Address("Minsk", "Old str.", 89, 17));
        Patient deanJordan = new Patient(emergencyDept);
        deanJordan.setFirstName("Dean");
        deanJordan.setLastName("Jordan");
        deanJordan.setAge(36);
        deanJordan.setAddress(new Address("Warsaw", "First str.", 2, 21));
        Patient jerryGordon = new Patient("Jerry", "Gordon", 33,
                new Address("Brest", "West str.", 13, 99), therapeuticDept);
        Patient miaCandy = new Patient("Mia", "Candy", 31,
                new Address("Minsk", "Green str.", 19, 49), therapeuticDept);
        Patient lisaChristy = new Patient("Lisa", "Christy", 25,
                new Address("Vilnius", "Main str.", 25, 84), surgeryDept);
        Patient donGordon = new Patient("Don", "Gordon", 29,
                new Address("Brest", "East str.", 33, 36), surgeryDept);
        Patient georgeHolland = new Patient("George", "Holland", 41,
                new Address("Warsaw", "Second str.", 77, 24), xRayDept);
        Patient milaHunt = new Patient("Mila", "Hunt", 40,
                new Address("Minsk", "Red str.", 8, 47), xRayDept);
        // Patient lists:
        Patient[] hospitalPatients = new Patient[] {jerryGordon, miaCandy, lisaChristy, donGordon, georgeHolland,
                milaHunt, andyBond, deanJordan};
        hospital.setPatients(hospitalPatients);
        Patient[] emergencyDeptPatients = new Patient[] {andyBond, deanJordan};
        emergencyDept.setPatients(emergencyDeptPatients);
        Patient[] therapeuticDeptPatients = new Patient[] {jerryGordon,  miaCandy};
        therapeuticDept.setPatients(therapeuticDeptPatients);
        Patient[] surgeryDeptPatients = new Patient[] {lisaChristy, donGordon};
        surgeryDept.setPatients(surgeryDeptPatients);
        Patient[] xRayDeptPatients = new Patient[] {georgeHolland, milaHunt};
        xRayDept.setPatients(xRayDeptPatients);
    }
}