package com.example.sudoku.model.game;

import com.example.sudoku.model.board.Board;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public abstract class GameAbstract implements IGame {
    protected GridPane boardGridPane;
    protected Board board;
    protected ArrayList<TextField> cells;

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
