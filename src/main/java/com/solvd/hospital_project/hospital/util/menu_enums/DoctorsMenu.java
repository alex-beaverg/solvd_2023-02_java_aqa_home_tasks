package com.solvd.hospital_project.hospital.util.menu_enums;

public enum DoctorsMenu implements IMenu {
    DOCTORS("Show all doctors to choose one of them"),
    DOCTORS_WITH_THEIR_PATIENTS("Show all doctors with their patients"),
    MAIN_MENU("Go to main menu"),
    EXIT("Exit");

    private final String title;

    DoctorsMenu(String title) {
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
