package com.example.sudoku.model.board;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Board {
    private final int SIZE= 6;
    private int [][] board;
    private final Deque<Move> movesHistory; // cola doble para registrar moves

    public Board(){
        board = new int[SIZE][SIZE];
        movesHistory = new ArrayDeque<>();
        fillBoard();
    }

    private void fillBoard(){
        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[i].length; j++){
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


    //Asigna un número a una celda y guarda el movimiento en la cola doble
    public void setValue(int row, int col, int value){
        int oldValue = board[row][col];
        board[row][col] = value;
        movesHistory.push(new Move(row, col, oldValue, value));
    }


    // Revierte el último movimiento realizado (Deshacer)
    public void undoLastMove(){
        if (!movesHistory.isEmpty()){
            Move lastMove = movesHistory.pop();
            board[lastMove.getRow()][lastMove.getCol()]= lastMove.getOldValue();
        }
    }

    // Verifica si un número puede colocarse en una celd
    public boolean isValidMove(int row, int col, int num){
        return !isInRow(row, num) && !isInColumn(col,num) && !isInBox(row, col, num);
    }

    private boolean isInRow(int row, int num){
        for (int c=0; c<SIZE; c++){
            if (board[row][c] == num) return true;
        }
        return false;
    }

    private boolean isInColumn(int col, int num){
        for (int r=0; r<SIZE; r++){
            if (board[r][col] == num) return true;
        }
        return false;
    }

    private boolean isInBox(int row, int col, int num){
        int startRow = (row/2)*2;
        int startCol = (col/3)*3;
        for (int r=0; r<2; r++){
            for (int c=0; c<3; c++){
                if (board[startRow+r][startCol+c] == num) return true;
            }
        }
        return false;
    }

    // Para la opción de ayuda
    public Set<Integer> getPossibleNumbers(int row, int col){
        Set<Integer> possibilities = new HashSet<>();
        if (board[row][col] != 0) return possibilities;
        for (int num=1; num<=SIZE; num++){
            if (isValidMove(row, col, num)) possibilities.add(num);
        }
        return possibilities;
    }

    // Clase interna para registrar cada movimiento
    private static class Move {
        private final int row;
        private final int col;
        private final int oldValue;
        private final int newValue;

        public Move(int row, int col, int oldValue, int newValue) {
            this.row = row;
            this.col = col;
            this.oldValue = oldValue;
            this.newValue = newValue;
        }
        public int getRow() { return row;}
        public int getCol() { return col;}
        public int getOldValue() { return oldValue;}
        public int getNewValue() { return newValue;}
    }
}
