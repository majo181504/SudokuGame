package com.example.sudoku.model.game;
/**
 * Interface defining the core behaviors of a Sudoku game.
 * Provides methods for starting, refreshing, and resetting the game.
 */
public interface IGame {
    void startGame();
    void refreshBoard();
    void resetGame();
    void undoMove();
}
