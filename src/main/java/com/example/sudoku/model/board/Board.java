package com.example.sudoku.model.board;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Board implements IBoard{

    public static final int SIZE= 6;
    private int [][] board;
    private Deque<Move> moveHistory; // cola doble para registrar moves


    private static class Move {
        int row, col, value;

        Move(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

        public Board(){
        board = new int[SIZE][SIZE];
        moveHistory = new ArrayDeque<>();
        fillBoard();
    }

    private void fillBoard(){
        for (int i=0; i< SIZE; i++){
            for (int j=0; j< SIZE; j++){
                board[i][j] = 0;
            }
        }
    }

    @Override
    public boolean isValid(int row, int col, int value) {
        if(value<1 || value>6) return false;

        for(int j=0; j< SIZE; j++){
            if(board[row][j]==value){return false;}
        }

        for(int i=0; i< SIZE; i++){
            if(board[i][col]==value){return false;}
        }

        int startRow = (row / 2) * 2;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 2; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == value) return false;
            }
        }
        return true;
    }

    @Override
    public void setCell(int row, int col, int value) {
        moveHistory.addLast(new Move(row, col, board[row][col]));
        board[row][col] = value;
    }

    @Override
    public int getCell(int row, int col) {
        return board[row][col];
    }

    public void undoLastMove(){
        if(!moveHistory.isEmpty()){
            Move LastMove = moveHistory.removeLast();
            board[LastMove.row][LastMove.col] = LastMove.value;
        }
    }

    public void clearHistory(){
        moveHistory.clear();
    }

    public int [][] getBoard(){
        return board;
    }

    public Deque<Move> getMoveHistory(){
        return moveHistory;
    }
}
