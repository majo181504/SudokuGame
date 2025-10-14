package com.example.sudoku.model.board;

import com.example.sudoku.model.user.User;

import java.util.*;

public class Board implements IBoard{

    public static final int SIZE= 6;
    private int [][] board;
    private Deque<Move> moveHistory; // cola doble para registrar moves


    public void initializeBoard() {
        fillBoard();

        // Paso 1: Genera una solución completa válida
        solveSudoku(board);

        // Paso 2: Crea una lista de todas las celdas
        List<int[]> allCells = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                allCells.add(new int[]{i, j});
            }
        }
        Collections.shuffle(allCells);

        // Paso 3: Asegura que cada bloque 2x3 tenga al menos 2 celdas visibles
        boolean[][] keepCell = new boolean[SIZE][SIZE];
        Random random = new Random();

        // Coordenadas de inicio de cada bloque
        int[][] blockStarts = {
                {0, 0}, {0, 3},
                {2, 0}, {2, 3},
                {4, 0}, {4, 3}
        };

        // Garantiza al menos 2 celdas visibles por bloque
        for (int[] start : blockStarts) {
            List<int[]> blockCells = new ArrayList<>();
            for (int i = start[0]; i < start[0] + 2; i++) {
                for (int j = start[1]; j < start[1] + 3; j++) {
                    blockCells.add(new int[]{i, j});
                }
            }
            Collections.shuffle(blockCells);
            for (int k = 0; k < 2; k++) { // 2 celdas por bloque
                int[] c = blockCells.get(k);
                keepCell[c[0]][c[1]] = true;
            }
        }

        // Paso 4: Mantén algunas celdas extra para dificultad razonable (~12 visibles)
        int cellsToShow = 12;
        int alreadyKept = 0;
        for (boolean[] row : keepCell)
            for (boolean b : row)
                if (b) alreadyKept++;

        int extraToKeep = Math.max(0, cellsToShow - alreadyKept);

        // Añade algunas más al azar
        Collections.shuffle(allCells);
        for (int[] pos : allCells) {
            if (extraToKeep == 0) break;
            if (!keepCell[pos[0]][pos[1]]) {
                keepCell[pos[0]][pos[1]] = true;
                extraToKeep--;
            }
        }

        // Paso 5: Vacía todas las que no se deben mantener
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!keepCell[i][j]) {
                    board[i][j] = 0;
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

        int[][] temp = copyBoard(board);
        if (solveSudoku(temp)) { // Usa el solver para encontrar una solución completa
            return temp[row][col]; // Devuelve el valor correcto según la solución real
        }
        return 0;
    }

    // Copia del tablero actual
    private int[][] copyBoard(int[][] source) {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            System.arraycopy(source[i], 0, copy[i], 0, SIZE);
        return copy;
    }

    // Valida una posición dentro del solver
    private boolean isValidPosition(int[][] grid, int row, int col, int value) {
        for (int j = 0; j < SIZE; j++)
            if (grid[row][j] == value && j != col) return false;

        for (int i = 0; i < SIZE; i++)
            if (grid[i][col] == value && i != row) return false;

        int startRow = (row / 2) * 2;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 2; i++)
            for (int j = startCol; j < startCol + 3; j++)
                if (grid[i][j] == value && (i != row || j != col)) return false;

        return true;
    }

    // Solver recursivo (backtracking)
    private boolean solveSudoku(int[][] grid) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    List<Integer> nums = new ArrayList<>();
                    for (int n = 1; n<= SIZE; n++) nums.add(n);
                    Collections.shuffle(nums); // <-- aleatoriza el orden de prueba

                    for(int num : nums){
                        if (isValidPosition(grid, i, j, num)) {
                            grid[i][j] = num;
                            if (solveSudoku(grid)) return true;
                            grid[i][j] = 0;
                        }
                    }
                    return false; // No hay número válido para esta celda
                }
            }
        }
        return true; // Sudoku completado
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

    public boolean isSolved(){
        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++){
                if (board[i][j]==0) return false;
            }

        }
        return true;
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
