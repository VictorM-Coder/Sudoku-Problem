package org.ufc.sudoku.algorithms;

import org.ufc.sudoku.SudokuType;
import org.ufc.utils.SudokuDivider;

public class Backtracking implements SudokuSolver{
    private final int degree;
    private final int kRow;
    private final int kCol;

    public Backtracking(SudokuType sudokuType) {
        this.degree = sudokuType.getDegree();
        this.kRow = sudokuType.getBlockLineNumbers();
        this.kCol = sudokuType.getBlockColumnNumbers();
    }

    public boolean solve(Integer[] sudoku) {
        Integer[][] sudokuToBeSolved = SudokuDivider.dividirArray(sudoku.clone(), degree);
        return solveSudoku(sudokuToBeSolved, 0, 0);
    }

    private boolean solveSudoku(Integer[][] grid, int row, int col)
    {
        if (row == degree - 1 && col == degree)
            return true;

        if (col == degree) {
            row++;
            col = 0;
        }

        if (grid[row][col] != 0)
            return solveSudoku(grid, row, col + 1);

        for (int num = 1; num < degree + 1; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;
                if (solveSudoku(grid, row, col + 1))
                    return true;
            }
            grid[row][col] = 0;
        }
        return false;
    }

    private boolean isSafe(Integer[][] grid, int row, int col, int num)
    {
        for (int x = 0; x < degree; x++)
            if (grid[row][x] == num)
                return false;

        for (int x = 0; x < degree; x++)
            if (grid[x][col] == num)
                return false;

        int startRow = row - row % kRow;
        int startCol = col - col % kCol;
        for (int i = 0; i < kRow; i++)
            for (int j = 0; j < kCol; j++)
                if (grid[i + startRow][j + startCol] == num)
                    return false;

        return true;
    }
}
