package com.solvd.hospital_project.hospital.util.menu_enums;

public enum DoctorMenu implements IMenu {
    SHOW_DOCTOR_INFO("Show full information about doctor"),
    SHOW_DOCTOR_PAYSLIP("Show doctor's payslip"),
    DOCTORS_MENU("Return to doctors menu actions"),
    MAIN_MENU("Go to main menu"),
    EXIT("Exit");

    private final String title;

    DoctorMenu(String title) {
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
