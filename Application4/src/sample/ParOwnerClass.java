package sample;

import Connection.ConnectionClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParOwnerClass implements Initializable {

    ValidationClass v = new ValidationClass();
    String filename = null;

    private FileInputStream fis;
    private FileInputStream fis1;
    private String imageFile;
    private String imageFile1;

    private FileChooser fileChooser;
    private FileChooser fileChooser1;

    ObservableList<String> tehsildata = FXCollections.observableArrayList();
    ObservableList<String> districtData = FXCollections.observableArrayList();
    ObservableList<String> divisionData = FXCollections.observableArrayList();

    @FXML
    private ImageView personCnicImage;

    @FXML
    private ImageView personImage;
    @FXML
    private JFXButton personCnicPhoto;

    @FXML
    private JFXButton personPhoto;

    @FXML
    private JFXButton savebtn;

    @FXML
    private JFXButton parback;

    @FXML
    private JFXButton parcancel;

    @FXML
    private JFXTextField parFirstNametxt;

    @FXML
    private JFXTextField parLastNametxt;

    @FXML
    private JFXTextField parCnictxt;

    @FXML
    private JFXTextField parUsertxt;

    @FXML
    private JFXDatePicker parDatetxt;

    @FXML
    private JFXTextField parAddresstxt;

    @FXML
    private JFXComboBox<String> parStatustxt;

    @FXML
    private JFXTextField parProvincetxt;

    @FXML
    private JFXComboBox<String> parTehsiltxt;

    @FXML
    private JFXComboBox<String> parDistricttxt;

    @FXML
    private JFXComboBox<String>  parDivisiontxt;

    @FXML
    private JFXTextField parPhonetxt;

    @FXML
    private JFXTextField parEmPhonetxt;

    @FXML
    private JFXComboBox<String> parGendertxt;

    @FXML
    private JFXTextField parFatherNametxt;

    @FXML
    private JFXTextField parFatherCNICtxt;

    @FXML
    private Label personLabel;

    @FXML
    private Label personCnicLabeel;
    
    private ConnectionClass connectionClass;
    private File file;
    private File file1;
   Alert alert = new Alert(Alert.AlertType.WARNING);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectionClass = new ConnectionClass();
       // connectionClass.auto_load_data_ComboBox(parDistricttxt,"se");
        getDivision();
    }
    private boolean clearfield(){
        boolean ans = false;
        if (parUsertxt.getText().isEmpty() || parUsertxt.getText().trim().isEmpty()) {
            alert.setHeaderText("Operation Failed");
            alert.setContentText("Test Field is Empty");
            alert.showAndWait();
        } else if (parFirstNametxt.getText().isEmpty() || parFirstNametxt.getText().trim().isEmpty()) {
            alert.setHeaderText("Operation Failed");
            alert.setContentText("Test Field is Empty");
            alert.showAndWait();
        } else if (parLastNametxt.getText().isEmpty() || parLastNametxt.getText().trim().isEmpty()) {
            alert.setHeaderText("Operation Failed");
            alert.setContentText("Test Field is Empty");
            alert.showAndWait();
        } else {
            ans = true;
        }
        return ans;
    }



    @FXML
    void dbsave(ActionEvent event) throws SQLException, FileNotFoundException {

        if (clearfield() == true) {
            if (validatefirstName() & validatelastName() & validateFatherName() & validatePhoneNo() & validateemPhoneNo()) {

                ConnectionClass connectionClass = new ConnectionClass();

                Connection connection = connectionClass.getConnection();

                String query = "insert into parowner(UserName, FirstName, LastName, CNIC, DateOfBirth, Address, Status, Province, Tehsil, District, Division, PhoneNo, EmergencyPhoneNo, Gender, FatherName, FatherCNIC,ImagePerson,ImageCnicPerson)  " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, parUsertxt.getText());
                preparedStatement.setString(2, parFirstNametxt.getText());
                preparedStatement.setString(3, parLastNametxt.getText());
                preparedStatement.setString(4, parCnictxt.getText());
                preparedStatement.setString(5, parDatetxt.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                preparedStatement.setString(6, parAddresstxt.getText());
                preparedStatement.setString(7, parStatustxt.getSelectionModel().getSelectedItem());
                preparedStatement.setString(8, parProvincetxt.getText());
                preparedStatement.setString(9, parTehsiltxt.getSelectionModel().getSelectedItem());
                preparedStatement.setString(10, parDistricttxt.getSelectionModel().getSelectedItem());
                preparedStatement.setString(11, parDivisiontxt.getSelectionModel().getSelectedItem());
                preparedStatement.setString(12, parPhonetxt.getText());
                preparedStatement.setString(13, parEmPhonetxt.getText());
                preparedStatement.setString(14, parGendertxt.getSelectionModel().getSelectedItem());
                preparedStatement.setString(15, parFatherNametxt.getText());
                preparedStatement.setString(16, parFatherCNICtxt.getText());
                fis = new FileInputStream(file);
                preparedStatement.setBinaryStream(17, (InputStream) fis, (int) file.length());
                fis1 = new FileInputStream(file1);
                preparedStatement.setBinaryStream(18, (InputStream) fis1, (int) file1.length());
                int success = preparedStatement.executeUpdate();

                if (success == 1) {
                    System.out.println("Data Successful Save");
                    savebtn.getScene().getWindow().hide();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("parLandRecord.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException ex) {
                        System.out.print(ex.getLocalizedMessage() + "\n" + ex.getMessage());
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Data Insertion Failed");
                    alert.show();
                }

                JOptionPane.showMessageDialog(null, "Insert Successfully");

            }
        }


    }

    @FXML
    void personCnicbtn() throws MalformedURLException {

        fileChooser1 = new FileChooser();
        fileChooser1.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Select Image","*.png","*.jpg","*.gif"));
        file1 = fileChooser1.showOpenDialog(null);
        if (file1 != null){
            imageFile1 = file1.toURI().toURL().toString();

            Image image1 = new Image(imageFile1);
            personCnicImage.setImage(image1);

        }else{
            System.out.println("image Error");

        }

    }

            @FXML
            void personPhotobtn (ActionEvent event) throws MalformedURLException {
                fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Select Image", "*.png", "*.jpg", "*.gif"));
                file = fileChooser.showOpenDialog(null);
                if (file != null) {
                    imageFile = file.toURI().toURL().toString();

                    Image image = new Image(imageFile);
                    personImage.setImage(image);

                } else {
                    System.out.println("Person image Error ");

                }
            }
            private boolean validatefield(){
                    boolean ans=true;
                if (!v.validateName(parFirstNametxt.getText())) {
                    ans=false;
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Check Your First Name.");
                    alert.showAndWait();
                    return false;
                }
                else if (!v.validateNumber(parLastNametxt.getText())) {
                    ans=false;
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Check Your Last Name.");
                    alert.showAndWait();
                    return false;
                }

                return ans;
            }
    private boolean validatefirstName() {

        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(parFirstNametxt.getText());
        if(m.find() && m.group().equals(parFirstNametxt.getText()))
        {
            return true;
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please Check Your First Name.");
            alert.showAndWait();
            return false;
        }
    }
    private boolean validatelastName() {
          Pattern p = Pattern.compile("[a-zA-Z]+");
          Matcher m = p.matcher(parLastNametxt.getText());
          if(m.find() && m.group().equals(parLastNametxt.getText()))
          {
              return true;
          }else {
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setTitle("invalid Input");
              alert.setHeaderText(null);
              alert.setContentText("Please Check Your Last Name.");
              alert.showAndWait();
              return false;
          }
    }


    public void getTehsil(){

        String district = parDistricttxt.getSelectionModel().getSelectedItem();

        String query = "SELECT DISTINCT Tehsil_Name FROM city WHERE District_Name = '" + district + "' ";

        ResultSet rs = connectionClass.execQuery(query);

        try{
            while (rs.next()){
                tehsildata.add(rs.getString(1));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        parTehsiltxt.setItems(tehsildata);
    }

    @FXML
    private void getDistrict(){

        String division = parDivisiontxt.getSelectionModel().getSelectedItem();
        String query = "SELECT DISTINCT District_Name FROM city WHERE division_Name = '"+ division+"' ";

        ResultSet rs = connectionClass.execQuery(query);

        try{
            while (rs.next()){
                districtData.add(rs.getString(1));

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        parDistricttxt.setItems(districtData);
    }

    private void getDivision() {
        String query = "SELECT DISTINCT division_Name FROM city ";
        ResultSet rs = connectionClass.execQuery(query);
        try {
            while (rs.next()) {
                divisionData.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        parDivisiontxt.setItems(divisionData);
    }
    private boolean validateFatherName() {


        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(parFatherNametxt.getText());
        if(m.find() && m.group().equals(parFatherNametxt.getText()))
        {
            return true;
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please Check Your Father Name.");
            alert.showAndWait();
            return false;
        }
    }
    private boolean validatePhoneNo(){
        Pattern pattern = Pattern.compile("[0-9]+"); //Pattern pattern = Pattern.compile("(0|92)?[3][0-9]{9}");
        Matcher matcher = pattern.matcher(parPhonetxt.getText());
        if (matcher.find() & matcher.group().equals(parPhonetxt.getText())){
            return true;
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Check Phone Number");
            alert.showAndWait();
            return false;
        }

    }
    private boolean validateemPhoneNo(){
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(parEmPhonetxt.getText());
        if (matcher.find() & matcher.group().equals(parEmPhonetxt.getText())){
            return true;
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Check Emergency Phone Number");
            alert.showAndWait();
            return false;
        }

    }




}

//    public void handleFNTF(KeyEvent keyEvent) {
//
//        String firstName = parFirstNametxt.getText();
//
//        if (v.validateName(firstName)) {
//            System.out.println("yes");
//        } else {
//            System.out.println("no");
//        }
//    }

/*
 public void getDistrict(){

        String query = "SELECT DISTINCT District FROM parcity; ";

        ResultSet rs = connectionClass.execQuery(query);

        try{
            while (rs.next()){
                districtData.add(rs.getString(1));

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        parDistricttxt.setItems(districtData);
    }
* */








//    private boolean validateProvince() {
//
//        String parProvince = parProvincetxt.getText();
//
//        if (parProvince.matches("^([A-Z][a-z]+)")) {
//            return true;
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setContentText("Please Check Your Province Name.");
//            alert.showAndWait();
//            return false;
//        }
//    }
//    private boolean validatetehsil() {
//
//        Pattern p = Pattern.compile("[a-zA-Z]+");
//        Matcher m = p.matcher(parTehsiltxt.getText());
//        if(m.find() && m.group().equals(parTehsiltxt.getText()))
//        {
//            return true;
//        }else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("invalid Input");
//            alert.setHeaderText(null);
//            alert.setContentText("Please Check Your Last Name.");
//            alert.showAndWait();
//            return false;
//        }
//    }
//    private boolean validateDistrict() {
//
//        Pattern p = Pattern.compile("[a-zA-Z]+");
//        Matcher m = p.matcher(parDistricttxt.getText());
//        if(m.find() && m.group().equals(parDistricttxt.getText()))
//        {
//            return true;
//        }else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("invalid Input");
//            alert.setHeaderText(null);
//            alert.setContentText("Please Check Your Last Name.");
//            alert.showAndWait();
//            return false;
//        }
//    }


/*
*
* // String query2 = "INSERT INTO `landrecord`.`parowner` VALUES('"+parFirstNametxt.getText()+"')";
        // String query3 = "INSERT INTO `landrecord`.`parowner`(`UserName`) VALUES('parUsertxt.getText()')";
        // Statement statement = connection.createStatement();
        // statement.executeUpdate(query3);
        // System.out.println(parDatetxt.getValue());


        String user = parUsertxt.getText();
        String first = parFirstNametxt.getText();
        String last = parLastNametxt.getText();
        String cnic = parCnictxt.getText();
        String date = parDatetxt.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String address = parAddresstxt.getText();
        String status = parStatustxt.getText();
        String province = parProvincetxt.getText();
        String district = parDistricttxt.getText();
        String tehsil = parTehsiltxt.getText();
        String Division = parDivisiontxt.getText();
        String phone = parPhonetxt.getText();
        String ephone = parEmPhonetxt.getText();
        String gender = parGendertxt.getSelectionModel().getSelectedItem();
        String father = parFatherNametxt.getText();
        String fathercnic = parFatherCNICtxt.getText();

        String personFilePath = file.getAbsolutePath();
        String cnicFilePath = file1.getAbsolutePath();


        System.out.println(user);


        String query = "insert into parowner(UserName, FirstName, LastName, CNIC, DateOfBirth, Address, Status, Province, Tehsil, District, Division, PhoneNo, EmergencyPhoneNo, Gender, FatherName, FatherCNIC)  " +
                "VALUES ('" + user + "','" + first + "','" + last + "','" + cnic + "','" + date + "','" + address + "','" + status + "','" + province + "','" + tehsil + "','" + district + "','" + Division + "','" + phone + "','" + ephone + "','" + gender + "','" + father + "','" + fathercnic + "')";



        if (connectionClass.execAction(query)) {
            if (connectionClass.insertPersonImage(personFilePath) && connectionClass.insertPersonImage(cnicFilePath)){
                System.out.println("Data Successful Save");
            savebtn.getScene().getWindow().hide();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("parLandRecord.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.print(ex.getLocalizedMessage() + "\n" + ex.getMessage());
            }

        }else{
                System.out.println("Insertion Failed due to images.");
            }
        } else {
            System.out.println("Insertion fail due to query data.");
        }

* */
















/*
ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getconnection();
    String query =  "INSERT INTO `landrecord`.`parowner`\n" +
            "(`UserName`,\n" +
            "`FirstName`,\n" +
            "`LastName`,\n" +
            "`CNIC`,\n" +
            "`DateOfBirth`,\n" +
            "`Address`,\n" +
            "`Status`,\n" +
            "`Province`,\n" +
            "`Tehsil`,\n" +
            "`District`,\n" +
            "`Division`,\n" +
            "`PhoneNo`,\n" +
            "`EmergencyPhoneNo`,\n" +
            "`Gender`,\n" +
            "`FatherName`,\n" +
            "`FatherCNIC`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    // String query2 = "INSERT INTO `landrecord`.`parowner` VALUES('"+parFirstNametxt.getText()+"')";
    // String query3 = "INSERT INTO `landrecord`.`parowner`(`UserName`) VALUES('parUsertxt.getText()')";
    // Statement statement = connection.createStatement();
    // statement.executeUpdate(query3);
    // System.out.println(parDatetxt.getValue());

      // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // String date = simpleDateFormat.format(parDatetxt.getUserData());
    PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, parUsertxt.getText());
                preparedStatement.setString(2,parFirstNametxt.getText());
                preparedStatement.setString(3,parLastNametxt.getText());
                preparedStatement.setString(4,parCnictxt.getText());
                // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                // String date = simpleDateFormat.format(parDatetxt.getUserData());
                preparedStatement.setString(5,parDatetxt.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                preparedStatement.setString(6,parAddresstxt.getText());
                preparedStatement.setString(7,parStatustxt.getText());
                preparedStatement.setString(8,parProvincetxt.getText());
                preparedStatement.setString(9,parTehsiltxt.getText());
                preparedStatement.setString(10,parDistricttxt.getText());
                preparedStatement.setString(11,parDivisiontxt.getText());
                preparedStatement.setString(12,parPhonetxt.getText());
                preparedStatement.setString(13,parEmPhonetxt.getText());
                preparedStatement.setString(14, (String) parGendertxt.getSelectionModel().getSelectedItem());
                preparedStatement.setString(15,parFatherNametxt.getText());
                preparedStatement.setString(16,parFatherCNICtxt.getText());
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Insert Successfully");
                
                */
