package com.example.sudoku.model.board;

import java.util.*;

public class Board implements IBoard{

    public static final int SIZE= 6;
    private int [][] board;
    private Deque<Move> moveHistory; // cola doble para registrar moves


    public void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = 0;
            }
        }

        int[][] blockStarts = {         //Start position of 2x3 grids
                {0, 0}, {0, 3}, {2, 0}, {2, 3}, {4, 0}, {4, 3}
        };
        Random random = new Random();

        for (int[] start : blockStarts) {
            int startRow = start[0];
            int startCol = start[1];

            List<Integer> startNumbers = new ArrayList<>();
            for (int n = 1; n <= SIZE; n++) {
                startNumbers.add(n);
            }
            Collections.shuffle(startNumbers);

            List<Integer> positions = new ArrayList<>();
            for (int p = 0; p < 6; p++) {
                positions.add(p);
            }
            Collections.shuffle(positions);

            int placedCounts = 0;
            int maxHints = 2;

            for (int k = 0; k < 2; k++) {
                if (placedCounts >= maxHints) break;

                int p = positions.get(k);
                int deltaRow = p / 3;
                int deltaCol = p % 3;

                int currentRow = startRow + deltaRow;
                int currentCol = startCol + deltaCol;
                Collections.shuffle(startNumbers);

                for (int value : startNumbers) {
                    if (isValid(currentRow, currentCol, value)) {
                        this.board[currentRow][currentCol] = value;
                        placedCounts++;
                        break;
                    }
                }

            }
        }
    }



    private static class Move {
        int row, col, value;

        Move(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    public int getHint(int row, int col) {
        if (board[row][col] != 0) {
            return 0; // Si ya tiene un número, no hay pista.
        }

        // Iterar sobre todos los posibles valores de 1 a SIZE (que es 6)
        for (int value = 1; value <= SIZE; value++) {

            // Usamos el método isValid existente para verificar
            if (isValid(row, col, value)) {
                return value; // Devuelve el primer valor válido que encuentra
            }
        }

        return 0; // Si el bucle termina sin encontrar un valor válido
    }

    public int[] getSomeHint(){
        List<int[]> emptyCells = new ArrayList<>();

        for (int i=0; i<SIZE; i++){
            for (int j=0; j<SIZE; j++){
                if (this.board[i][j]==0){
                    emptyCells.add(new int[]{i, j});
                }
            }
        }

        if (emptyCells.isEmpty()){ return null;}

        Collections.shuffle(emptyCells);

        for (int [] cell: emptyCells){
            int row = cell[0];
            int col = cell[1];

            // Usamos tu método getHint(row, col) para ver si tiene una solución posible
            int hintValue = getHint(row, col);

            if (hintValue != 0) {
                return new int[]{row, col, hintValue};
            }
        }

        return null;
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
    public boolean isValid(int row, int col, int value) {      //validates the number entered by the user in each cell
        if(value<1 || value>6) return false;

        for(int j=0; j< SIZE; j++){
            if (j == col) continue;
            if(board[row][j]==value){return false;}    //returns false if a number is repeated in the same column
        }

        for(int i=0; i< SIZE; i++){
            if (i == row) continue;
            if(board[i][col]==value){return false;}     //returns false if a number is repeated in the same row
        }

        int startRow = (row / 2) * 2;        //starts the 2x3 grids
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 2; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (i == row && j == col) { continue;}
                if (board[i][j] == value) return false;     //returns false if a number is repeated in the 2x3 grid
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
