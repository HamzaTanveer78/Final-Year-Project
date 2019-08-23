package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageClass {

    @FXML
    private Pane mainpane;

    @FXML
    private JFXHamburger hum1;

    @FXML
    private JFXButton searchbtn;

    @FXML
    private JFXButton recordbtn;
    @FXML
    private Pane mainPane1;

    @FXML
    void addrecord(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("ParOwner.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.print(e);
        }
    }

    public void setmode(Node node){
        mainPane1.getChildren().clear();
        mainPane1.getChildren().add((Node)node);
    }
    @FXML
    void dbsearch(ActionEvent event) {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("record.fxml"));
            setmode(root);


        } catch (IOException e){
            System.out.print(e);
        }
    }

}
