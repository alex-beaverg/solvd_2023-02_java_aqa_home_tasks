package org.example.hospital.structure;

import org.example.hospital.people.Position;

public class Accounting {
    private static final double BASE_SALARY = 1503.5;
    private static final double TAX = 0.2;

    public static double calculateSalary(Position position, double costOfServices) {
        return (BASE_SALARY + costOfServices  / 6) * position.getClassBonus() -
                ((BASE_SALARY + costOfServices  / 6) * position.getClassBonus() ) * TAX;
    }
}
