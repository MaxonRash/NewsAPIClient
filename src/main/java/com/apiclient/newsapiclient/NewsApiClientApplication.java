package com.apiclient.newsapiclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication/*(exclude={EmbeddedMongoAutoConfiguration.class})*/
@EnableMongoRepositories(basePackages = "com.apiclient.newsapiclient.repositories")
public class NewsApiClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewsApiClientApplication.class, args);
    }

}
