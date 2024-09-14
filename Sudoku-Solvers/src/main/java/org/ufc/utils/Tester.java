package org.ufc.utils;

import org.ufc.algorithms.Backtracking;
import org.ufc.algorithms.SaturBFS;
import org.ufc.algorithms.SudokuType;

public final class Tester {
    private static int MAX_ATP = 1;

    private Tester() {}

    public static void test() {
        System.out.println("===============================BACKTRACKING===============================");
        testBacktracking(SudokuType.S4);
        testBacktracking(SudokuType.S6);
        testBacktracking(SudokuType.S8);
        testBacktracking(SudokuType.S9);
        testBacktracking(SudokuType.S10);
        testBacktracking(SudokuType.S12);

        System.out.println("\n\n");
//
        System.out.println("===============================SATURBFS===============================");
        testSaturBFS(SudokuType.S4);
        testSaturBFS(SudokuType.S6);
        testSaturBFS(SudokuType.S8);
        testSaturBFS(SudokuType.S9);
        testSaturBFS(SudokuType.S10);
        testSaturBFS(SudokuType.S12);
    }

    private static void testSaturBFS(SudokuType type) {
        System.out.println("-------------------Sudoku " + type.getDegree() + "-------------------");
        testSudokuBFSByDifficult(type, Difficult.EASY);
        testSudokuBFSByDifficult(type, Difficult.HARD);
    }

    private static void testBacktracking(SudokuType type) {
        System.out.println("-------------------Sudoku " + type.getDegree() + "-------------------");
        testSudokuBacktrackingByDifficult(type, Difficult.EASY);
        testSudokuBacktrackingByDifficult(type, Difficult.HARD);
    }

    private static void testSudokuBacktrackingByDifficult(SudokuType type, Difficult difficult) {
        Integer[] sudoku = SudokuReader.readSudokuFile(type, difficult);

        Backtracking backtracking = new Backtracking(type);
        Integer[][] sudokuToBeSolved = SudokuDivider.dividirArray(sudoku, type.getDegree());

        ExecutionResult executionResult = TimeCalculator.calcularTempoDeExecucao(
                () -> backtracking.solveSudoku(sudokuToBeSolved, 0, 0));

        System.out.printf("Tempo de execucao %s: %.16f \n", difficult.name() , executionResult.timeExpended());
    }

    private static void testSudokuBFSByDifficult(SudokuType type, Difficult difficult) {
        Integer[] sudoku = SudokuReader.readSudokuFile(type, difficult);
        double totalTimeExpended = 0;
        int totalSudokuSolveds = 0;

        SaturBFS saturBFS = new SaturBFS(type);

        for (int cont = 1; cont <= MAX_ATP; cont++) {
            ExecutionResult executionResult = TimeCalculator.calcularTempoDeExecucao(
                    () -> saturBFS.saturBFS(sudoku.clone())
            );

//            System.out.printf("%.16f \n", executionResult.timeExpended());
            totalTimeExpended += executionResult.timeExpended();
            totalSudokuSolveds += executionResult.solved() ? 1: 0;
        }

        System.out.printf("Media do tempo de execucao %s: %.16f | Precisao: %.2f %n",
                difficult.name() , totalTimeExpended/MAX_ATP, (totalSudokuSolveds*100.0)/MAX_ATP);
    }
}
