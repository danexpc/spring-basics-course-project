package com.yet.spring.core.spring;

import com.yet.spring.core.beans.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.text.DateFormat;
import java.util.Date;

@Configuration
@PropertySource("classpath:client.properties")
@ComponentScan("com.yet.spring.core")
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public Date newDate() {
        return new Date();
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateInstance();
    }

    @Bean
    public Client client() {
        var client = new Client();
        client.setId(Integer.parseInt(environment.getRequiredProperty("id")));
        client.setFullName(environment.getRequiredProperty("name"));
        client.setGreeting(environment.getRequiredProperty("greeting"));
        return client;
    }
}
