package org.ufc;


import org.ufc.utils.SudokuReader;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] vertexColor = new Integer[16];
        SudokuReader.readSudokuFile("sudokus/4x4/difficult/sudoku4x4_difficult_1", vertexColor);
        System.out.println(Arrays.toString(vertexColor));
    }
}