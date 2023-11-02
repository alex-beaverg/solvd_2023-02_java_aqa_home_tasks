package org.example.hospital.people;

public enum Diagnosis {
    FLU("Flu"),
    COVID("Covid-19"),
    BROKEN_BONE("Broken bone"),
    UNKNOWN("Unknown diagnosis");

    private final String title;

    Diagnosis(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
