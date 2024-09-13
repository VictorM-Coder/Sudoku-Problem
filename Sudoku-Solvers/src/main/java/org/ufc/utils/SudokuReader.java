package org.ufc.utils;

import org.ufc.algorithms.SudokuType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public final class SudokuReader {
    private SudokuReader() {
    }

    public static List<Integer[]> readAllSudokus() {
        return List.of(
                readSudokuFile(SudokuType.S4, Difficult.EASY),
                readSudokuFile(SudokuType.S4, Difficult.HARD),

                readSudokuFile(SudokuType.S6, Difficult.EASY),
                readSudokuFile(SudokuType.S6, Difficult.HARD),

                readSudokuFile(SudokuType.S8, Difficult.EASY),
                readSudokuFile(SudokuType.S8, Difficult.HARD),

                readSudokuFile(SudokuType.S9, Difficult.EASY),
                readSudokuFile(SudokuType.S9, Difficult.HARD),

                readSudokuFile(SudokuType.S10, Difficult.EASY),
                readSudokuFile(SudokuType.S10, Difficult.HARD),

                readSudokuFile(SudokuType.S12, Difficult.EASY),
                readSudokuFile(SudokuType.S12, Difficult.HARD)
        );
    }

    public static Integer[] readSudokuFile(SudokuType sudokuType, Difficult difficult) {
        int n = sudokuType.getDegree();
        Integer[] vertexColor = new Integer[n * n];
        int cont = 0;

        try (
                FileReader file = new FileReader(buildFileName(n, difficult));
                BufferedReader bufferedReader = new BufferedReader(file)
        ) {
            String line = bufferedReader.readLine();
            String[] values;

            while ((line != null) && !(line.isEmpty())) {
                values = line.split("-");

                for (String s : values) {
                    int value = Integer.parseInt(s.trim());
                    if (value != 0) {
                        vertexColor[cont] = value;
                    }
                    cont++;
                }
                line = bufferedReader.readLine();
            }

            return vertexColor;
        } catch (IOException e) {
            return new Integer[0];
        }
    }

    private static String buildFileName(int n, Difficult difficult) {
        String difficultString =
                (difficult == Difficult.EASY) ? "easy" : "hard";

        return "sudokus/" + n + "x" + n + "/" + n + "x" + n + "_" +
                difficultString + ".txt";
    }
}
