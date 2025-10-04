package com.example.sudoku.model.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertBox implements IAlertBox{
    private Alert alert;

    @Override
    public void showAlert(String headerText, String contentText, AlertType alertType){
        alert = new Alert(alertType);
        alert.setTitle("Sudoku");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
