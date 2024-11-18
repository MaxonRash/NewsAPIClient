package com.apiclient.newsapiclient;

import com.apiclient.newsapiclient.jobs.GetNewsForQueryAndSendToKafka;
import com.apiclient.newsapiclient.kafka.KafkaQueriesNewsSender;
import com.apiclient.newsapiclient.services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestSaveAndDeleteMongo implements CommandLineRunner {
    QueryService queryService;
    ApiClient apiClient;
    KafkaQueriesNewsSender kafkaSender;
    GetNewsForQueryAndSendToKafka getNewsForQueryAndSendToKafka;

    @Autowired
    public TestSaveAndDeleteMongo(QueryService queryService, ApiClient apiClient, KafkaQueriesNewsSender kafkaSender,
                                  GetNewsForQueryAndSendToKafka getNewsForQueryAndSendToKafka) {
        this.queryService = queryService;
        this.apiClient = apiClient ;
        this.kafkaSender = kafkaSender;
        this.getNewsForQueryAndSendToKafka = getNewsForQueryAndSendToKafka;
    }

    @Override
    public void run(String... args) {
//        Query query = new Query();
//        query.setName("nvidia");
//        queryService.save(query);
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        getNewsForQueryAndSendToKafka.execute();
    }
}
