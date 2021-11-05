package ru.test.dadata.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;
import ru.test.dadata.tools.JsonWorker;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.HashSet;
import java.util.Set;

@Component
public class Service {
    private HttpURLConnection connection;
    private static JsonWorker jsonWorker;

    public Service(HttpURLConnection connection) {
        this.connection = connection;
    }

    public void setConnection() {
        jsonWorker = new JsonWorker();
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(jsonWorker.getJsonObject().toString());
            outputStreamWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JsonArray getArrayJsonResponse() throws IOException {
         return jsonWorker.getResponseJsonArr(connection);
    }

    public void getResponse()  {
        Set<String > listStreets = new HashSet<>();
        JsonArray jsonResponseArray = null;
        try {
            jsonResponseArray = getArrayJsonResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int arrSize = jsonResponseArray.size();
        for(int i = 0; i < arrSize; i++) {
            JsonObject data = (JsonObject) jsonResponseArray.get(i);
            JsonObject obdata = (JsonObject) data.getAsJsonObject("data");
            String street = String.valueOf(obdata.get("street_with_type"));
            listStreets.add(street);
        }
        System.out.println("Количество найденных улиц: " + listStreets.size());
        System.out.println("Список найденных улиц: ");
        for(String street : listStreets){
            System.out.println(street);
        }
    }
}