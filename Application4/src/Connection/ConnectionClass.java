package Connection;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

public class ConnectionClass {

    private static Connection connection = null;
    private static Statement statement = null;
    private ResultSet rs = null;
    private static PreparedStatement preparedStatement = null;
    private FileInputStream inputStream;

    public ConnectionClass() {
        getConnection();
    }

    public static void main(String[] args) {

    }

    public Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String userName = "root";
            String password = "Mimikhan007";
            String dbName = "landrecord";

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3300/" + dbName, userName, password);
            if (connection == null) {
                System.out.println("Connection can not be established");
            }
            return connection;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database Connection Failed");
        }
        return null;
    }

public void auto_load_data_ComboBox(JFXComboBox box, String query)
{

    ObservableList<String> data  = FXCollections.observableArrayList();
    try
    {
        statement = connection.createStatement();
        rs = statement.executeQuery(query);
        while (rs.next())
        {
            data.add(rs.getString(1));
        }

        box.setItems(data);
    }
    catch (Exception e){
        JOptionPane.showMessageDialog(null,e.getMessage());
    }
    finally {
        try {
            statement.close();
            connection.close();
            rs.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}

    public ResultSet execQuery(String qu) {
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(qu);
        } catch (SQLException e) {
            System.out.println("Exception at execQuery:database " + e.getLocalizedMessage());
//            TrayNotification trayNotification = new TrayNotification();
//            trayNotification.setNotificationType(NotificationType.ERROR);
//            trayNotification.setTitle("ExecQuery Function");
//            trayNotification.setMessage("Exception at execQuery:database " + e.getLocalizedMessage());
//            trayNotification.setAnimationType(AnimationType.SLIDE);
//            trayNotification.showAndDismiss(Duration.millis(4000));
            return null;
        }
        return resultSet;
    }

    public boolean execAction(String query) {
        try {
            statement = connection.createStatement();
            statement.execute(query);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception at execAction:database " + e.getLocalizedMessage());
//            TrayNotification trayNotification = new TrayNotification();
//            trayNotification.setNotificationType(NotificationType.ERROR);
//            trayNotification.setTitle("ExecAction Function");
//            trayNotification.setMessage("Exception at execAction:database " + e.getLocalizedMessage());
//            trayNotification.setAnimationType(AnimationType.SLIDE);
//            trayNotification.showAndDismiss(Duration.millis(4000));
            return false;
        }
    }

    public boolean insertPersonImage(String filePath) {

        try {
            File image = new File(filePath);
            inputStream = new FileInputStream(image);


            preparedStatement = connection.prepareStatement("insert into parowner(ImagePerson) values(?);");
            preparedStatement.setBinaryStream(1, (InputStream) inputStream, (int) (image.length()));

            preparedStatement.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage() + e.getCause());
        } finally {
        }
        return false;
    }

    public boolean insertPersonCnicImage(String filePath) {

        try {
            File image = new File(filePath);
            inputStream = new FileInputStream(image);


            preparedStatement = connection.prepareStatement("insert into parowner(ImageCnicPerson) values(?);");
            preparedStatement.setBinaryStream(1, (InputStream) inputStream, (int) (image.length()));

            preparedStatement.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage() + e.getCause());
        } finally {
        }
        return false;
    }
}
