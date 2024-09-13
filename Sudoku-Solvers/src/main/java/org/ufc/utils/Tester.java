package org.ufc.utils;

import org.ufc.algorithms.Backtracking;
import org.ufc.algorithms.SudokuType;

public final class Tester {


    private Tester() {}

    public static void test() {
        testSudoku(SudokuType.S4);
        testSudoku(SudokuType.S6);
        testSudoku(SudokuType.S8);
        testSudoku(SudokuType.S9);
        testSudoku(SudokuType.S10);
        testSudoku(SudokuType.S12);
    }

    private static void testSudoku(SudokuType type) {
        System.out.println("-------------------Sudoku " + type.getDegree() + "-------------------");
        testSudokuByDifficult(type, Difficult.EASY);
        testSudokuByDifficult(type, Difficult.HARD);
    }

    private static void testSudokuByDifficult(SudokuType type, Difficult difficulty) {
        Integer[] sudoku = SudokuReader.readSudokuFile(type, difficulty);

        Backtracking backtracking = new Backtracking(type);
        Integer[][] sudokuToBeSolved = SudokuDivider.dividirArray(sudoku, type.getDegree());

        double tempoDeExecucaoEasy = TimeCalculator.calcularTempoDeExecucao(
                () -> backtracking.solveSudoku(sudokuToBeSolved, 0, 0));

        System.out.printf("Tempo de execucao %s: %.16f \n", difficulty.name() , tempoDeExecucaoEasy);
    }
}
