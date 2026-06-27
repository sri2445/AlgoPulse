package com.example.demo;

public class SortingStep {
    private int[] currentArray;
    private int indexA;
    private int indexB;
    private String type; // "COMPARE" or "SWAP"

    public SortingStep(int[] currentArray, int indexA, int indexB, String type) {
        this.currentArray = currentArray;
        this.indexA = indexA;
        this.indexB = indexB;
        this.type = type;
    }

    public int[] getCurrentArray() { return currentArray; }
    public int getIndexA() { return indexA; }
    public int getIndexB() { return indexB; }
    public String getType() { return type; }
}
