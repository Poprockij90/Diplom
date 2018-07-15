package com.andryha.storage;

import com.andryha.storage.connections.MySqlConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/globalView.fxml"));
        primaryStage.setTitle("Склад товаров");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }
    public void Stop(){
        MySqlConnection.closeConnection();
    }


    public static void main(String[] args) {
        launch(args);
    }
}