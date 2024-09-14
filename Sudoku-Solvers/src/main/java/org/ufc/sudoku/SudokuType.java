package org.ufc.sudoku;

public final class SudokuType {
    private int degree;
    private int blockLineNumbers;
    private int blockColumnNumbers;

    public static SudokuType S4 = new SudokuType(4, 2, 2);
    public static SudokuType S6 = new SudokuType(6, 2, 3);
    public static SudokuType S8 = new SudokuType(8, 2, 4);
    public static SudokuType S9 = new SudokuType(9, 3, 3);
    public static SudokuType S10 = new SudokuType(10, 2, 5);
    public static SudokuType S12 = new SudokuType(12, 3, 4);

    private SudokuType(int degree, int blockLineNumbers, int blockColumnNumbers) {
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
