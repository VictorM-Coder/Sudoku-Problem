package org.ufc.algorithms;

public class SudokuType {
    private int degree;
    private int blockLineNumbers;
    private int blockColumnNumbers;

    public SudokuType(int degree, int blockLineNumbers, int blockColumnNumbers) {
        this.degree = degree;
        this.blockLineNumbers = blockLineNumbers;
        this.blockColumnNumbers = blockColumnNumbers;
    }

    public int getDegree() {
        return degree;
    }

    public int getBlockLineNumbers() {
        return blockLineNumbers;
    }

    public int getBlockColumnNumbers() {
        return blockColumnNumbers;
    }
}
