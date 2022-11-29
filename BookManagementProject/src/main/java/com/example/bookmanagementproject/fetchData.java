package com.example.bookmanagementproject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class fetchData {
    public static String fetchData() {
        try {
            URL url = new URL("https://opentdb.com/api.php?amount=10&category=10&type=multiple");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            StringBuilder informationString;
            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();
            }
            return informationString.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<QuizUser> getData() {
        String jsonFile = fetchData();
        ArrayList<QuizUser> questionList = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(String.valueOf(jsonFile));
            JSONObject resultObj = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) resultObj.get("results");
            for (Object o : jsonArray) {
                JSONObject object = (JSONObject) o;
                String question = (String) object.get("question");
                String difficulty = (String) object.get("difficulty");
                String correct = (String) object.get("correct_answer");
                JSONArray jsonArrayIncorrect = (JSONArray) object.get("incorrect_answers");
                ArrayList<String> incorrect = new ArrayList<>();
                for (Object value : jsonArrayIncorrect) {
                    incorrect.add(((String) value).replaceAll("&#039;", "'").replaceAll("&quot;", "\""));
                }
                questionList.add(new QuizUser(question.replaceAll("&#039;", "'").replaceAll("&quot;", "\""), difficulty, correct.replaceAll("&#039;", "'").replaceAll("&quot;", "\""), incorrect));
            }
            return questionList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
