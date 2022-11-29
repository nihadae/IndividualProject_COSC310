package com.example.bookmanagementproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class LoginController {
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    private Connection connect;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public static String usernameUsed;
    public void login(){
        if(Objects.equals(username.getText(), "admin")){
            String sql = "select * from Admin where username = ? and password = ?";
            connect = Database.connectDB();

            try {
                preparedStatement = connect.prepareStatement(sql);
                preparedStatement.setString(1, username.getText());
                preparedStatement.setString(2, password.getText());
                resultSet = preparedStatement.executeQuery();

                Alert alert;
                if (username.getText().isEmpty() || password.getText().isEmpty()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Admin message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill in all the blanks.");
                    alert.showAndWait();
                }
                else {
                    if (resultSet.next()){
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Admin message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully logged in.");
                        alert.showAndWait();

                        loginButton.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("DashboardAdmin.fxml"));

                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }else{
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Admin message");
                        alert.setHeaderText(null);
                        alert.setContentText("Credentials are not found. Please try again.");
                        alert.showAndWait();
                    }
                }
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            String sql = "select * from User where username = ? and password = ?";
            connect = Database.connectDB();

            try {
                preparedStatement = connect.prepareStatement(sql);
                preparedStatement.setString(1, username.getText());
                preparedStatement.setString(2, password.getText());
                resultSet = preparedStatement.executeQuery();

                Alert alert;
                if (username.getText().isEmpty() || password.getText().isEmpty()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Admin message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill in all the blanks.");
                    alert.showAndWait();
                }
                else {
                    if (resultSet.next()){
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Admin message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully logged in.");
                        alert.showAndWait();

                        loginButton.getScene().getWindow().hide();
                        usernameUsed = username.getText();
                        Parent root = FXMLLoader.load(getClass().getResource("DashboardUser.fxml"));

                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }else{
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Admin message");
                        alert.setHeaderText(null);
                        alert.setContentText("Credentials are not found. Please try again.");
                        alert.showAndWait();
                    }
                }
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}