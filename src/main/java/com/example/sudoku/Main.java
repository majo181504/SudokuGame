package com.example.sudoku;

import com.example.sudoku.view.WelcomeStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX Application class to launch the Sudoku game.
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the JavaFX application by displaying the welcome stage.
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        WelcomeStage.getInstance();

    }
}
