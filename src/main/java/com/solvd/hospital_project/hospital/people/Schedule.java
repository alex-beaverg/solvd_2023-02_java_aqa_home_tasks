package com.solvd.hospital_project.hospital.people;

public enum Schedule {
    BEFORE_NOON_EVEN_DAYS ("Before noon", "Even days"),
    BEFORE_NOON_ODD_DAYS ("Before noon", "Odd days"),
    AFTERNOON_EVEN_DAYS ("Afternoon", "Even days"),
    AFTERNOON_ODD_DAYS ("Afternoon", "Odd days");

    private final String partOfTheDay;
    private final String daysOfTheMonth;

    Schedule(String partOfTheDay,
             String daysOfTheMonth) {
        this.partOfTheDay = partOfTheDay;
        this.daysOfTheMonth = daysOfTheMonth;
    }

    @Override
    public String toString() {
        return partOfTheDay + " (" + daysOfTheMonth + ")";
    }
}
