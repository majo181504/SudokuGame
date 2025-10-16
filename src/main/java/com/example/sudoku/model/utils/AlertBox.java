package com.example.sudoku.model.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Class to display alert boxes in the Sudoku game (There´s only one alert box to verify the user nickname)
 */
public class AlertBox implements IAlertBox{
    private Alert alert;

    /**
     * Displays an alert box with the specified header text, content text, and alert type.
     * @param headerText
     * @param contentText
     * @param alertType
     */
    @Override
    public void showAlert(String headerText, String contentText, AlertType alertType){
        alert = new Alert(alertType);
        alert.setTitle("Sudoku");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
