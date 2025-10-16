package com.example.sudoku.controller;

import com.example.sudoku.model.user.User;
import com.example.sudoku.view.FinalStage;
import com.example.sudoku.view.WelcomeStage;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

import java.io.IOException;


/**
 * Controller for the final screen of the Sudoku game.
 * This class manages the UI elements and interactions on the final screen,
 * including displaying a message with the user's nickname and handling navigation back to the welcome screen.
 */
public class FinalController {
    @FXML
    private Text changeText;

    @FXML
    private Button backButton;

    /**
     * Sets the text message to include the user's nickname.
     * This method retrieves the nickname from the User singleton instance and updates the changeText Text element
     */
    private void setName() {
        User userNickname = User.getInstance();
        String nameUser = userNickname.getNickname();

        changeText.setText("Has salido del juego "+nameUser);
    }

    /**
     * Handles the action when the "VOLVER" button is pressed.
     * This method closes the current FinalStage and opens the main WelcomeStage.
     * @throws IOException
     */
    @FXML
    private void handleBack() throws IOException {
        FinalStage.deleteInstance();
        WelcomeStage.getInstance();
    }

    /**
     * Initializes the controller.
     * This method is called automatically after the FXML file has been loaded. It sets the user's nickname in the text message.
     */
    @FXML
    public void initialize(){
        setName();
    }
}
