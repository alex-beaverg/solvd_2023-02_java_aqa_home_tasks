package org.example.tasks.homework02;

public class Floor {
    private int numberOfFloor;
    private OfficeRoom[] officeRooms;

    public Floor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    public OfficeRoom[] getOfficeRooms() {
        return officeRooms;
    }

    public void setOfficeRooms(OfficeRoom[] officeRooms) {
        this.officeRooms = officeRooms;
    }
}
