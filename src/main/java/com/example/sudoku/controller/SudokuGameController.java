package com.example.sudoku.controller;

import com.example.sudoku.model.user.User;
import com.example.sudoku.model.game.Game;
import com.example.sudoku.view.FinalStage;
import com.example.sudoku.view.SudokuGameStage;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;


import java.io.IOException;

/**
 * Controller class for the Sudoku game screen.
 * This controller manages the interactions within the Sudoku game,
 * including starting a new game, handling user moves, providing hints,
 * and managing the game's state.
 */
public class SudokuGameController {
    @FXML
    private GridPane boardGridPane;
    private Game game;

    /**
     * Initializes the controller.
     * This method is called automatically after the FXML file has been loaded. It sets up the game board and starts a new game.
     */
    @FXML
    public void initialize() {
      game= new Game(boardGridPane);
      game.startGame();
    }

    /**
     * Handles the action when the "AYUDA" button is pressed.
     * This method provides hints to the player.
     */
    @FXML
    private void handleHelp(){
        game.helpHints();}

    /**
     * Handles the action when the "SALIR" button is pressed.
     * This method exits the current game and returns to the final screen.
     * @throws IOException
     */
    @FXML
    private void handleExit() throws IOException {
        FinalStage.getInstance().getController();
        SudokuGameStage.deleteInstance();
    }


    private User user;

    /**
     * Sets the user for the game session.
     * @param user
     */
    public void setUser(User user){this.user = user;}
}
