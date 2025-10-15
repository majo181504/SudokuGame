package com.example.sudoku.model.game;

import com.example.sudoku.view.FinalStage;
import com.example.sudoku.view.SudokuGameStage;
import com.example.sudoku.view.VictoryStage;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import com.example.sudoku.model.board.Board;
import javafx.scene.text.Text;

import java.io.IOException;


public class Game extends GameAbstract {
    public Game(GridPane boardGridpane) {  //constructor of the Game class that receives the GridPane from Boardkm
        super(boardGridpane);
    }

    public void helpHints(){
        int[] hintData = board.getSomeHint();

        if (hintData != null) {             //returns an array with the hint's value and position
            int hintRow = hintData[0];
            int hintCol = hintData[1];
            int hintValue = hintData[2];


            int SIZE = Board.SIZE;

            int cellIndex = hintRow * SIZE + hintCol;  //formula for having a single game board index

            // It gets the hint's index
            TextField targetCell = cells.get(cellIndex);

            board.setCell(hintRow, hintCol, hintValue);   //Shows the hint's position and value

            targetCell.setText(String.valueOf(hintValue));
            targetCell.setStyle("-fx-background-color: #ffffa0;");
            targetCell.setEditable(false);

            if(board.isSolved()){
                endGame();
            }

        } else {
            //Any hint is found
            System.out.println("No se puede dar una pista aleatoria en este momento.");
        }
    }

    private void endGame(){
        try{
        VictoryStage victory = VictoryStage.getInstance();
        victory.show();
        SudokuGameStage.deleteInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startGame() {
        boardGridPane.getChildren().clear();//Clear the Gridpane to start the game.
        board.initializeBoard();
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                TextField cell = new TextField();
                cell.setText(board.getCell(i, j) == 0 ? "" : String.valueOf(board.getCell(i, j)));   //checks if the cells are empty, and if not, displays the number in the cell

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
                                        cell.setStyle("-fx-background-color: #c3f7b3;");// Green in cell if valid
                                    } else {
                                        cell.setStyle("-fx-background-color: #ffb0b0;");// Red in cell if not valid
                                        cell.setText("");
                                    }
                                    if (board.isSolved()) {
                                        endGame();
                                    }
                                } catch (NumberFormatException e) {     //executes if the user did not enter a numeric value
                                    cell.setText("");
                                }

                            } else if (event.getCode() == KeyCode.BACK_SPACE) {
                                if (!cell.isEditable()){
                                    return;
                                }
                                cell.setText("");
                                board.setCell(row, col, 0);
                                cell.setStyle("-fx-background-color: white;");
                            }
                            if(!event.getCode().isDigitKey()){
                                cell.setText("");
                                cell.setStyle("-fx-background-color: white;");
                                board.setCell(row,col,0);
                            }

                        });;





                boardGridPane.add(cell, j, i);   //boardGridPane.add (node, columnIndex, rowIndex)
                cells.add(cell);
            }
        }
    }
}