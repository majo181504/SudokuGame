package com.example.sudoku.view;

import com.example.sudoku.controller.FinalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * The FinalStage class represents the final stage of the Sudoku application.
 * It is implemented as a singleton to ensure only one instance exists.
 * This class loads the final view from an FXML file and sets up the stage properties.
 */
public class FinalStage extends Stage{
    private FinalController controller;

    /**
     * Private constructor to initialize the FinalStage.
     * @throws IOException
     */
    private FinalStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/sudoku/sudoku-final-view.fxml")
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
     * Gets the controller associated with the FinalStage.
     * @return
     */
    public FinalController getController(){
        return controller;
    }

    /**
     * Holder class for implementing the singleton pattern.
     * Contains a static instance of FinalStage.
     */
    private static class Holder {
        private static FinalStage INSTANCE = null;
    }

    /**
     * Provides access to the singleton instance of FinalStage.
     * @return
     * @throws IOException
     */
    public static FinalStage getInstance() throws IOException{
        FinalStage.Holder.INSTANCE = FinalStage.Holder.INSTANCE != null ?
                FinalStage.Holder.INSTANCE : new FinalStage();
        return FinalStage.Holder.INSTANCE;
    }

    /**
     * Deletes the current instance of FinalStage, closing the stage and setting the instance to null.
     */
    public static void  deleteInstance(){
        FinalStage.Holder.INSTANCE.close();
        FinalStage.Holder.INSTANCE = null;
    }

}


