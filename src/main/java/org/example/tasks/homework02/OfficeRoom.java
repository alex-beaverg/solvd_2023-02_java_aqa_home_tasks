package org.example.tasks.homework02;

public class OfficeRoom {
    private int numberOfRoom;
    private Floor floor;
    private Equipment[] listOfEquipment;

    public OfficeRoom(int numberOfRoom, Floor floor) {
        this.numberOfRoom = numberOfRoom;
        this.floor = floor;
    }

    public int getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(int numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public Equipment[] getListOfEquipment() {
        return listOfEquipment;
    }

    public void setListOfEquipment(Equipment[] listOfEquipment) {
        this.listOfEquipment = listOfEquipment;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }
}
