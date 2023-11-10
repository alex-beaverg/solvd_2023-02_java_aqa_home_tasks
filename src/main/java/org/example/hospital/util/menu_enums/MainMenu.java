package org.example.hospital.util.menu_enums;

public enum MainMenu implements IMenu {
    DEPARTMENTS("Show departments"),
    EMPLOYEES("Show all employees"),
    DOCTORS_MENU("Doctors menu actions"),
    PATIENTS_MENU("Patients menu actions"),
    EXIT("Exit");

    private final String title;

    MainMenu(String title) {
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
