package com.solvd.hospital_project.task_0512;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static com.solvd.hospital_project.hospital.util.Printers.*;

public class ClassInformation {
    private final String EXP_CLASS_NAME = "com.solvd.hospital_project.task_0512.ExperimentalClass";
    private final Class<?> EXP_CLASS = getMyClass(EXP_CLASS_NAME);

    public void runClassInformation() {
        PRINT2LN.info("Classes:");
        PRINTLN.info("");

        // Class:
        PRINTLN.info("Simple class name: " + EXP_CLASS.getSimpleName());
        PRINTLN.info("Full class name: " + EXP_CLASS.getName());
        PRINTLN.info("Package name: " + EXP_CLASS.getPackageName());
        PRINTLN.info("");

        // Methods:
        Method[] expClassMethods = getMyMethods(EXP_CLASS);
        PRINTLN.info("Number of methods: " + Arrays.stream(expClassMethods).count());
        PRINTLN.info("");
        PRINTLN.info("Method access modifiers: ");
        Arrays.stream(expClassMethods)
                .forEach(method -> PRINTLN.info("\t- " + getAccessModifierAsString(method.getModifiers()) +
                        " (" + method.getName() + ")"));
        PRINTLN.info("");
        PRINTLN.info("Method return types: ");
        Arrays.stream(expClassMethods)
                .forEach(method -> PRINTLN.info("\t- " + method.getReturnType().getSimpleName() +
                        " (" + method.getName() + ")"));
        PRINTLN.info("");
        PRINTLN.info("Method parameter types: ");
        Arrays.stream(expClassMethods)
                .forEach(method -> PRINTLN.info("\t- " + Arrays.toString(Arrays.stream(method.getParameterTypes())
                                .map(Class::getSimpleName)
                                .toArray()) + " (" + method.getName() + ")"));
        PRINTLN.info("");

        // Fields:
        Field[] expClassFields = getMyFields(EXP_CLASS);
        PRINTLN.info("Number of fields: " + Arrays.stream(expClassFields).count());
        PRINTLN.info("");
        PRINTLN.info("Field access modifiers: ");
        Arrays.stream(expClassFields)
                .forEach(field -> PRINTLN.info("\t- " + getAccessModifierAsString(field.getModifiers()) +
                        " (" + field.getName() + ")"));
        PRINTLN.info("");
        PRINTLN.info("Field types: ");
        Arrays.stream(expClassFields)
                .forEach(field -> PRINTLN.info("\t- " + field.getType().getSimpleName() + " (" + field.getName() + ")"));
        PRINTLN.info("");
        PRINTLN.info("Static fields: ");
        Arrays.stream(expClassFields)
                .filter(field -> (field.getModifiers() & Modifier.STATIC) > 0)
                .forEach(field -> PRINTLN.info("\t- " + field.getName()));
        PRINTLN.info("");
        PRINTLN.info("Final fields: ");
        Arrays.stream(expClassFields)
                .filter(field -> (field.getModifiers() & Modifier.FINAL) > 0)
                .forEach(field -> PRINTLN.info("\t- " + field.getName()));
        PRINTLN.info("");

        // Constructors:
        Constructor<?>[] expClassConstructors = getMyConstructors(EXP_CLASS);
        PRINTLN.info("Number of constructors: " + Arrays.stream(expClassConstructors).count());
        PRINTLN.info("");
        PRINTLN.info("Constructor access modifiers: ");
        AtomicInteger index = new AtomicInteger();
        Arrays.stream(expClassConstructors)
                .forEach(constructor -> PRINTLN.info("\t- " + getAccessModifierAsString(constructor.getModifiers()) +
                        " (Constructor N" + index.incrementAndGet() + ")"));
        PRINTLN.info("");
        index.set(0);
        PRINTLN.info("Constructor parameter types: ");
        Arrays.stream(expClassConstructors)
                .forEach(constructor -> PRINTLN.info("\t- " + Arrays.toString(Arrays.stream(constructor.getParameterTypes())
                        .map(Class::getSimpleName)
                        .toArray()) + " (Constructor N" + (index.incrementAndGet()) + ")"));

        PRINTLN.info("");
    }

    private Class<?> getMyClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Method[] getMyMethods(Class<?> clazz) {
        return clazz.getDeclaredMethods();
    }

    private Field[] getMyFields(Class<?> clazz) {
        return clazz.getDeclaredFields();
    }

    private Constructor<?>[] getMyConstructors(Class<?> clazz) {
        return clazz.getDeclaredConstructors();
    }

    private String getAccessModifierAsString(int index) {
        if (index >= 1024) index -= 1024;
        if (index >= 512) index -= 512;
        if (index >= 256) index -= 256;
        if (index >= 128) index -= 128;
        if (index >= 64) index -= 64;
        if (index >= 32) index -= 32;
        if (index >= 16) index -= 16;
        if (index >= 8) index -= 8;

        switch (index) {
            case (0) -> {
                return "'default'";
            }
            case (1) -> {
                return "public";
            }
            case (2) -> {
                return "private";
            }
            case (4) -> {
                return "protected";
            }
        }
        return null;
    }
}
