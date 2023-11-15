package org.example.hospital.util.menu_enums;

public enum ComplaintsMenu implements IMenu {
    COUGH("Cough", "General"),
    NO_SMELLS("No smells", "General"),
    BROKEN_BONE("Broken bone", "Injuries"),
    HAND_INJURY("Hand injury", "Injuries"),
    LEG_INJURY("Leg injury", "Injuries"),
    UNKNOWN("Unknown", "General");

    private final String title;
    private final String type;

    ComplaintsMenu(String title, String type) {
        this.title = title;
        this.type = type;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return title;
    }
}
