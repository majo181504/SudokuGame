package com.example.sudoku.controller;

import com.example.sudoku.view.VictoryStage;
import com.example.sudoku.view.WelcomeStage;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * Controller class for the Victory screen.
 * This controller handles user interactions after completing the Sudoku game.
 * It manages the behavior of the "VOLVER" button, which allows the user
 * to return to the main welcome screen.
 *
 */
public class VictoryController {
    /**
     * Handles the action when the "VOLVER" button is pressed.
     * This method closes the current VictoryStage and opens the main
     * WelcomeStage where the player can start a new game or exit.
     *
     * @param event The action event triggered by clicking the button.
     * @throws IOException If there is an issue loading the welcome screen FXML.
     */
    @FXML
    private void handleStar(ActionEvent event) throws IOException {
        // Close the victory window
        VictoryStage.deleteInstance();

        // Open the main welcome screen
        WelcomeStage.getInstance();
    }
}
