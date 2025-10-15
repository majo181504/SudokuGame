package com.example.sudoku.controller;

import com.example.sudoku.model.user.User;
import com.example.sudoku.view.FinalStage;
import com.example.sudoku.view.WelcomeStage;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

import java.io.IOException;

public class FinalController {
    @FXML
    private Text changeText;

    @FXML
    private Button backButton;

    private void setName() {
        User userNickname = User.getInstance();
        String nameUser = userNickname.getNickname();

        changeText.setText("Has salido del juego "+nameUser);
    }

    @FXML
    private void handleBack() throws IOException {
        FinalStage.deleteInstance();
        WelcomeStage.getInstance();
    }
    @FXML
    public void initialize(){
        setName();
    }
}
