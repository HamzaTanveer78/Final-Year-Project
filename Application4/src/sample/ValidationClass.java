package sample;

import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationClass {

    public boolean validateName(String text) {

        if (text.matches("([A-Z][a-z]{3}+)")) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please Check Your First Name.");
            alert.showAndWait();
            return false;
        }
    }

    public boolean validateNumber(String txt){
        boolean ans=true;
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(txt);
        if(m.find() && m.group().equals(txt))
        {
           ans=true;
        }else {
            ans=false;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please Check Your First Name.");
            alert.showAndWait();

        }
        return ans;
    }


}

