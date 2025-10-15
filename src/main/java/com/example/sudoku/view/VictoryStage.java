package com.example.sudoku.view;

import com.example.sudoku.controller.VictoryController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class VictoryStage extends Stage {
    private VictoryController controller;
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

    }

    public VictoryController getController(){
        return controller;
    }

    private static class Holder {
        private static VictoryStage INSTANCE = null;
    }
    public static VictoryStage getInstance() throws IOException{
        VictoryStage.Holder.INSTANCE = VictoryStage.Holder.INSTANCE != null ?
                VictoryStage.Holder.INSTANCE : new VictoryStage();
        return VictoryStage.Holder.INSTANCE;
    }

    public static void  deleteInstance(){
        VictoryStage.Holder.INSTANCE.close();
        VictoryStage.Holder.INSTANCE = null;
    }

}

