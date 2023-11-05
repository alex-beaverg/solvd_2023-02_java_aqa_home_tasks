package org.example.hospital.people;

public enum Diagnosis {
    FLU("Flu"),
    COVID("Covid-19"),
    BONE_FRACTURE("Bone fracture"),
    UNKNOWN("Unknown diagnosis");

    private final String title;

    Diagnosis(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
