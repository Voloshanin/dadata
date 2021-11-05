package ru.test.dadata.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

@Component
public class JsonWorker {
    private  JsonParser parser = new JsonParser();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public JsonObject getJsonObject(){
        System.out.println("Введите название улицы/фрагмент названия улицы:");
        String inputAddress = null;
        try {
            inputAddress = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonInputString = "{ \"query\": \"Краснодар " + inputAddress + "\" }";
        return  (JsonObject)parser.parse(jsonInputString);
    }

    public JsonArray getResponseJsonArr(HttpURLConnection connection) throws IOException {
        StringBuilder response = new StringBuilder();
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
         JsonObject jsonResponse = (JsonObject) parser.parse(response.toString());
         return jsonResponse.getAsJsonArray("suggestions");
    }
}
