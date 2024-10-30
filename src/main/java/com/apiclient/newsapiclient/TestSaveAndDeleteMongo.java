package com.apiclient.newsapiclient;

import com.apiclient.newsapiclient.entities.Query;
import com.apiclient.newsapiclient.services.QueryService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.internal.MongoClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestSaveAndDeleteMongo implements CommandLineRunner {
    QueryService queryService;

    @Autowired
    public TestSaveAndDeleteMongo(QueryService queryService) {
        this.queryService = queryService;
    }

    @Override
    public void run(String... args) {
        Query query = new Query();
        query.setName("tesla2");
        queryService.save(query);
        System.out.println(queryService.findAll());
//        System.out.println(queryService.save(query));
//        System.out.println(queryService.findOne(query));
//        queryService.delete(query);
    }
}
