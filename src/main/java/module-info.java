module com.example.sudoku {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;



    opens com.example.sudoku to javafx.fxml;
    opens com.example.sudoku.controller to javafx.fxml;

    exports com.example.sudoku;
}