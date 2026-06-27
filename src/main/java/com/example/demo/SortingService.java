package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class SortingService {

    public List<SortingStep> bubbleSort(int[] array) {
        List<SortingStep> steps = new ArrayList<>();
        int n = array.length;
        int[] current = array.clone();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                steps.add(new SortingStep(current.clone(), j, j + 1, "COMPARE"));
                if (current[j] > current[j + 1]) {
                    int temp = current[j];
                    current[j] = current[j + 1];
                    current[j + 1] = temp;
                    steps.add(new SortingStep(current.clone(), j, j + 1, "SWAP"));
                }
            }
        }
        return steps;
    }

    public List<SortingStep> selectionSort(int[] array) {
        List<SortingStep> steps = new ArrayList<>();
        int n = array.length;
        int[] current = array.clone();

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                steps.add(new SortingStep(current.clone(), j, minIdx, "COMPARE"));
                if (current[j] < current[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = current[minIdx];
            current[minIdx] = current[i];
            current[i] = temp;
            steps.add(new SortingStep(current.clone(), i, minIdx, "SWAP"));
        }
        return steps;
    }

    public List<SortingStep> insertionSort(int[] array) {
        List<SortingStep> steps = new ArrayList<>();
        int n = array.length;
        int[] current = array.clone();

        for (int i = 1; i < n; ++i) {
            int key = current[i];
            int j = i - 1;

            steps.add(new SortingStep(current.clone(), i, j, "COMPARE"));
            while (j >= 0 && current[j] > key) {
                current[j + 1] = current[j];
                steps.add(new SortingStep(current.clone(), j + 1, j, "SWAP"));
                j = j - 1;
            }
            current[j + 1] = key;
            steps.add(new SortingStep(current.clone(), j + 1, i, "SWAP"));
        }
        return steps;
    }

    public List<SortingStep> mergeSort(int[] array) {
        List<SortingStep> steps = new ArrayList<>();
        int[] current = array.clone();
        runMergeSort(current, 0, current.length - 1, steps);
        return steps;
    }

    private void runMergeSort(int[] arr, int l, int r, List<SortingStep> steps) {
        if (l < r) {
            int m = l + (r - l) / 2;
            runMergeSort(arr, l, m, steps);
            runMergeSort(arr, m + 1, r, steps);
            merge(arr, l, m, r, steps);
        }
    }

    private void merge(int[] arr, int l, int m, int r, List<SortingStep> steps) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[m + 1 + j];

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            steps.add(new SortingStep(arr.clone(), l + i, m + 1 + j, "COMPARE"));
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            steps.add(new SortingStep(arr.clone(), k, k, "SWAP"));
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            steps.add(new SortingStep(arr.clone(), k, k, "SWAP"));
            i++; k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            steps.add(new SortingStep(arr.clone(), k, k, "SWAP"));
            j++; k++;
        }
    }
}
