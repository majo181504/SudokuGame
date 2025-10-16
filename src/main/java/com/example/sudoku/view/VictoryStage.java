package com.example.sudoku.view;

import com.example.sudoku.controller.VictoryController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The VictoryStage class represents the stage displayed when the user wins the Sudoku game.
 * It follows the singleton design pattern to ensure only one instance of the stage exists.
 * The stage is initialized with a specific FXML layout and associated controller.
 */
public class VictoryStage extends Stage {
    private VictoryController controller;

    /**
     * Private constructor to initialize the VictoryStage.
     * Loads the FXML layout, sets up the scene, and configures stage properties.
     * @throws IOException
     */
    private VictoryStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/sudoku/sudoku-victory-view.fxml")
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
     * Gets the controller associated with the VictoryStage.
     * @return
     */
    public VictoryController getController(){
        return controller;
    }

    /**
     * Holder class for implementing the singleton pattern.
     * Contains a static instance of VictoryStage.
     */
    private static class Holder {
        private static VictoryStage INSTANCE = null;
    }
    /**
     * Provides access to the singleton instance of VictoryStage.
     * If the instance does not exist, it creates a new one.
     * @return
     * @throws IOException
     */
    public static VictoryStage getInstance() throws IOException{
        VictoryStage.Holder.INSTANCE = VictoryStage.Holder.INSTANCE != null ?
                VictoryStage.Holder.INSTANCE : new VictoryStage();
        return VictoryStage.Holder.INSTANCE;
    }

    /**
     * Deletes the current instance of VictoryStage, closing the stage and setting the instance to null.
     */
    public static void  deleteInstance(){
        VictoryStage.Holder.INSTANCE.close();
        VictoryStage.Holder.INSTANCE = null;
    }

}

