package org.example.hospital.data;

import org.example.hospital.people.*;
import org.example.hospital.structure.*;
import org.example.hospital.util.Creator;

public final class HardCodeObjects {
    public Hospital hospital = Creator.setHospital("City Hospital");

    public Department therapeuticDept = Creator.setDepartment("Therapeutic department", 103);
    public Department surgeryDept = Creator.setDepartment("Surgery department", 202);

    public Employee johnSmith = Creator.setEmployee("John", "Smith", 55,
            "Minsk", "Old avenue", 5, 100,
            therapeuticDept, Position.DEPT_HEAD, Schedule.BEFORE_NOON_EVEN_DAYS);
    public Employee alexWhite = Creator.setEmployee("Alex", "White", 51,
            "Minsk", "New street", 10, 215,
            surgeryDept, Position.DEPT_HEAD, Schedule.BEFORE_NOON_ODD_DAYS);
    public Employee emmaGreet = Creator.setEmployee("Emma", "Greet", 29,
            "Minsk", "Yellow avenue", 1, 1,
            therapeuticDept, Position.DOCTOR_1_CATEGORY, Schedule.AFTERNOON_EVEN_DAYS);
    public Employee jakeTube = Creator.setEmployee("Jake", "Tube", 35,
            "Minsk", "Red street", 4, 100,
            therapeuticDept, Position.DOCTOR_2_CATEGORY, Schedule.AFTERNOON_ODD_DAYS);
    public Employee samFreeze = Creator.setEmployee("Sam", "Freeze", 35,
            "Minsk", "Gold street", 43, 60,
            therapeuticDept, Position.DOCTOR_3_CATEGORY, Schedule.BEFORE_NOON_ODD_DAYS);
    public Employee bobTorn = Creator.setEmployee("Bob", "Torn", 31,
            "Minsk", "Green street", 31, 75,
            surgeryDept, Position.DOCTOR_1_CATEGORY, Schedule.AFTERNOON_ODD_DAYS);
    public Employee lesDrop = Creator.setEmployee("Les", "Drop", 38,
            "Minsk", "Old avenue", 61, 23,
            surgeryDept, Position.DOCTOR_2_CATEGORY, Schedule.AFTERNOON_EVEN_DAYS);
    public Employee chrisStone = Creator.setEmployee("Chris", "Stone", 33,
            "Minsk", "Old avenue", 12, 35,
            surgeryDept, Position.DOCTOR_3_CATEGORY, Schedule.BEFORE_NOON_ODD_DAYS);
    public Employee helgaMoon = Creator.setEmployee("Helga", "Moon", 25,
            "Minsk", "Green street", 32, 56,
            therapeuticDept, Position.NURSE, Schedule.BEFORE_NOON_ODD_DAYS);
    public Employee steeveHawk = Creator.setEmployee("Steeve", "Hawk", 24,
            "Minsk", "Red street", 2, 7,
            surgeryDept, Position.NURSE, Schedule.BEFORE_NOON_EVEN_DAYS);

    public Patient ericAdams = Creator.setPatient("Eric", "Adams", 30, "Minsk",
            "Main street", 22, 11, Diagnosis.FLU, therapeuticDept);
    public Patient lisaBourne = Creator.setPatient("Lisa", "Bourne", 28, "Minsk",
            "Green street", 45, 9, Diagnosis.BONE_FRACTURE, surgeryDept);
    public Patient roseDart = Creator.setPatient("Rose", "Dart", 23, "Brest",
            "Old avenue", 4, 66, Diagnosis.COVID, therapeuticDept);
    public Patient maxCorn = Creator.setPatient("Max", "Corn", 33, "Minsk",
            "Red street", 5, 97, Diagnosis.UNKNOWN, therapeuticDept);

    public void fillArrays() {
        hospital.setDepartments(new Department[]{therapeuticDept, surgeryDept});
        hospital.setEmployees(new Employee[]{johnSmith, emmaGreet, helgaMoon, alexWhite, bobTorn, steeveHawk, jakeTube,
                samFreeze, lesDrop, chrisStone});
        therapeuticDept.setEmployees(new Employee[]{johnSmith, emmaGreet, helgaMoon, jakeTube, samFreeze});
        surgeryDept.setEmployees(new Employee[]{alexWhite, bobTorn, steeveHawk, lesDrop, chrisStone});

        hospital.addPatient(ericAdams);
        therapeuticDept.addPatient(ericAdams);
        ericAdams.setDiagnosis(Diagnosis.FLU);
        ericAdams.setDoctor(therapeuticDept.getRandomEmployeeBySpecialistClass(2));
        ericAdams.setNurse(therapeuticDept.getRandomEmployeeBySpecialistClass(1));
        ericAdams.addService(Service.APPOINTMENT);
        ericAdams.addService(Service.TREATMENT);
        ericAdams.addVipService(VipService.WALK_IN_SERVICE);
        ericAdams.getDoctor().addService(Service.APPOINTMENT);
        ericAdams.getDoctor().addService(Service.TREATMENT);
        ericAdams.getDoctor().addVipService(VipService.WALK_IN_SERVICE);
        ericAdams.getDoctor().addPatient(ericAdams);

        hospital.addPatient(lisaBourne);
        surgeryDept.addPatient(lisaBourne);
        lisaBourne.setDiagnosis(Diagnosis.BONE_FRACTURE);
        lisaBourne.setDoctor(surgeryDept.getRandomEmployeeBySpecialistClass(2));
        lisaBourne.setNurse(surgeryDept.getRandomEmployeeBySpecialistClass(1));
        lisaBourne.addService(Service.APPOINTMENT);
        lisaBourne.addService(Service.HOSPITALIZATION);
        lisaBourne.addVipService(VipService.SEPARATE_ROOM);
        lisaBourne.addVipService(VipService.SPECIAL_FOOD);
        lisaBourne.getDoctor().addService(Service.APPOINTMENT);
        lisaBourne.getDoctor().addService(Service.HOSPITALIZATION);
        lisaBourne.getDoctor().addVipService(VipService.SEPARATE_ROOM);
        lisaBourne.getDoctor().addVipService(VipService.SPECIAL_FOOD);
        lisaBourne.getDoctor().addPatient(lisaBourne);

        hospital.addPatient(roseDart);
        therapeuticDept.addPatient(roseDart);
        roseDart.setDiagnosis(Diagnosis.COVID);
        roseDart.setDoctor(therapeuticDept.getRandomEmployeeBySpecialistClass(2));
        roseDart.setNurse(therapeuticDept.getRandomEmployeeBySpecialistClass(1));
        roseDart.addService(Service.APPOINTMENT);
        roseDart.addService(Service.HOSPITALIZATION);
        roseDart.addService(Service.TREATMENT);
        roseDart.addService(Service.EXAMINATION);
        roseDart.addVipService(VipService.SEPARATE_ROOM);
        roseDart.addVipService(VipService.SPECIAL_FOOD);
        roseDart.addVipService(VipService.WALK_IN_SERVICE);
        roseDart.addVipService(VipService.ENTERTAINMENT);
        roseDart.getDoctor().addService(Service.APPOINTMENT);
        roseDart.getDoctor().addService(Service.HOSPITALIZATION);
        roseDart.getDoctor().addService(Service.TREATMENT);
        roseDart.getDoctor().addService(Service.EXAMINATION);
        roseDart.getDoctor().addVipService(VipService.SEPARATE_ROOM);
        roseDart.getDoctor().addVipService(VipService.SPECIAL_FOOD);
        roseDart.getDoctor().addVipService(VipService.WALK_IN_SERVICE);
        roseDart.getDoctor().addVipService(VipService.ENTERTAINMENT);
        roseDart.getDoctor().addPatient(roseDart);

        hospital.addPatient(maxCorn);
        therapeuticDept.addPatient(maxCorn);
        maxCorn.setDiagnosis(Diagnosis.UNKNOWN);
        maxCorn.setDoctor(therapeuticDept.getRandomEmployeeBySpecialistClass(2));
        maxCorn.setNurse(therapeuticDept.getRandomEmployeeBySpecialistClass(1));
        maxCorn.addService(Service.APPOINTMENT);
        maxCorn.addService(Service.HOSPITALIZATION);
        maxCorn.addVipService(VipService.SPECIAL_FOOD);
        maxCorn.addVipService(VipService.WALK_IN_SERVICE);
        maxCorn.getDoctor().addService(Service.APPOINTMENT);
        maxCorn.getDoctor().addService(Service.HOSPITALIZATION);
        maxCorn.getDoctor().addVipService(VipService.SPECIAL_FOOD);
        maxCorn.getDoctor().addVipService(VipService.WALK_IN_SERVICE);
        maxCorn.getDoctor().addPatient(maxCorn);
    }
}
