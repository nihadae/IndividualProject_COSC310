package com.example.bookmanagementproject;

import javafx.animation.PauseTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class QuizController implements Initializable {

    public static int score = 0;
    int no = 0;
    int questionNum = 0;
    ArrayList<QuizUser> list = fetchData.getData();

    @FXML
    private Button ans1;

    @FXML
    private Button ans2;

    @FXML
    private Button ans3;

    @FXML
    private Button ans4;


    @FXML
    private Label question;

    @FXML
    private Label diff;

    public void loadQuestion(ActionEvent actionEvent) {
        if (questionNum == 0) {
            if (actionEvent.getSource() == ans1) {
                if (ans1.getText().equals(list.get(0).getCorrect_answer())) {
                    ans1.setStyle("-fx-base: lightgreen");
                    score++;
                }else
                    ans1.setStyle("-fx-background-color: tomato");
            } else if (actionEvent.getSource() == ans2) {
                if (ans2.getText().equals(list.get(0).getCorrect_answer())) {
                    ans2.setStyle("-fx-base: lightgreen");
                    score++;
                }else
                    ans2.setStyle("-fx-background-color: tomato");
            } else if (actionEvent.getSource() == ans3) {
                if (ans3.getText().equals(list.get(0).getCorrect_answer())) {
                    ans3.setStyle("-fx-base: lightgreen");
                    score++;
                }else
                    ans3.setStyle("-fx-background-color: tomato");
            } else if (actionEvent.getSource() == ans4) {
                if (ans4.getText().equals(list.get(0).getCorrect_answer())) {
                    ans4.setStyle("-fx-base: lightgreen");
                    score++;
                }else
                    ans4.setStyle("-fx-background-color: tomato");
            }
        } else {
            if (actionEvent.getSource() == ans1) {
                if (ans1.getText().equals(list.get(questionNum).getCorrect_answer())) {
                    ans1.setStyle("-fx-background-color: lightgreen");
                    score++;
                }else
                    ans1.setStyle("-fx-background-color: tomato");
            } else if (actionEvent.getSource() == ans2) {
                if (ans2.getText().equals(list.get(questionNum).getCorrect_answer())) {
                    ans2.setStyle("-fx-background-color: lightgreen");
                    score++;
                }else
                    ans2.setStyle("-fx-background-color: tomato");
            } else if (actionEvent.getSource() == ans3) {
                if (ans3.getText().equals(list.get(questionNum).getCorrect_answer())) {
                    ans3.setStyle("-fx-background-color: lightgreen");
                    score++;
                }else
                    ans3.setStyle("-fx-background-color: tomato");
            } else if (actionEvent.getSource() == ans4) {
                if (ans4.getText().equals(list.get(questionNum).getCorrect_answer())) {
                    ans4.setStyle("-fx-background-color: lightgreen");
                    score++;
                }else
                    ans4.setStyle("-fx-background-color: tomato");
            }
        }
        Stage stage = (Stage) question.getScene().getWindow();
        PauseTransition pause = new PauseTransition(
                Duration.seconds(0.5)
                );
        pause.setOnFinished(event -> {
            ans1.setStyle("-fx-background-color: white");
            ans2.setStyle("-fx-background-color: white");
            ans3.setStyle("-fx-background-color: white");
            ans4.setStyle("-fx-background-color: white");
            questionNum++;
            if (questionNum < 10) {
                ArrayList<String> allOptions = new ArrayList<>();
                allOptions.add(list.get(questionNum).getCorrect_answer());
                allOptions.addAll(list.get(questionNum).getIncorrect_answers());
                Collections.shuffle(allOptions);
                question.setText(list.get(questionNum).getQuestion());
                ans1.setText(allOptions.get(0));
                ans2.setText(allOptions.get(1));
                ans3.setText(allOptions.get(2));
                ans4.setText(allOptions.get(3));
                diff.setText("Difficulty level: " +list.get(questionNum).getDifficulty());
            } else {
                try{
                    stage.hide();
                    Parent root = FXMLLoader.load(getClass().getResource("ResultPage.fxml"));
                    Stage stage1 = new Stage();
                    Scene scene = new Scene(root);
                    stage1.setScene(scene);
                    stage1.show();
                    score = 0;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        pause.play();
    }

    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        question.setText(list.get(0).getQuestion());
        ArrayList<String> allOptions = new ArrayList<>();
        allOptions.add(list.get(0).getCorrect_answer());
        allOptions.addAll(list.get(0).getIncorrect_answers());
        Collections.shuffle(allOptions);
        question.setText(list.get(0).getQuestion());
        ans1.setText(allOptions.get(0));
        ans2.setText(allOptions.get(1));
        ans3.setText(allOptions.get(2));
        ans4.setText(allOptions.get(3));
        diff.setText("Difficulty level: " +list.get(0).getDifficulty());
    }
}
