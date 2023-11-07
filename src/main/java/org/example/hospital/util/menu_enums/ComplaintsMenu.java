package org.example.hospital.util.menu_enums;

public enum ComplaintsMenu implements IMenu {
    COUGH("Cough"),
    NO_SMELLS("No smells"),
    BROKEN_BONE("Broken bone"),
    UNKNOWN("Unknown");

    private final String title;

    ComplaintsMenu(String title) {
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
