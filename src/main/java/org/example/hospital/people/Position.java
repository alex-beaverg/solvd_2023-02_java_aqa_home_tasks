package org.example.hospital.people;

public enum Position {
    DEPT_HEAD ("Department Head", 3, 1, 1.8),
    DOCTOR_1_CATEGORY("Doctor 1st category", 2, 1, 1.0),
    DOCTOR_2_CATEGORY("Doctor 2nd category", 2, 2, 1.2),
    DOCTOR_3_CATEGORY("Doctor 3rd category", 2, 3, 1.6),
    NURSE ("Nurse", 1, 1, 1.0);

    private final String title;
    private final int specialistClass;
    private final int category;
    private final double serviceCoefficient;

    Position(String title,
             int specialistClass,
             int category,
             double serviceCoefficient) {
        this.title = title;
        this.specialistClass = specialistClass;
        this.category = category;
        this.serviceCoefficient = serviceCoefficient;
    }

    public int getSpecialistClass() {
        return specialistClass;
    }

    public int getCategory() {
        return category;
    }

    public double getServiceCoefficient() {
        return serviceCoefficient;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
