package com.example.sudoku.view;

import com.example.sudoku.controller.FinalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;

public class FinalStage extends Stage{
    private FinalController controller;
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

    public FinalController getController(){
        return controller;
    }


    private static class Holder {
        private static FinalStage INSTANCE = null;
    }
    public static FinalStage getInstance() throws IOException{
        FinalStage.Holder.INSTANCE = FinalStage.Holder.INSTANCE != null ?
                FinalStage.Holder.INSTANCE : new FinalStage();
        return FinalStage.Holder.INSTANCE;
    }

    public static void  deleteInstance(){
        FinalStage.Holder.INSTANCE.close();
        FinalStage.Holder.INSTANCE = null;
    }

}


