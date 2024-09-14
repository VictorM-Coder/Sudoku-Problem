package org.ufc.execution;

import org.ufc.sudoku.algorithms.Backtracking;
import org.ufc.sudoku.algorithms.SaturBFS;
import org.ufc.sudoku.algorithms.SudokuSolver;
import org.ufc.sudoku.Difficult;
import org.ufc.sudoku.SudokuType;
import org.ufc.utils.SudokuReader;

public final class Tester {
    private static final int MAX_ATP = 15_000;

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

        System.out.println("===============================SATURBFS===============================");
        testSaturBFS(SudokuType.S4);
        testSaturBFS(SudokuType.S6);
        testSaturBFS(SudokuType.S8);
        testSaturBFS(SudokuType.S9);
        testSaturBFS(SudokuType.S10);
        testSaturBFS(SudokuType.S12);
    }

    private static void testSaturBFS(SudokuType type) {
        System.out.println("-------------------------------Sudoku " + type.getDegree() + "-------------------------------");
        testSudokuBFSByDifficult(type, Difficult.EASY);
        testSudokuBFSByDifficult(type, Difficult.MEDIUM);
        testSudokuBFSByDifficult(type, Difficult.HARD);
    }

    private static void testBacktracking(SudokuType type) {
        System.out.println("-------------------------------Sudoku " + type.getDegree() + "-------------------------------------------");
        testSudokuBacktrackingByDifficult(type, Difficult.EASY);
        testSudokuBacktrackingByDifficult(type, Difficult.MEDIUM);
        testSudokuBacktrackingByDifficult(type, Difficult.HARD);
    }

    private static void testSudokuBacktrackingByDifficult(SudokuType type, Difficult difficult) {
        testSudokuDifficult(new Backtracking(type), type, difficult);
    }

    private static void testSudokuBFSByDifficult(SudokuType type, Difficult difficult) {
        testSudokuDifficult(new SaturBFS(type), type, difficult);
    }

    private static void testSudokuDifficult(SudokuSolver sudokuSolver, SudokuType type, Difficult difficult) {
        Integer[] sudoku = SudokuReader.readSudokuFile(type, difficult);
        double totalTimeExpended = 0;
        int totalSudokuSolveds = 0;

        for (int cont = 1; cont <= MAX_ATP; cont++) {
            ExecutionResult executionResult = TimeCalculator.calcularTempoDeExecucao(
                    () -> sudokuSolver.solve(sudoku.clone())
            );

            totalTimeExpended += executionResult.timeExpended();
            totalSudokuSolveds += executionResult.solved() ? 1: 0;
        }

        printFeedback(difficult, totalTimeExpended, totalSudokuSolveds);
    }

    private static void printFeedback(Difficult difficult, double totalTimeExpended, int totalSudokuSolveds) {
        System.out.printf("Media do tempo de execucao %s: %.16f | Precisao: %.2f %n",
                difficult.name() , totalTimeExpended/MAX_ATP, (totalSudokuSolveds *100.0)/MAX_ATP);
    }
}
