package com.example.sudoku.view;
import com.example.sudoku.controller.SudokuWelcomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The SudokuWelcomeStage class represents the welcome stage of the Sudoku application.
 * It is implemented as a singleton to ensure only one instance exists.
 * This class loads the welcome view from an FXML file and sets up the stage properties.
 */
public class SudokuWelcomeStage extends Stage{
    private SudokuWelcomeController controller;

    /**
     * Private constructor to initialize the SudokuWelcomeStage.
     * @throws IOException
     */
    private SudokuWelcomeStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/com/example/sudoku/sudoku-welcome-view.fxml")
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
     * Gets the controller associated with the SudokuWelcomeStage.
     * @return
     */
    public SudokuWelcomeController getController(){
        return controller;
    }

    /**
     * Holder class for implementing the singleton pattern.
     * Contains a static instance of SudokuWelcomeStage.
     */
    private static class Holder {
        private static SudokuWelcomeStage INSTANCE = null;
    }

    /**
     * Provides access to the singleton instance of SudokuWelcomeStage.
     * @return
     * @throws IOException
     */
    public static SudokuWelcomeStage getInstance() throws IOException{
        Holder.INSTANCE = Holder.INSTANCE != null ?
                Holder.INSTANCE : new SudokuWelcomeStage();
        return Holder.INSTANCE;
    }
    /**
     * Deletes the singleton instance of SudokuWelcomeStage.
     */
    public static void  deleteInstance(){
        Holder.INSTANCE.close();
        Holder.INSTANCE = null;
    }

}
