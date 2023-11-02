package org.example.hospital.structure;

public enum Service {
    APPOINTMENT("Make an appointment", 5),
    TREATMENT("Prescribe treatment", 25),
    HOSPITALIZATION("To hospitalize", 100),
    NOTHING("Do nothing", 0);

    private String title;
    private double price;

    Service(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return title + " (price: " + price + " BYN)";
    }
}
