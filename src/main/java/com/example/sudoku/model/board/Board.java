package com.example.sudoku.model.board;

public class Board {
    private int [][] board;

    public Board(){
        board = new int[6][6];
        fillBoard();
    }

    private void fillBoard(){
        for (int i=0; 1<board.length; i++){
            for (int j=0; j<board.length; j++){
                board[i][j] = 0;
            }
        }
    }

    public int [][] getBoard(){
        return board;
    }

    public void setBoard(int [][] board){
        this.board = board;
    }
}
