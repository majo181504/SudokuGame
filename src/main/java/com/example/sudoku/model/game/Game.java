package com.example.sudoku.model.game;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import com.example.sudoku.model.board.Board;

public class Game extends GameAbstract {
    public Game(GridPane boardGridpane) {  //constructor of the Game class that receives the GridPane from Board
        super(boardGridpane);
    }

    public void helpHints(){
        int[] hintData = board.getSomeHint();

        if (hintData != null) {
            int hintRow = hintData[0];
            int hintCol = hintData[1];
            int hintValue = hintData[2];

            // El tamaño del tablero es 6
            int SIZE = Board.SIZE;

            // 1. Calcular el índice en tu lista lineal 'cells'
            // cells es una lista que guarda todos los TextFields en orden lineal (fila a fila)
            int cellIndex = hintRow * SIZE + hintCol;

            // 2. Obtener el TextField y actualizarlo
            TextField targetCell = cells.get(cellIndex);

            // 3. Aplicar el movimiento internamente (usando tu método setCell)
            board.setCell(hintRow, hintCol, hintValue);

            // 4. Actualizar la interfaz gráfica
            targetCell.setText(String.valueOf(hintValue));

            // 5. Aplicar el estilo de ayuda (o el estilo de celda válida)
            targetCell.setStyle("-fx-background-color: #ffffa0;"); // Ejemplo: Amarillo claro para ayuda

        } else {
            // El tablero está lleno o no se encontró ninguna pista válida.
            // Aquí podrías mostrar un Alert de JavaFX al usuario.
            System.out.println("No se puede dar una pista aleatoria en este momento.");
        }
    }


    @Override
    public void startGame() {
        boardGridPane.getChildren().clear();//Clear the Gridpane to start the game.
        board.initializeBoard();
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                TextField cell = new TextField();
                cell.setText(board.getCell(i, j) == 0 ? "" : String.valueOf(board.getCell(i, j)));   //checks if the cells are empty, and if not,
                // displays the number in the cell

                if (board.getCell(i, j) != 0) {
                    cell.setText(String.valueOf(board.getCell(i, j)));
                    cell.setEditable(false);
                    cell.setStyle("-fx-background-color:  #f6bdd1;");

                } else {
                    cell.setText("");
                    cell.setStyle("-fx-background-color: white;");
                }


                int row = i;
                int col = j;

                cell.setOnKeyReleased(event -> {    //event with the keyboard
                    String text = cell.getText().trim();

                    if (text.isEmpty()) {
                        board.setCell(row, col, 0);
                        cell.setStyle("-fx-background-color: white;");
                        return;
                    }

                    if (event.getCode().isDigitKey()) {       //validates whether the text entered by the user is a numeric value
                        try {
                            int value = Integer.parseInt(text);
                            if (board.isValid(row, col, value)) {
                                board.setCell(row, col, value);
                                cell.setStyle("-fx-background-color: #c3f7b3;"); // Green in cell if valid
                            } else {
                                cell.setStyle("-fx-background-color: #ffb0b0;");// Red in cell if not valid
                                cell.setText("");
                            }
                        } catch (NumberFormatException e) {     //executes if the user did not enter a numeric value
                            cell.setText("");
                        }
                    } else if (event.getCode() == KeyCode.BACK_SPACE) {
                        board.setCell(row, col, 0);
                        cell.setStyle("-fx-background-color: white;");
                    }
                });

                boardGridPane.add(cell, j, i);   //boardGridPane.add (node, columnIndex, rowIndex)
                cells.add(cell);
            }
        }
    }
}