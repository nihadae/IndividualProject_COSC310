package com.example.bookmanagementproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultPage implements Initializable {

    @FXML
    private ImageView imgView;
    @FXML
    private Label scoreLast;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgView.setImage(new Image("trophy.png"));
        scoreLast.setText("Your score is: " + QuizController.score + " out of 10.");
    }
}
