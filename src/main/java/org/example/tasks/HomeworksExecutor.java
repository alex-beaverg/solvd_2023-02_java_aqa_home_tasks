package org.example.tasks;

import java.util.Arrays;

/**
 * Class to executing homeworks
 */
public class HomeworksExecutor {
    public static void executeHW01() {

        // Homework 01-1 (31.10.2023) - Hello world:
        System.out.println("Hello world!");

        // Homework 01-2 (31.10.2023) - Insertion sort:
        int[] array = new int[]{10, 23, 5, 11, 7, 100, 45, 1, 33, 18};
        System.out.println("Unsorted array: " + Arrays.toString(array));
        Homework01.insertionSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));
    }
}
