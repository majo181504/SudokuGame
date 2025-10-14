package com.example.sudoku.controller;

import com.example.sudoku.model.board.Board;
import com.example.sudoku.model.user.User;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class FinalController {
    @FXML
    private Text changeText;

    private void setName() {
        User userNickname = User.getInstance();
        String nameUser = userNickname.getNickname();

        changeText.setText("Has salido del juego "+nameUser);
    }
   @FXML
    public void initialize(){
        setName();
    }
}
