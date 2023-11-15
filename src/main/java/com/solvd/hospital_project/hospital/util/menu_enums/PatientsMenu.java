package com.solvd.hospital_project.hospital.util.menu_enums;

public enum PatientsMenu implements IMenu {
    EXIST_PATIENT("Show all patients and choose one of them"),
    FIND_PATIENT("Find and choose the patient in registry"),
    REGISTER_PATIENT("Register new patient"),
    MAIN_MENU("Go to main menu"),
    EXIT("Exit");

    private final String title;

    PatientsMenu(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
