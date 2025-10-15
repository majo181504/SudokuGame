package com.example.sudoku.controller;

import com.example.sudoku.model.user.User;
import com.example.sudoku.model.game.Game;
import com.example.sudoku.view.FinalStage;
import com.example.sudoku.view.SudokuGameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;


import java.io.IOException;

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
    private void handleHelp(){game.helpHints();};

    @FXML
    private void handleExit(ActionEvent event) throws IOException {
        FinalStage.getInstance().getController();
        SudokuGameStage.deleteInstance();
    }

    private User user;
    public void setUser(User user){this.user = user;}
}
