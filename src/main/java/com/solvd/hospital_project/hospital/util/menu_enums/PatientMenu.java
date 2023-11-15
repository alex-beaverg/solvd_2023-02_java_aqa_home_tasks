package com.solvd.hospital_project.hospital.util.menu_enums;

public enum PatientMenu implements IMenu {
    CHANGE_DOCTOR("Change doctor"),
    DELETE_VIP("Delete VIP service"),
    ADD_VIP("Add VIP service"),
    SHOW_PATIENT_INFO("Show full information about patient"),
    PATIENTS_MENU("Return to patients menu actions"),
    MAIN_MENU("Go to main menu"),
    EXIT("Exit");

    private final String title;

    PatientMenu(String title) {
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
