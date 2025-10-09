package com.example.sudoku.controller;

import com.example.sudoku.model.user.User;
import com.example.sudoku.model.game.Game;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SudokuGameController {
    @FXML
    private GridPane boardGridPane;
    private Game game;

    @FXML
    public void initialize() {
      game= new Game(boardGridPane);
      game.startGame();
    }

    @FXML
    private void handleUndo(){
        game.undoMove();
    }

    @FXML
    private void handleHelp(){
        System.out.println("mostar ayuda del jeugo");
    }

    private User user;
    public void setUser(User user){this.user = user;}
}
