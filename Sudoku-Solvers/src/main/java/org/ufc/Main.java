package org.ufc;


import org.ufc.algorithms.Backtracking;
import org.ufc.algorithms.SudokuType;
import org.ufc.utils.SudokuReader;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static Integer[][] dividirArray(Integer[] array, int tamanhoLinha) {
        int linhas = array.length / tamanhoLinha;
        Integer[][] resultado = new Integer[linhas][tamanhoLinha];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < tamanhoLinha; j++) {
                resultado[i][j] = array[i * tamanhoLinha + j];
            }
        }

        return resultado;
    }

    public static void main(String[] args) {
        SudokuType S4 = new SudokuType(4, 2, 2);
        SudokuType S6 = new SudokuType(6, 2, 3);
        SudokuType S8 = new SudokuType(8, 2, 4);
        SudokuType S9 = new SudokuType(9, 3, 3);
        SudokuType S10 = new SudokuType(10, 2, 5);
        SudokuType S12 = new SudokuType(12, 3, 4);

        int N = S12.getDegree();
        int kRow = S12.getBlockLineNumbers();
        int kCol = S12.getBlockColumnNumbers();

        Integer[] vertexColor = new Integer[N*N];
        SudokuReader.readSudokuFile("sudokus/"+ N + "x" + N + "/"+ N + "x" + N + "_hard", vertexColor);
        System.out.println(Arrays.toString(vertexColor));

        // Lógica para o backtracking funcionar
        for (int i = 0; i < vertexColor.length; i++) {
            if (vertexColor[i] == null) {
                vertexColor[i] = 0;
            }
        }

        Backtracking bt = new Backtracking(N, kRow, kCol);

        Integer[][] novoArray = dividirArray(vertexColor, N);

        if (bt.solveSudoku(novoArray, 0, 0)) {
            bt.print(novoArray);
        } else {
            System.out.println("No Solution exists");
        }
    }
}