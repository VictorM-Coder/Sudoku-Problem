package org.ufc;


import org.ufc.utils.SudokuReader;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] vertexColor = new Integer[144];
        SudokuReader.readSudokuFile("sudokus/12x12/12x12_easy", vertexColor);
        System.out.println(Arrays.toString(vertexColor));
    }
}