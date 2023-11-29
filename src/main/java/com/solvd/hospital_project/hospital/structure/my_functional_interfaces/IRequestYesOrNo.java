package com.solvd.hospital_project.hospital.structure.my_functional_interfaces;

@FunctionalInterface
public interface IRequestYesOrNo<T, V> {
    T ask(V question);
}
