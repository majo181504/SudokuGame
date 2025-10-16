package com.example.sudoku.model.game;

import com.example.sudoku.model.board.Board;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

/**
 * Abstract base class for Sudoku game implementations.
 * This class provides common functionality for managing the Sudoku board,
 * handling game reset and refresh operations, and maintaining references
 * to the board model and its visual representation.
 * It implements the {@link IGame} interface and serves as a foundation
 * for concrete game classes such as {@code Game}, which define specific
 * game logic and user interaction behavior.
 */

public abstract class GameAbstract implements IGame {
    /** The GridPane representing the Sudoku board in the UI. */
    protected GridPane boardGridPane;
    /** The model representing the logical structure of the Sudoku board. */
    protected Board board;
    /** A list containing all TextFields that represent the Sudoku cells. */
    protected ArrayList<TextField> cells;

    /**
     * Constructs a new GameAbstract instance.
     *
     * @param boardGridPane the GridPane where the Sudoku cells are displayed
     */

    public GameAbstract(GridPane boardGridPane) {
        this.boardGridPane = boardGridPane;
        this.board = new Board();
        this.cells = new ArrayList<>();
    }

    @Override
    public void undoMove(){
        board.undoLastMove();
        refreshBoard();
    }

    @Override
    public void resetGame() {
        board = new Board();
        refreshBoard();
    }

    @Override
    public void refreshBoard() {
        boardGridPane.getChildren().clear();
        startGame();
    }
}
