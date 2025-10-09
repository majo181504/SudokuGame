package com.example.sudoku.model.game;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import com.example.sudoku.model.board.Board;

public class Game extends GameAbstract {
    public Game(GridPane boardGridpane) {
        super(boardGridpane);
    }

    @Override
    public void startGame() {
        boardGridPane.getChildren().clear();
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                TextField cell = new TextField();
                cell.setText(board.getCell(i, j) == 0 ? "" : String.valueOf(board.getCell(i, j)));


                int row = i;
                int col = j;

                cell.setOnKeyReleased(event -> {
                    String text = cell.getText().trim();

                    if (text.isEmpty()) {
                        board.setCell(row, col, 0);
                        cell.setStyle("-fx-background-color: white;");
                        return;
                    }

                    if (event.getCode().isDigitKey()) {
                        try {
                            int value = Integer.parseInt(text);
                            if (board.isValid(row, col, value)) {
                                board.setCell(row, col, value);
                                cell.setStyle("-fx-background-color: #c3f7b3;"); // Verde si es válido
                            } else {
                                cell.setStyle("-fx-background-color: #ffb0b0;"); // Rojo si no
                            }
                        } catch (NumberFormatException e) {
                            cell.setText("");
                        }
                    } else if (event.getCode() == KeyCode.BACK_SPACE) {
                        board.setCell(row, col, 0);
                        cell.setStyle("-fx-background-color: white;");
                    }
                });

                boardGridPane.add(cell, i, j);
                cells.add(cell);
            }
        }
    }
}