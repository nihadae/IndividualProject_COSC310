package com.example.bookmanagementproject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class QuoteGet {
    public static String fetchData() {
        try {
            URL url = new URL("https://zenquotes.io/api/random");

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

    public static ArrayList<String> getData() {
        String jsonFile = fetchData();
        ArrayList<String> quoteAndAuthor = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(String.valueOf(jsonFile));
            JSONArray jsonArray = (JSONArray) obj;
            String quote = "";
            String author = "";
            for (Object o : jsonArray) {
                JSONObject object1 = (JSONObject) o;
                quote = (String) object1.get("q");
                author = (String) object1.get("a");

            }
            quoteAndAuthor.add(quote);
            quoteAndAuthor.add(author);
            return quoteAndAuthor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
