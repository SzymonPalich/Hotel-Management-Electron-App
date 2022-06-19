package com.spurvago.configuration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Konfiguracja połączenia z bazą danych
 */
@Configuration
public class DbConfig {
    @Bean
    public DataSource getDataSource() {
        JSONParser jsonParser = new JSONParser();
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        try(Reader reader = new FileReader("data.json")) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            if(jsonObject.get("port")!=""){
                dataSourceBuilder.url("jdbc:mysql://" + jsonObject.get("server") + ":" + jsonObject.get("port") + "/" + jsonObject.get("database"));

            }
            else {
                dataSourceBuilder.url("jdbc:mysql://localhost:3306/hotel");
            }
            dataSourceBuilder.username(String.valueOf(jsonObject.get("username")));
            dataSourceBuilder.password(String.valueOf(jsonObject.get("password")));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return dataSourceBuilder.build();
    }
}
