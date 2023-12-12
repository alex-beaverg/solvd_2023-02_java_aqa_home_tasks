package com.solvd.hospital_project;

import com.solvd.hospital_project.hospital.util.ConsoleMenu;
import com.solvd.hospital_project.task_0512.ClassInformation;
import com.solvd.hospital_project.task_0512.Reflection;
import com.solvd.hospital_project.task_0812.MultiThreading;

/**
 * Ideal main method!!!
 */
public class Main {
    public static void main(String[] args) {
        // Run Hospital application:
//        new ConsoleMenu().runApp();
        // Class info task:
        new ClassInformation().runClassInformation();
        // Reflection task:
        new Reflection().runReflection();
        // Multithreading task:
        new MultiThreading().runMultiThreading();
    }
}