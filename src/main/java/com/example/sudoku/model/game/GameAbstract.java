package com.example.sudoku.model.game;

import com.example.sudoku.model.board.Board;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class GameAbstract implements IGame {
    protected GridPane boardGridPane;
    protected Board board;
    protected ArrayList<TextField> cells;

    public GameAbstract(GridPane boardGridPane){
        this.boardGridPane = boardGridPane;
        this.board = new Board();
        this.cells = new ArrayList<>();
    }

    @Override
    public void startGame() {}
    @Override
    public void resetGame() {
        boardGridPane.getChildren().clear();
        board = new Board();
        cells.clear();
    }
}
