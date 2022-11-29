package com.example.bookmanagementproject;

import java.util.ArrayList;

public class QuizUser {
    private String question, difficulty, correct_answer;
    private ArrayList<String> incorrect_answers;

    public QuizUser(String question, String difficulty, String correct_answer, ArrayList<String> incorrect_answers) {
        this.question = question;
        this.difficulty = difficulty;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }

    public String getQuestion() {
        return question;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public ArrayList<String> getIncorrect_answers() {
        return incorrect_answers;
    }
}
