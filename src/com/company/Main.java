package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application{

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("datos.fxml"));
        primaryStage.setTitle("MundarainJ - Simulación Comedor - 17 jun 2018");
        primaryStage.setScene(new Scene(root, 1272, 800));
        primaryStage.show();
    }

    public static void main(String[] args)  {
        launch(args);
    }


}
