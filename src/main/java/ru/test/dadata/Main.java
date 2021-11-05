package ru.test.dadata;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.test.dadata.config.SpringConfiguration;
import ru.test.dadata.service.Service;

import java.net.HttpURLConnection;

public class Main {
    public static void main(String[] args)  {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        HttpURLConnection connection = context.getBean("connection", HttpURLConnection.class);
        Service service = context.getBean("service", Service.class);
        service.setConnection();
        service.getResponse();
    }
}
