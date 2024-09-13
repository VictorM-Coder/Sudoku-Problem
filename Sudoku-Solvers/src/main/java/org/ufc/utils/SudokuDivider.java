package org.ufc.utils;

public class SudokuDivider {
    public static Integer[][] dividirArray(Integer[] array, int tamanhoLinha) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = 0;
            }
        }

        int linhas = array.length / tamanhoLinha;
        Integer[][] resultado = new Integer[linhas][tamanhoLinha];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < tamanhoLinha; j++) {
                resultado[i][j] = array[i * tamanhoLinha + j];
            }
        }

        return resultado;
    }
}
