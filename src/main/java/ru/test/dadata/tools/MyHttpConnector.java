package ru.test.dadata.tools;

import org.springframework.stereotype.Component;
import ru.test.dadata.config.ConfProperties;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class MyHttpConnector {
        public static HttpURLConnection getConnection(String u) throws IOException {
        URL url = new URL(u);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization", "Token " + ConfProperties.getProperty("token"));
        connection.setDoOutput(true);
//        connection.setDoInput(true);
        return connection;
    }
}
