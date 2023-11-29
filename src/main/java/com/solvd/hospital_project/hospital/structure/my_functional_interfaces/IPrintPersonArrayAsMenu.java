package com.solvd.hospital_project.hospital.structure.my_functional_interfaces;

@FunctionalInterface
public interface IPrintPersonArrayAsMenu<V, T> {
    V print(T[] array);
}
