package org.example.tasks;

import java.util.Arrays;

/**
 * Homework 01-2 (31.10.2023) - Insertion sort
 */
public class Homework01 {
    private final int[] arrayToSort;

    public Homework01(int[] arrayToSort) {
        this.arrayToSort = arrayToSort;
    }

    public void insertionSortAndPrintResult() {
        // Print array before sorting:
        printArray();

        // Insertion sort:
        int j;
        for (int i = 1; i < this.arrayToSort.length; i++) {
            int comparedElement = this.arrayToSort[i];
            for (j = i; j > 0 && comparedElement < this.arrayToSort[j - 1]; j--) {
                this.arrayToSort[j] = this.arrayToSort[j - 1];
            }
            this.arrayToSort[j] = comparedElement;
        }

        // Print array after sorting:
        printArray();
    }

    private void printArray() {
        System.out.println(Arrays.toString(this.arrayToSort));
    }
}
