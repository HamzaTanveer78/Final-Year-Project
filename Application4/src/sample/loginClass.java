package sample;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class loginClass implements Initializable {

    @FXML
    private JFXButton getstart;
    @FXML
    private Pane pane1;

    @FXML
    private Pane pane2;

    @FXML
    private Pane pane3;

    @FXML
    private Pane pane4;

    @FXML
    private Pane pane5;

    @FXML
    private Pane pane6;

    @FXML
    private Pane pane7;

    @FXML
    private Pane pane8;

    @FXML
    private Pane pane9;

    @FXML
    private Pane sampane;

    @FXML
    void getstart(ActionEvent event) {

//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setContentText("Do You Really want to login?");
//        Optional<ButtonType> answer = alert.showAndWait();
//
//        if (answer.equals(ButtonType.OK)) {
//
//        } else {
//            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
//            alert.setContentText("You selected NO in confirmation. Sorry");
//            alert.showAndWait();
//        }
        getstart.getScene().getWindow().hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.print(ex.getLocalizedMessage());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        pane9.setStyle("-fx-background-image: url(\"/sample/8.jpg\")" + ";-fx-background-size: cover");
//        //pane9.setStyle("-fx-background-size: cover");
//        pane8.setStyle("-fx-background-image: url(\"/sample/8.jpg\")");
//        pane8.setStyle("-fx-background-size: cover");
//        pane7.setStyle("-fx-background-image: url(\"/sample/7.jpg\")");
//        pane6.setStyle("-fx-background-image: url(\"/sample/6.jpg\")");
//        pane5.setStyle("-fx-background-image: url(\"/sample/5.jpg\")");
//        pane4.setStyle("-fx-background-image: url(\"/sample/4.jpg\")");
//        pane3.setStyle("-fx-background-image: url(\"/sample/3.jpg\")");
//        pane2.setStyle("-fx-background-image: url(\"/sample/2.jpg\")");
//        pane1.setStyle("-fx-background-image: url(\"/sample/1.jpg\")");
//        pane1.setStyle("-fx-background-size: cover");


        backgroungAnnimation();
    }

    private void backgroungAnnimation() {

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2),pane9);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        fadeTransition.setOnFinished(event -> {
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(2),pane8);
            fadeTransition1.setFromValue(1);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

         fadeTransition1.setOnFinished(event1 -> {
             FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(2),pane7);
             fadeTransition2.setFromValue(1);
             fadeTransition2.setToValue(0);
             fadeTransition2.play();

          fadeTransition2.setOnFinished(event2 -> {

              FadeTransition fadeTransition3  = new FadeTransition(Duration.seconds(2),pane6);
              fadeTransition3.setFromValue(1);
              fadeTransition3.setToValue(0);
              fadeTransition3.play();

           fadeTransition3.setOnFinished(event3 -> {
               FadeTransition fadeTransition4 = new FadeTransition(Duration.seconds(2),pane5);
               fadeTransition4.setFromValue(1);
               fadeTransition4.setToValue(0);
               fadeTransition4.play();

            fadeTransition4.setOnFinished(event4 -> {
                FadeTransition fadeTransition5 = new FadeTransition(Duration.seconds(2),pane4);
                fadeTransition5.setFromValue(1);
                fadeTransition5.setToValue(0);
                fadeTransition5.play();

             fadeTransition5.setOnFinished(event5 -> {
                 FadeTransition fadeTransition6 = new FadeTransition(Duration.seconds(2),pane3);
                 fadeTransition6.setFromValue(1);
                 fadeTransition6.setToValue(0);
                 fadeTransition6.play();

              fadeTransition6.setOnFinished(event6 -> {
                  FadeTransition fadeTransition7 = new FadeTransition(Duration.seconds(2),pane2);
                  fadeTransition7.setFromValue(1);
                  fadeTransition7.setToValue(0);
                  fadeTransition7.play();
               fadeTransition7.setOnFinished(event7 -> {
                   backgroungAnnimation();

               });
              });
             });
            });
           });
          });
         });
        });
   }
}
