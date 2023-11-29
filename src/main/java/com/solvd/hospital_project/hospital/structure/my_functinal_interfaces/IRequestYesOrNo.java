package com.solvd.hospital_project.hospital.structure.my_functinal_interfaces;

@FunctionalInterface
public interface IRequestYesOrNo<T, V> {
    T ask(V question);
}
