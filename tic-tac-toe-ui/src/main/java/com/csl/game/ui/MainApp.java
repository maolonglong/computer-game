package com.csl.game.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author MaoLongLong
 * @date 2021-02-18 20:20
 */
public class MainApp extends Application {

    @Override
    @SuppressWarnings("ConstantConditions")
    public void start(Stage primaryStage) throws Exception {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Parent root = FXMLLoader.load(cl.getResource("views/index.fxml"));
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
