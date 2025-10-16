package com.example.sudoku.controller;

import com.example.sudoku.view.SudokuWelcomeStage;
import com.example.sudoku.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

/**
 * Controller class for the welcome view of the Sudoku application.
 * Handles user interactions and transitions to the SudokuWelcomeStage to enter the user nickname.
 */
public class WelcomeController {
    /**
     * Handles the action when the "PLAY" button is pressed.
     * This method transitions the application to the SudokuWelcomeStage where the user can enter their username
     * @param event
     * @throws IOException
     */
    @FXML
    private void handlePlaay(ActionEvent event) throws IOException {
        SudokuWelcomeStage.getInstance().getController();
        WelcomeStage.deleteInstance();
    }
}
