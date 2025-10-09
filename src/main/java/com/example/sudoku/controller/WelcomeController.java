package com.example.sudoku.controller;

import com.example.sudoku.model.user.User;
import com.example.sudoku.view.SudokuGameStage;
import com.example.sudoku.view.SudokuWelcomeStage;
import com.example.sudoku.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {
    @FXML
    private void handlePlaay(ActionEvent event) throws IOException {
        SudokuWelcomeStage.getInstance().getController();
        WelcomeStage.deleteInstance();
    }
}
