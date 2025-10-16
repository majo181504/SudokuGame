package com.example.sudoku.model.board;

import java.util.*;


/**
 * This class represents a 6x6 Sudoku board and provides methods to initialize, validate, and manipulate the board.
 * It includes functionality to generate a valid Sudoku puzzle, check for valid moves, provide hints.
 */
public class Board implements IBoard{

    public static final int SIZE= 6;
    private int [][] board;
    private Deque<Move> moveHistory; // Deque to register moves

    /**
     * Initializes the Sudoku board by generating a valid puzzle with a reasonable difficulty level.
     * The method first fills the board with zeros, then creates a complete valid Sudoku solution.
     * It then removes numbers from the board while ensuring that each 2x3 grid has at least two visible cells,
     * and adds some extra visible cells to achieve a reasonable difficulty level
     */
    public void initializeBoard() {
        fillBoard();

        // Creates a complete valid board
        solveSudoku(board);

        // Create a list of all cell positions
        List<int[]> allCells = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                allCells.add(new int[]{i, j});
            }
        }
        Collections.shuffle(allCells);

        //It makes sure that every 2x3 gris has, at least, 2 visible cells
        boolean[][] keepCell = new boolean[SIZE][SIZE];
        Random random = new Random();

        // Start coordinates of each 2x3 block
        int[][] blockStarts = {
                {0, 0}, {0, 3},
                {2, 0}, {2, 3},
                {4, 0}, {4, 3}
        };

        // C
        for (int[] start : blockStarts) {
            List<int[]> blockCells = new ArrayList<>();
            for (int i = start[0]; i < start[0] + 2; i++) {
                for (int j = start[1]; j < start[1] + 3; j++) {
                    blockCells.add(new int[]{i, j});
                }
            }
            Collections.shuffle(blockCells);
            for (int k = 0; k < 2; k++) { // 2 cells per block
                int[] c = blockCells.get(k);
                keepCell[c[0]][c[1]] = true;
            }
        }

        // keeps some extra cells for reasonable difficulty (~12 visibles)
        int cellsToShow = 12;
        int alreadyKept = 0;
        for (boolean[] row : keepCell)
            for (boolean b : row)
                if (b) alreadyKept++;

        int extraToKeep = Math.max(0, cellsToShow - alreadyKept);

        //adds more extra cells at random
        Collections.shuffle(allCells);
        for (int[] pos : allCells) {
            if (extraToKeep == 0) break;
            if (!keepCell[pos[0]][pos[1]]) {
                keepCell[pos[0]][pos[1]] = true;
                extraToKeep--;
            }
        }

        // This empties all the ones that should not be kept
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!keepCell[i][j]) {
                    board[i][j] = 0;
                }
            }
        }
    }

    /**
     * Inner class to represent a move made on the board, storing the row, column, and previous value of the cell.
     */
    private static class Move {
        int row, col, value;

        Move(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    /**
     * Provides a hint for a specific cell by returning the correct value that should be placed in that cell.
     * If the cell is already filled, it returns 0. It uses the Sudoku solver to find a completed solution and retrieves the value for the specified cell.
     * @param row
     * @param col
     * @return
     */
    public int getHint(int row, int col) {
        if (board[row][col] != 0) {
            return 0; //If there is a number, there is no a hint
        }

        int[][] temp = copyBoard(board);
        if (solveSudoku(temp)) { // Uses the solver to find a completed solution
            return temp[row][col]; // Returns the correct value according to the solved board
        }
        return 0;
    }

    /**
        * Creates a deep copy of the current board state to avoid modifying the original board during solving.
     * @param source
     * @return
     */
    private int[][] copyBoard(int[][] source) {    //Current board copy
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            System.arraycopy(source[i], 0, copy[i], 0, SIZE);
        return copy;
    }

    /**
     * Checks if placing a specific value in a given row and column is valid according to Sudoku rules.
     * @param grid
     * @param row
     * @param col
     * @param value
     * @return
     */
    private boolean isValidPosition(int[][] grid, int row, int col, int value) {     //Validates a position inside the solver
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

    /**
     * Solves the Sudoku puzzle using a backtracking algorithm. It fills the grid with valid numbers according to Sudoku rules.
     * @param grid
     * @return
     */
    private boolean solveSudoku(int[][] grid) {   //Backtracking algorithm to solve the Sudoku
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    List<Integer> nums = new ArrayList<>();
                    for (int n = 1; n<= SIZE; n++) nums.add(n);
                    Collections.shuffle(nums); // <-- randomize the test order

                    for(int num : nums){
                        if (isValidPosition(grid, i, j, num)) {
                            grid[i][j] = num;
                            if (solveSudoku(grid)) return true;
                            grid[i][j] = 0;
                        }
                    }
                    return false; // there is no valid number for this cell
                }
            }
        }
        return true; // Completed Sudoku
    }

    /**
     * Provides a hint for one of the empty cells on the board by randomly selecting an empty cell and returning its row, column, and the correct value to be placed there.
     * If no empty cells are available or no valid hint can be found, it returns null
     * @return
     */
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

            // It uses the getHint method to see is there is a possible solution
            int hintValue = getHint(row, col);

            if (hintValue != 0) {
                return new int[]{row, col, hintValue};
            }
        }

        return null;
    }

    /**
     * Constructor to initialize the board and move history.
     */
    public Board(){
        board = new int[SIZE][SIZE];
        moveHistory = new ArrayDeque<>();
        fillBoard();
    }

    /**
     * Fills the board with zeros to represent an empty state.
     */
    private void fillBoard(){
        for (int i=0; i< SIZE; i++){
            for (int j=0; j< SIZE; j++){
                board[i][j] = 0;
            }
        }
    }

    /**
     * Checks if placing a specific value in a given row and column is valid according to Sudoku rules.
     * @param row
     * @param col
     * @param value
     * @return
     */
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

    /**
     * Sets the value of a specific cell on the board and records the move in the move history.
     * @param row
     * @param col
     * @param value
     */
    @Override
    public void setCell(int row, int col, int value) {
        moveHistory.addLast(new Move(row, col, board[row][col]));
        board[row][col] = value;
    }

    /**
     * Retrieves the value of a specific cell on the board.
     * @param row
     * @param col
     * @return
     */
    @Override
    public int getCell(int row, int col) {
        return board[row][col];
    }

    /**
     * Checks if the Sudoku puzzle is completely and correctly solved.
     * @return
     */
    public boolean isSolved(){
        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++){
                int value = board[i][j];
                if (value==0) return false;
                if(!isValid(i,j,value))return false;
            }

        }
        return true;
    }

    /**
     * Gets the size of the board.
     * @return
     */
    public int getSize() {
        return SIZE;
    }

    /**
     * Gets the value at a specific row and column, throwing an exception if the coordinates are out of bounds.
     * @param row
     * @param col
     * @return
     */
    public  int getValue(int row, int col){
        if (row<0 || row>= getSize() || col<0 || col>= getSize()){
            throw new IndexOutOfBoundsException("Coordenadas fuera de rango");
        }
        return board[row][col];
    }

    public int [][] getBoard(){
        return board;
    }
}
