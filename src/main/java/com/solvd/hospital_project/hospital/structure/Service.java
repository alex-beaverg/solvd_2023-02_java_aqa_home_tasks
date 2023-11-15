package com.solvd.hospital_project.hospital.structure;

public enum Service implements IService {
    APPOINTMENT("Make an appointment", 5),
    TREATMENT("Prescribe treatment", 25),
    HOSPITALIZATION("Hospitalization", 100),
    EXAMINATION("Medical examination", 50);

    private final String title;
    private final double price;

    Service(String title, double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return title;
    }
}
