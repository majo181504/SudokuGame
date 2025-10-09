package com.example.sudoku.controller;

import com.example.sudoku.model.user.User;


import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class SudokuGameController {
    @FXML
    private GridPane boardGridPane;

    public void fillBoard(){
        System.out.println("Creando Tablero...");
    }



    private User user;
    public void setUser(User user){this.user = user;}
}
