package org.example;

import org.example.tasks.Homework01;

/**
 * Entry point
 */
public class Main {
    public static void main(String[] args) {

        // Homework 01-1 (31.10.2023) - Hello world:
        System.out.println("Hello world!");

        // Homework 01-2 (31.10.2023) - Insertion sort:
        int[] unsortedArray = new int[]{10, 23, 2, 11, 7, 100, 45, 1, 33, 18};
        Homework01 homeWork01 = new Homework01(unsortedArray);
        homeWork01.insertionSortAndPrintResult();
    }
}