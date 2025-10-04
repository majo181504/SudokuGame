package com.example.sudoku.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class SudokuGameController {
    @FXML
    private GridPane boardGridPane;

    public void fillBoard(){
        System.out.println("Creando Tablero...");
    }
}
