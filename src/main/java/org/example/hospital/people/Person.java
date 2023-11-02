package org.example.hospital.people;

public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected int age;
    protected Address address;

    public Person() { }

    public Person(String firstName,
                  String lastName,
                  int age,
                  Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
    }

    public abstract String getRole();

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "\n\tFirst name: " + firstName +
                "\n\tLast name: " + lastName +
                "\n\tAge: " + age +
                "\n\t" + address;
    }
}
