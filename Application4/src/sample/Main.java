package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("ParOwner.fxml"));
        primaryStage.setTitle("Welcome to Pakistan's Land Record System");
        window = primaryStage;
        primaryStage.setOnCloseRequest(event -> closeprogram());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
    public void closeprogram(){
       System.out.println("File is close");
        window.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
