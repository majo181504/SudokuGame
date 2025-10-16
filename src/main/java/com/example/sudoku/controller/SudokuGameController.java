package com.example.sudoku.controller;

import com.example.sudoku.model.board.Board;
import com.example.sudoku.model.user.User;
import com.example.sudoku.model.game.Game;
import com.example.sudoku.view.FinalStage;
import com.example.sudoku.view.SudokuGameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
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
    private Board board;

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
    private void handleHelp(){game.helpHints();};

    /**
     * Handles the action when the "SALIR" button is pressed.
     * This method exits the current game and returns to the final screen.
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleExit(ActionEvent event) throws IOException {
        FinalStage.getInstance().getController();
        SudokuGameStage.deleteInstance();
    }

    /**
     * Handles the action when the "DESHACER" button is pressed.
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleUndo(ActionEvent event) throws IOException {    // Undo the last move (button)
        game.undoMove();
        refreshBoardUI();
    }

    /**
     * Helper method to get a node from the GridPane by its row and column indices.
     * @param row
     * @param column
     * @param gridPane
     * @return
     */
    private Node getNodeByRowColumnIndex(int row, int column, GridPane gridPane) { // Helper to get row/column node
        for (Node node : gridPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer colIndex = GridPane.getColumnIndex(node);
            if ((rowIndex == null ? 0 : rowIndex) == row && (colIndex == null ? 0 : colIndex) == column) {
                return node;
            }
        }
        return null;
    }

    /**
     * Refreshes the UI to reflect the current state of the game board.
     * This method updates the text fields in the grid pane to match the values in the game board model.
     */
    private void refreshBoardUI() {
        // It makes sure that the board is initialized
        if (board == null) board = game.getBoard();
        int size = board.getSize();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int modelValue = board.getValue(row, col);
                Node node = getNodeByRowColumnIndex(row, col, boardGridPane);
                if (node instanceof TextField) {
                    TextField cell = (TextField) node;
                    if (modelValue==0){
                        cell.setText("");
                        cell.setStyle("-fx-background-color: white;");
                    } else {
                        cell.setText(String.valueOf(modelValue));
                    }
                }
            }
        }
    }

    private User user;

    /**
     * Sets the user for the game session.
     * @param user
     */
    public void setUser(User user){this.user = user;}
}
