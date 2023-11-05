package org.example.hospital.data;

import org.example.hospital.people.*;
import org.example.hospital.structure.*;

public class Creator {
    public Hospital hospital = setHospital("City Hospital");

    public Department therapeuticDept = setDepartment("Therapeutic department", 103);
    public Department surgeryDept = setDepartment("Surgery department", 202);

    public Employee johnSmith = setEmployee("John", "Smith", 55, "Minsk", "Old str.",
            5, 100, therapeuticDept, Position.DEPT_HEAD, Schedule.BEFORE_NOON_EVEN_DAYS);
    public Employee alexWhite = setEmployee("Alex", "White", 51, "Minsk", "New str.",
            10, 215, surgeryDept, Position.DEPT_HEAD, Schedule.BEFORE_NOON_ODD_DAYS);
    public Employee emmaGreet = setEmployee("Emma", "Greet", 29, "Minsk", "Yellow str.",
            1, 1, therapeuticDept, Position.DOCTOR_1_CATEGORY, Schedule.AFTERNOON_EVEN_DAYS);
    public Employee jakeTube = setEmployee("Jake", "Tube", 35, "Minsk", "Red str.",
            4, 100, therapeuticDept, Position.DOCTOR_2_CATEGORY, Schedule.AFTERNOON_ODD_DAYS);
    public Employee samFreeze = setEmployee("Sam", "Freeze", 35, "Minsk", "Gold str.",
            43, 60, therapeuticDept, Position.DOCTOR_3_CATEGORY, Schedule.BEFORE_NOON_ODD_DAYS);
    public Employee bobTorn = setEmployee("Bob", "Torn", 31, "Minsk", "Green str.",
            31, 75, surgeryDept, Position.DOCTOR_1_CATEGORY, Schedule.AFTERNOON_ODD_DAYS);
    public Employee lesDrop = setEmployee("Les", "Drop", 38, "Minsk", "Old str.",
            61, 23, surgeryDept, Position.DOCTOR_2_CATEGORY, Schedule.AFTERNOON_EVEN_DAYS);
    public Employee chrisStone = setEmployee("Chris", "Stone", 33, "Minsk", "Old str.",
            12, 35, surgeryDept, Position.DOCTOR_3_CATEGORY, Schedule.BEFORE_NOON_ODD_DAYS);
    public Employee helgaMoon = setEmployee("Helga", "Moon", 25, "Minsk", "Green str.",
            32, 56, therapeuticDept, Position.NURSE, Schedule.BEFORE_NOON_ODD_DAYS);
    public Employee steeveHawk = setEmployee("Steeve", "Hawk", 24, "Minsk", "Red str.",
            2, 7, surgeryDept, Position.NURSE, Schedule.BEFORE_NOON_EVEN_DAYS);

    public Patient ericAdams = setPatient("Eric", "Adams", 30, "Minsk", "Main str.",
            22, 11, Diagnosis.COVID, therapeuticDept);
    public Patient lisaBourne = setPatient("Lisa", "Bourne", 28, "Minsk", "Green str.",
            45, 9, Diagnosis.BONE_FRACTURE, surgeryDept);
    public Patient roseDart = setPatient("Rose", "Dart", 23, "Brest", "Old str.",
            4, 66, Diagnosis.FLU, therapeuticDept);
    public Patient maxCorn = setPatient("Max", "Corn", 33, "Minsk", "Red str.",
            5, 97, Diagnosis.UNKNOWN, therapeuticDept);

    public static Hospital setHospital(String hospitalTitle) {
        return new Hospital(hospitalTitle);
    }

    public static Department setDepartment(String departmentTitle, int officeRoom) {
        return new Department(departmentTitle, officeRoom);
    }

    public static Employee setEmployee(String firstName, String lastName, int age, String city, String street,
                                       int houseNumber, int flatNumber, Department department, Position position,
                                       Schedule schedule) {
        Address address = new Address(city, street, houseNumber, flatNumber);
        return new Employee(firstName, lastName, age, address, department, position, schedule);
    }

    public static Patient setPatient(String firstName, String lastName, int age, String city, String street,
                                     int houseNumber, int flatNumber, Diagnosis diagnosis, Department department) {
        Address address = new Address(city, street, houseNumber, flatNumber);
        return new Patient(firstName, lastName, age, address, diagnosis, department);
    }

    public void fillArrays() {
        hospital.setDepartments(new Department[]{therapeuticDept, surgeryDept});
        hospital.setEmployees(new Employee[]{johnSmith, emmaGreet, helgaMoon, alexWhite, bobTorn, steeveHawk, jakeTube,
                samFreeze, lesDrop, chrisStone});
        therapeuticDept.setEmployees(new Employee[]{johnSmith, emmaGreet, helgaMoon, jakeTube, samFreeze});
        surgeryDept.setEmployees(new Employee[]{alexWhite, bobTorn, steeveHawk, lesDrop, chrisStone});

        hospital.addPatient(ericAdams);
        therapeuticDept.addPatient(ericAdams);
        ericAdams.setDiagnosis(Diagnosis.FLU);
        ericAdams.setTherapist(therapeuticDept.getEmployeeBySpecialistClass(2));
        ericAdams.setNurse(therapeuticDept.getEmployeeBySpecialistClass(1));
        ericAdams.addService(Service.APPOINTMENT);
        ericAdams.addService(Service.TREATMENT);
        ericAdams.addVipService(VipService.WALK_IN_SERVICE);
        ericAdams.getTherapist().addService(Service.APPOINTMENT);
        ericAdams.getTherapist().addService(Service.TREATMENT);
        ericAdams.getTherapist().addVipService(VipService.WALK_IN_SERVICE);
        ericAdams.getTherapist().addPatient(ericAdams);

        hospital.addPatient(lisaBourne);
        surgeryDept.addPatient(lisaBourne);
        lisaBourne.setDiagnosis(Diagnosis.COVID);
        lisaBourne.setTherapist(surgeryDept.getEmployeeBySpecialistClass(2));
        lisaBourne.setNurse(surgeryDept.getEmployeeBySpecialistClass(1));
        lisaBourne.addService(Service.APPOINTMENT);
        lisaBourne.addService(Service.HOSPITALIZATION);
        lisaBourne.addVipService(VipService.SEPARATE_ROOM);
        lisaBourne.addVipService(VipService.SPECIAL_FOOD);
        lisaBourne.getTherapist().addService(Service.APPOINTMENT);
        lisaBourne.getTherapist().addService(Service.HOSPITALIZATION);
        lisaBourne.getTherapist().addVipService(VipService.SEPARATE_ROOM);
        lisaBourne.getTherapist().addVipService(VipService.SPECIAL_FOOD);
        lisaBourne.getTherapist().addPatient(lisaBourne);

        hospital.addPatient(roseDart);
        therapeuticDept.addPatient(roseDart);
        roseDart.setDiagnosis(Diagnosis.BONE_FRACTURE);
        roseDart.setTherapist(therapeuticDept.getEmployeeBySpecialistClass(2));
        roseDart.setNurse(therapeuticDept.getEmployeeBySpecialistClass(1));
        roseDart.addService(Service.APPOINTMENT);
        roseDart.addService(Service.HOSPITALIZATION);
        roseDart.addService(Service.TREATMENT);
        roseDart.addService(Service.EXAMINATION);
        roseDart.addVipService(VipService.SEPARATE_ROOM);
        roseDart.addVipService(VipService.SPECIAL_FOOD);
        roseDart.addVipService(VipService.WALK_IN_SERVICE);
        roseDart.addVipService(VipService.ENTERTAINMENT);
        roseDart.getTherapist().addService(Service.APPOINTMENT);
        roseDart.getTherapist().addService(Service.HOSPITALIZATION);
        roseDart.getTherapist().addService(Service.TREATMENT);
        roseDart.getTherapist().addService(Service.EXAMINATION);
        roseDart.getTherapist().addVipService(VipService.SEPARATE_ROOM);
        roseDart.getTherapist().addVipService(VipService.SPECIAL_FOOD);
        roseDart.getTherapist().addVipService(VipService.WALK_IN_SERVICE);
        roseDart.getTherapist().addVipService(VipService.ENTERTAINMENT);
        roseDart.getTherapist().addPatient(roseDart);

        hospital.addPatient(maxCorn);
        therapeuticDept.addPatient(maxCorn);
        maxCorn.setDiagnosis(Diagnosis.UNKNOWN);
        maxCorn.setTherapist(therapeuticDept.getEmployeeBySpecialistClass(2));
        maxCorn.setNurse(therapeuticDept.getEmployeeBySpecialistClass(1));
        maxCorn.addService(Service.APPOINTMENT);
        maxCorn.addService(Service.HOSPITALIZATION);
        maxCorn.addVipService(VipService.SPECIAL_FOOD);
        maxCorn.addVipService(VipService.WALK_IN_SERVICE);
        maxCorn.getTherapist().addService(Service.APPOINTMENT);
        maxCorn.getTherapist().addService(Service.HOSPITALIZATION);
        maxCorn.getTherapist().addVipService(VipService.SPECIAL_FOOD);
        maxCorn.getTherapist().addVipService(VipService.WALK_IN_SERVICE);
        maxCorn.getTherapist().addPatient(maxCorn);
    }
}
