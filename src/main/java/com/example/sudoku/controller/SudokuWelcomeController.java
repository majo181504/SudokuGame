package com.example.sudoku.controller;

import com.example.sudoku.model.user.User;
import com.example.sudoku.model.utils.AlertBox;
import com.example.sudoku.view.SudokuGameStage;
import com.example.sudoku.view.SudokuWelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.IOException;

/**
 * Controller class for the Sudoku welcome screen.
 * This controller manages user interactions on the welcome screen,
 * including handling the nickname input and starting the game.
 */
public class SudokuWelcomeController {
    @FXML
    private TextField nicknameTxt;

    /**
     * Initializes the controller.
     * This method is called automatically after the FXML file has been loaded.
     * It sets up the event handler for the nickname text field to start the game when the Enter key is pressed.
     */
    @FXML
    void initialize() {
        nicknameTxt.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    handlePlay(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Handles the action when the "INICIAR" button is pressed or Enter key is hit.
     * This method retrieves the nickname from the text field, validates it,
     * and starts the Sudoku game if the nickname is valid. If the nickname is empty,
     * it shows an error alert.
     * @throws IOException
     */
    @FXML
    void handlePlay(ActionEvent event) throws IOException {
        String nickname = nicknameTxt.getText().trim();
        User.getInstance().setNickname(nickname);

        if (nickname.equals("")){
            new AlertBox().showAlert("Error", "Ingresa un nickname", Alert.AlertType.ERROR);
        } else{
            SudokuGameStage.getInstance().getController().setUser(new User(nickname));
            SudokuWelcomeStage.deleteInstance();
        }


    }
}
