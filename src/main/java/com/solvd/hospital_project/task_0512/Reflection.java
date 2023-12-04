package com.solvd.hospital_project.task_0512;

import static com.solvd.hospital_project.hospital.util.Printers.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {
    private final String EXP_CLASS_NAME = "com.solvd.hospital_project.task_0512.ExperimentalClass";
    private final Class<ExperimentalClass> EXP_CLASS = (Class<ExperimentalClass>) getMyClass(EXP_CLASS_NAME);

    public void runReflection() {
        PRINT2LN.info("Reflection:\n");
        try {
            // Getting constructor with 4 parameters:
            Constructor<ExperimentalClass> expConstructor = EXP_CLASS
                    .getDeclaredConstructor(String.class, int.class, double.class, long.class);
            // Creating instance using constructor with 4 parameters:
            ExperimentalClass expInstance = expConstructor.newInstance("Hello", 100, 1.1, 5000L);
            // Getting private method printData():
            Method printData = EXP_CLASS.getDeclaredMethod("printData");
            // Getting access to private method printData():
            printData.setAccessible(true);
            // Invoking private method printData():
            PRINTLN.info("Results of invoking private method printData():");
            printData.invoke(expInstance);
            // Getting public method toString():
            Method toStringMethod = EXP_CLASS.getDeclaredMethod("toString");
            // Invoking public method toString():
            PRINT2LN.info("Results of invoking public method toString():");
            PRINTLN.info(toStringMethod.invoke(expInstance));
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Class<?> getMyClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
