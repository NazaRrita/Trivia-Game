package com.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TriviaApiService {
    private static final String TRIVIA_URL = "http://numbersapi.com/random?min=0&max=1000";

    public String callApi() throws IOException {
        String responseFromApi;
        try {
            responseFromApi = getResponseFromApi();
        } catch (IOException e) {
            throw new IOException(e);
        }
        return responseFromApi;
    }

    public String getResponseFromApi() throws IOException {
        URL url = new URL(TRIVIA_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));) {
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } finally {
            con.disconnect();
        }
    }
}
