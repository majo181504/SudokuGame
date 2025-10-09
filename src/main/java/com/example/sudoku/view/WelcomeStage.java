package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeStage extends Stage {
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

    private static class Holder {
        private static WelcomeStage INSTANCE = null;
    }
    public static WelcomeStage getInstance() throws IOException{
        WelcomeStage.Holder.INSTANCE = WelcomeStage.Holder.INSTANCE != null ?
                WelcomeStage.Holder.INSTANCE : new WelcomeStage();
        return WelcomeStage.Holder.INSTANCE;
    }

    public static void  deleteInstance(){
        WelcomeStage.Holder.INSTANCE.close();
        WelcomeStage.Holder.INSTANCE = null;
    }

}

