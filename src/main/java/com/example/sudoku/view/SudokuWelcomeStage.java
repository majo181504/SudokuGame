package com.example.sudoku.view;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuWelcomeStage extends Stage{
    private SudokuWelcomeStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/com/example/sudoku/sudoku-welcome-view.fxml")
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
        private static SudokuWelcomeStage INSTANCE = null;
    }
    public static SudokuWelcomeStage getInstance() throws IOException{
        Holder.INSTANCE = Holder.INSTANCE != null ?
                Holder.INSTANCE : new SudokuWelcomeStage();
        return Holder.INSTANCE;
    }

    public static void  deleteInstance(){
        Holder.INSTANCE.close();
        Holder.INSTANCE = null;
    }

}
