package org.example.tasks;

/**
 * Homework 01-2 (31.10.2023) - Insertion sort
 */
public class Homework01 {
    public static void insertionSort(int[] array) {
        int j;
        for (int i = 1; i < array.length; i++) {
            int comparedElement = array[i];
            for (j = i; j > 0 && comparedElement < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            array[j] = comparedElement;
        }
    }
}
