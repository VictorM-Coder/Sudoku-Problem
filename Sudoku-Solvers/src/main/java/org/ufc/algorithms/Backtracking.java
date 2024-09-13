package org.ufc.algorithms;

public class Backtracking {
    private final int N;
    private final int kRow;
    private final int kCol;

    public Backtracking(SudokuType sudokuType) {
        this.N = sudokuType.getDegree();
        this.kRow = sudokuType.getBlockLineNumbers();
        this.kCol = sudokuType.getBlockColumnNumbers();
    }

    public boolean solveSudoku(Integer grid[][], int row, int col)
    {
        if (row == N - 1 && col == N)
            return true;

        if (col == N) {
            row++;
            col = 0;
        }

        if (grid[row][col] != 0)
            return solveSudoku(grid, row, col + 1);

        for (int num = 1; num < N + 1; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;
                if (solveSudoku(grid, row, col + 1))
                    return true;
            }
            grid[row][col] = 0;
        }
        return false;
    }

    /* A utility function to print grid */
    public void print(Integer[][] grid)
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(grid[i][j] + " ");
            System.out.println();
        }
    }

    boolean isSafe(Integer[][] grid, int row, int col, int num)
    {
        for (int x = 0; x < N; x++)
            if (grid[row][x] == num)
                return false;

        for (int x = 0; x < N; x++)
            if (grid[x][col] == num)
                return false;

        int startRow = row - row % kRow, startCol = col - col % kCol;
        for (int i = 0; i < kRow; i++)
            for (int j = 0; j < kCol; j++)
                if (grid[i + startRow][j + startCol] == num)
                    return false;

        return true;
    }
}
