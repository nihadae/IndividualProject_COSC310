package com.example.bookmanagementproject;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.xml.transform.Result;
import java.sql.*;

public class CheckoutBooks {
    @FXML
    public TextField ISBNfield;

    @FXML
    public Button cancelField;

    @FXML
    public Button checkoutField;

    @FXML
    public TextField usernameField;

    public Connection connect;
    public PreparedStatement preparedStatement;

    public ResultSet resultSet;
    public int assignBook(String username, String ISBN){

        String sql = "insert into Assigned values(?,?,?,?)";
        String sql1 = "update Book set inventory = ? where ISBN = ?";
        String sql2 = "select inventory from Book where ISBN = ?";
        connect = Database.connectDB();
        Alert alert;
        int rstTest = 0;
        try {
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,ISBN);
            preparedStatement.setDate(3, Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.setDate(4, Date.valueOf(java.time.LocalDate.now().plusDays(14)));
            rstTest = preparedStatement.executeUpdate();
            preparedStatement = connect.prepareStatement(sql2);
            preparedStatement.setString(1, ISBNfield.getText());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                preparedStatement = connect.prepareStatement(sql1);
                int inventory = resultSet.getInt(1);
                preparedStatement.setInt(1, inventory-1);
                preparedStatement.setString(2, ISBNfield.getText());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Admin message");
            alert.setHeaderText(null);
            alert.setContentText("There is no user or book to checkout.");
            alert.showAndWait();
        }
        return rstTest;
    }


    public void assignSave(){
        assignBook(usernameField.getText(), ISBNfield.getText());
        checkoutField.getScene().getWindow().hide();
    }

    public void cancel(){
        cancelField.getScene().getWindow().hide();
    }


}
