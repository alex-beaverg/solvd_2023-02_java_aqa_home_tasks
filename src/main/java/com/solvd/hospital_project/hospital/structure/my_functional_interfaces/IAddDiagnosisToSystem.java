package com.solvd.hospital_project.hospital.structure.my_functional_interfaces;

@FunctionalInterface
public interface IAddDiagnosisToSystem<T> {
    void add(T object);
}
