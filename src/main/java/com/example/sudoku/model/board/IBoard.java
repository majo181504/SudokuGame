package com.example.sudoku.model.board;

public interface IBoard {
    boolean fillBlocks(int blockIndex);
    boolean isValid(int row, int col, int candidate);
}
