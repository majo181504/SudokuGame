package com.example.sudoku.model.game;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import com.example.sudoku.model.board.Board;

public class Game extends GameAbstract {
    public Game(GridPane boardGridpane){super(boardGridpane);}

    @Override
    public void startGame() {
        boardGridPane.getChildren().clear();
        for (int i = 0; i < board.getBoard().length; i++){
            for (int j = 0; j < board.getBoard()[i].length; j++){
                TextField cell = new TextField();
                cell.setAlignment(Pos.CENTER);
                cell.setPrefWidth(40);
                cell.setPrefHeight(40);

                int value = board.getBoard()[i][j];
                if(value !=0){
                    cell.setText(String.valueOf(value));
                    cell.setEditable(false);
                }

                int row = i;
                int col = j;

                cell.setOnKeyPressed(event -> {
                    String input = cell.getText().trim();
                    if (input.matches("[1-6]")){
                        int number = Integer.parseInt(input);
                        if (board.isValidMove(row, col, number)){
                            board.setValue(row, col, number);

                        }
                    }
                });

            }
        }
    }
}
