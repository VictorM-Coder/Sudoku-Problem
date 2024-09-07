package org.ufc.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class SudokuReader {
    private SudokuReader() {}
    public static void readSudokuFile(String nomeArquivo, Integer[] vertexColor) {
        int cont = 0;

        try (
                FileReader file = new FileReader(nomeArquivo + ".txt");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
