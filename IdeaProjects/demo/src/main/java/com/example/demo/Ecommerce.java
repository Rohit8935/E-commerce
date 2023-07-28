package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Ecommerce extends Application {

    UserInterface userinterface=new UserInterface();
    public Ecommerce() throws FileNotFoundException {
    }
    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(userinterface.createContent());
        stage.setTitle("Shopify");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch(); //Main function from where the application starts
    }
}