package com.example.sudoku.model.board;
/**
 * Interface representing the basic operations of a Sudoku board.
 * Defines methods to validate moves and manage cell values.
 */
public interface IBoard {
    boolean isValid(int row, int col, int value);
    void setCell(int row, int col, int value);
    int getCell(int row, int col);
}
