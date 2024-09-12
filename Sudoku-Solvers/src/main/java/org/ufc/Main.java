package org.ufc;


import org.ufc.algorithms.Backtracking;
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
        Integer[] vertexColor = new Integer[81];
        SudokuReader.readSudokuFile("sudokus/9x9/9x9_easy", vertexColor);
        System.out.println(Arrays.toString(vertexColor));

        // Lógica para o backtracing funcionar
        for (int i = 0; i < vertexColor.length; i++) {
            if (vertexColor[i] == null) {
                vertexColor[i] = 0;
            }
        }

        int N = 9;
        int k = 3;

        Backtracking bt = new Backtracking(N, k);

        Integer[][] novoArray = dividirArray(vertexColor, N);

        if (bt.solveSudoku(novoArray, 0, 0)) {
            bt.print(novoArray);
        } else {
            System.out.println("No Solution exists");
        }
    }
}