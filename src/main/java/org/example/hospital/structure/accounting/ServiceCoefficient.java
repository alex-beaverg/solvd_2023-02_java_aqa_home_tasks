package org.example.hospital.structure.accounting;

public enum ServiceCoefficient implements IGetValue {
    SC1_CAT1_COEF("1st specialist class with 1st category coefficient", 1.01),
    SC1_CAT2_COEF("1st specialist class with 2nd category coefficient", 1.09),
    SC1_CAT3_COEF("1st specialist class with 3rd category coefficient", 1.17),
    SC2_CAT1_COEF("2nd specialist class with 1st category coefficient", 1.09),
    SC2_CAT2_COEF("2nd specialist class with 2nd category coefficient", 1.33),
    SC2_CAT3_COEF("2nd specialist class with 3rd category coefficient", 1.57),
    SC3_CAT1_COEF("3rd specialist class with 1st category coefficient", 1.33),
    SC3_CAT2_COEF("3rd specialist class with 2nd category coefficient", 1.81),
    SC3_CAT3_COEF("3rd specialist class with 3rd category coefficient", 2.29);

    private final String title;
    private final double serviceCoefficient;

    ServiceCoefficient(String title,
                        double serviceCoefficient) {
        this.title = title;
        this.serviceCoefficient = serviceCoefficient;
    }

    @Override
    public double getValue() {
        return serviceCoefficient;
    }

    @Override
    public String toString() {
        return title;
    }
}
