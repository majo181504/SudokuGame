package com.example.sudoku.controller;

import com.example.sudoku.model.user.User;
import com.example.sudoku.model.utils.AlertBox;
import com.example.sudoku.view.SudokuGameStage;
import com.example.sudoku.view.SudokuWelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SudokuWelcomeController {
    @FXML
    private TextField nicknameTxt;

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
