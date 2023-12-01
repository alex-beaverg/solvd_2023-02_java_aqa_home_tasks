package com.solvd.hospital_project.hospital.structure.my_functional_interfaces;

@FunctionalInterface
public interface IPrintAsMenu<T, V> {
    void print(T index, V line);
}
