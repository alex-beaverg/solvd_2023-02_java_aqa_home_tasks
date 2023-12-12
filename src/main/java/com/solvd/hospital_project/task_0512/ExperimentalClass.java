package com.solvd.hospital_project.task_0512;

import static com.solvd.hospital_project.hospital.util.Printers.*;

public class ExperimentalClass {
    // Fields:
    public final static String CLASS_CONST = "Hello world!";
    protected final int INSTANCE_CONST = 100;
    public String publicStringField;
    private int privateIntField;
    protected double protectedDoubleField;
    long defaultLongField;

    // Constructors:
    private ExperimentalClass() { }

    public ExperimentalClass(String publicStringField) {
        this.publicStringField = publicStringField;
    }

    protected ExperimentalClass(String publicStringField,
                                int privateIntField) {
        this.publicStringField = publicStringField;
        this.privateIntField = privateIntField;
    }

    ExperimentalClass(String publicStringField,
                      int privateIntField,
                      double protectedDoubleField) {
        this.publicStringField = publicStringField;
        this.privateIntField = privateIntField;
        this.protectedDoubleField = protectedDoubleField;
    }

    public ExperimentalClass(String publicStringField,
                             int privateIntField,
                             double protectedDoubleField,
                             long defaultLongField) {
        this.publicStringField = publicStringField;
        this.privateIntField = privateIntField;
        this.protectedDoubleField = protectedDoubleField;
        this.defaultLongField = defaultLongField;
    }

    // Getters and Setters:
    public String getPublicStringField() {
        return publicStringField;
    }

    protected void setPublicStringField(String publicStringField) {
        this.publicStringField = publicStringField;
    }

    public int getPrivateIntField() {
        return privateIntField;
    }

    protected void setPrivateIntField(int privateIntField) {
        this.privateIntField = privateIntField;
    }

    public double getProtectedDoubleField() {
        return protectedDoubleField;
    }

    protected void setProtectedDoubleField(double protectedDoubleField) {
        this.protectedDoubleField = protectedDoubleField;
    }

    public long getDefaultLongField() {
        return defaultLongField;
    }

    protected void setDefaultLongField(long defaultLongField) {
        this.defaultLongField = defaultLongField;
    }

    // Other methods:
    protected double calculateSomething(double somethingIncome, double somethingIncomeElse) {
        return (somethingIncome + somethingIncomeElse) * protectedDoubleField;
    }

    String concatenateSomething(String somethingIncome, String somethingIncomeElse) {
        return somethingIncome + " " + somethingIncomeElse + " " + publicStringField + " " + CLASS_CONST;
    }

    private int calculatePrivateData() {
        return (privateIntField + INSTANCE_CONST) * 5;
    }

    private void printData() {
        PRINTLN.info("CLASS_CONST: " + CLASS_CONST);
        PRINTLN.info("INSTANCE_CONST: " + INSTANCE_CONST);
        PRINTLN.info("publicStringField: " + publicStringField);
        PRINTLN.info("privateIntField: " + privateIntField);
        PRINTLN.info("protectedDoubleField: " + protectedDoubleField);
        PRINTLN.info("defaultLongField: " + defaultLongField);
    }

    @Override
    public String toString() {
        return "ExperimentalClass {" +
                "CLASS_CONST = " + CLASS_CONST +
                ", INSTANCE_CONST = " + INSTANCE_CONST +
                ", publicStringField = '" + publicStringField + '\'' +
                ", privateIntField = " + privateIntField +
                ", protectedDoubleField = " + protectedDoubleField +
                ", defaultLongField = " + defaultLongField +
                '}';
    }
}
