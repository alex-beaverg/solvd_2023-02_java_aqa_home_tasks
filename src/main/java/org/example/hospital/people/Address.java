package org.example.hospital.people;

import java.util.Objects;

public class Address {
    protected String city;
    protected String street;
    protected int houseNumber;
    protected int flatNumber;

    public Address() { }

    public Address(String city,
                   String street,
                   int houseNumber,
                   int flatNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }

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
