package org.example.hospital.people;

import org.example.hospital.structure.Department;

import java.util.Objects;

public enum Position {
    DEPT_HEAD ("Department Head", 3),
    DOCTOR ("Doctor", 2),
    NURSE ("Nurse", 1);

    private final String title;
    private final int classBonus;

    Position(String title,
             int classBonus) {
        this.title = title;
        this.classBonus = classBonus;
    }

    public int getClassBonus() {
        return classBonus;
    }

    @Override
    public String toString() {
        return title;
    }
}
