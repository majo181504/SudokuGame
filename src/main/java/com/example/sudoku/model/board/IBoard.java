package com.example.sudoku.model.board;

public interface IBoard {
    boolean isValid(int row, int col, int value);
    void setCell(int row, int col, int value);
    int getCell(int row, int col);
}
