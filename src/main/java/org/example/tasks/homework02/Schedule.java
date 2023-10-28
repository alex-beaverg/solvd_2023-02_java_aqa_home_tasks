package org.example.tasks.homework02;

public class Schedule {
    private String partOfTheDay;
    private String daysOfTheMonth;

    public Schedule(String partOfTheDay, String daysOfTheMonth) {
        this.partOfTheDay = partOfTheDay;
        this.daysOfTheMonth = daysOfTheMonth;
    }

    public String getPartOfTheDay() {
        return partOfTheDay;
    }

    public void setPartOfTheDay(String partOfTheDay) {
        this.partOfTheDay = partOfTheDay;
    }

    public String getDaysOfTheMonth() {
        return daysOfTheMonth;
    }

    public void setDaysOfTheMonth(String daysOfTheMonth) {
        this.daysOfTheMonth = daysOfTheMonth;
    }
}
