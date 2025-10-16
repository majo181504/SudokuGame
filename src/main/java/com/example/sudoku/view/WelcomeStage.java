package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The WelcomeStage class represents the welcome stage of the Sudoku application.
 * It is implemented as a singleton to ensure only one instance exists.
 * This class loads the welcome view from an FXML file and sets up the stage properties.
 */
public class WelcomeStage extends Stage {
    /**
     * Private constructor to initialize the WelcomeStage.
     * @throws IOException
     */
    private WelcomeStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/sudoku/sudoku-welcomeplay-view.fxml")
        );
        Parent root = loader.load();
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
     * Holder class for implementing the singleton pattern.
     * Contains a static instance of WelcomeStage.
     */
    private static class Holder {
        private static WelcomeStage INSTANCE = null;
    }

    /**
     * Provides access to the singleton instance of WelcomeStage.
     * @return
     * @throws IOException
     */
    public static WelcomeStage getInstance() throws IOException{
        WelcomeStage.Holder.INSTANCE = WelcomeStage.Holder.INSTANCE != null ?
                WelcomeStage.Holder.INSTANCE : new WelcomeStage();
        return WelcomeStage.Holder.INSTANCE;
    }

    /**
     * Deletes the singleton instance of WelcomeStage.
     */
    public static void  deleteInstance(){
        WelcomeStage.Holder.INSTANCE.close();
        WelcomeStage.Holder.INSTANCE = null;
    }

}

