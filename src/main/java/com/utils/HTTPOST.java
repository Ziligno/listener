/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utils;

import com.utils.pojo.SmsPojo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author drnefario
 */
public class HTTPOST {
      public static void main(String[] args) {
        String url = "http://localhost:9999/api/logSms";
        String jsonInputString = "{\"name\": \"John\", \"age\": 30}";

        try {
//            postRequest(url, jsonInputString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public static void postRequest(String urlString, String jsonObject) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setDoOutput(true);

        // Convert JSON object to string and write to output stream
        try (OutputStream os = httpURLConnection.getOutputStream()) {
            byte[] input = jsonObject.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = httpURLConnection.getResponseCode();
        System.out.println("Response Code: " + code);

        try {
            if (code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_CREATED) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println("Response: " + response.toString());
                }
            } else {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getErrorStream(), "utf-8"))) {
                    StringBuilder errorResponse = new StringBuilder();
                    String errorLine;
                    while ((errorLine = br.readLine()) != null) {
                        errorResponse.append(errorLine.trim());
                    }
                    System.err.println("Error Response: " + errorResponse.toString());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: The requested resource could not be found.");
            throw e;
        } catch (IOException e) {
            System.err.println("IOException: An I/O error occurred while reading the response.");
            throw e;
        }
    }
}