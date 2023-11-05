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

    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public final void setAge(int age) {
        this.age = age;
    }

    public final void setAddress(Address address) {
        this.address = address;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "\n\tFirst name: " + firstName +
                "\n\tLast name: " + lastName +
                "\n\tAge: " + age +
                "\n\t" + address;
    }
}
