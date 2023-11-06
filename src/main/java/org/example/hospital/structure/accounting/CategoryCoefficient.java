package org.example.hospital.structure.accounting;

public enum CategoryCoefficient implements IGetValue {
    CAT1_COEF("1st category coefficient", 1.32),
    CAT2_COEF("2nd category coefficient", 1.61),
    CAT3_COEF("3rd category coefficient", 1.89);

    private final String title;
    private final double categoryCoefficient;

    CategoryCoefficient(String title,
             double categoryCoefficient) {
        this.title = title;
        this.categoryCoefficient = categoryCoefficient;
    }

    @Override
    public double getValue() {
        return categoryCoefficient;
    }

    @Override
    public String toString() {
        return title;
    }
}
