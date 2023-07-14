package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Integration.fxml"));
            Scene cena = new Scene(loader.load());
            primaryStage.setTitle("Projeto Integração");
            primaryStage.setScene(cena);
            primaryStage.show();
        }catch(IOException e){
            e.printStackTrace();
         }
    }
    public static void main(String[] args){

        launch(args);

    }
}