package com.example.sudoku.model.utils;

import javafx.scene.control.Alert;

public interface IAlertBox {
    void showAlert(String headerText, String contentText,Alert.AlertType alertType);
}
