package ru.test.dadata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.test.dadata.service.Service;
import ru.test.dadata.tools.JsonWorker;
import ru.test.dadata.tools.MyHttpConnector;
import java.io.IOException;
import java.net.HttpURLConnection;

@Configuration
@ComponentScan("ru.kubancredit.dadata")
@PropertySource("application.properties")
public class SpringConfiguration {

    @Bean
    public HttpURLConnection connection() throws IOException {
    return MyHttpConnector.getConnection(ConfProperties.getProperty("url"));
    }

    @Bean
    public Service service() throws IOException {
        return new Service(connection());
    }

    @Bean
    public JsonWorker jsonWorker() {
        return new JsonWorker();
    }


}
