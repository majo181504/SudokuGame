package com.example.sudoku.view;

import com.example.sudoku.controller.SudokuGameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The SudokuGameStage class represents the main game stage of the Sudoku application.
 * It is implemented as a singleton to ensure only one instance exists.
 * This class loads the game view from an FXML file and sets up the stage properties.
 */
public class SudokuGameStage extends Stage {
    private SudokuGameController controller;

    /**
     * Private constructor to initialize the SudokuGameStage.
     * @throws IOException
     */
    private SudokuGameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/sudoku/sudoku-game-view.fxml")
        );
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);
        setScene(scene);

        setTitle("Sudoku");
        setResizable(false);
        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource("/com/example/sudoku/sudokuicon.png")))
        );
        show();
    }

    /**
     * Gets the controller associated with the SudokuGameStage.
     * @return
     */
    public SudokuGameController getController(){
        return controller;
    }

    /**
     * Holder class for implementing the singleton pattern.
     * Contains a static instance of SudokuGameStage.
     */
    private static class Holder {
        private static SudokuGameStage INSTANCE = null;
    }
    /**
     * Provides access to the singleton instance of SudokuGameStage.
     * @return
     * @throws IOException
     */
    public static SudokuGameStage getInstance() throws IOException {
        SudokuGameStage.Holder.INSTANCE = SudokuGameStage.Holder.INSTANCE != null ?
                SudokuGameStage.Holder.INSTANCE : new SudokuGameStage();
        return SudokuGameStage.Holder.INSTANCE;
    }

        /**
         * Deletes the current instance of SudokuGameStage, closing the stage and setting the instance to null.
         */
        public static void deleteInstance() {
            SudokuGameStage.Holder.INSTANCE.close();
            Holder.INSTANCE = null;
        }

    }


