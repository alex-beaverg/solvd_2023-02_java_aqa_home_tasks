package org.example.hospital.people;

import java.util.Objects;

public final class Address implements AutoCloseable {
    private String city;
    private String street;
    private int houseNumber;
    private int flatNumber;

    public Address() { }

    // Overloading:
    public Address(String city,
                   String street,
                   int houseNumber,
                   int flatNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    @Override
    public void close() { }

    @Override
    public int hashCode() {
        int result = city == null ? 0 : city.hashCode();
        result = 31 * result + (street == null ? 0 : street.hashCode());
        result = 31 * result + houseNumber;
        result = 31 * result + flatNumber;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Address that = (Address) obj;
        if (!Objects.equals(city, that.city)) return false;
        if (!Objects.equals(street, that.street)) return false;
        if (houseNumber != that.houseNumber) return false;
        return flatNumber == that.flatNumber;
    }

    @Override
    public String toString() {
        return "Address: " + city + ", " + street + ", " + houseNumber + "-" + flatNumber;
    }
}
