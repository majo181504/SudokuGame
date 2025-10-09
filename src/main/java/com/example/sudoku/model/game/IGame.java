package com.example.sudoku.model.game;

public interface IGame {
    void startGame();
    void refreshBoard();
    void undoMove();
    void resetGame();
}
