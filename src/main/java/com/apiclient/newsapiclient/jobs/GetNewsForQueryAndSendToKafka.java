package com.apiclient.newsapiclient.jobs;

import com.apiclient.newsapiclient.ApiClient;
import com.apiclient.newsapiclient.entities.Query;
import com.apiclient.newsapiclient.kafka.KafkaQueriesNewsSender;
import com.apiclient.newsapiclient.kafka.entities.ListOfNewsObject;
import com.apiclient.newsapiclient.kafka.entities.MapOfNews;
import com.apiclient.newsapiclient.kafka.entities.NewsApiDTOToListOfNewsObjectConverter;
import com.apiclient.newsapiclient.kafka.entities.NewsObject;
import com.apiclient.newsapiclient.services.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GetNewsForQueryAndSendToKafka {
    private final ApiClient apiClient;
    private final QueryService queryService;
    private final KafkaQueriesNewsSender kafkaSender;

    @Autowired
    public GetNewsForQueryAndSendToKafka(ApiClient apiClient, QueryService queryService, KafkaQueriesNewsSender kafkaSender) {
        this.apiClient = apiClient;
        this.queryService = queryService;
        this.kafkaSender = kafkaSender;
    }

    @Scheduled(fixedRateString = "${bot.client.getArticlesFixedRate}")
    public void execute() {
        LinkedHashMap<String, ListOfNewsObject> queriesNewsMap = new LinkedHashMap<>();
        log.info("Job GetNewsForQueryAndSendToKafka started");
        var queries = queryService.findAll();
        log.info("Found queries from mongo. Quantity: " + queries.size());
        if (queries.size() == 0) {
            log.info("No active queries found in MongoDB");
        }
        else {
            Map<String, Query> map = queries.stream().collect(Collectors.toMap(
                    Query::getName, Function.identity(), (e1, e2) -> {
                        throw new RuntimeException();
                    }, LinkedHashMap::new));
//        map.forEach((k,v) -> log.info("key is " + k + " : value is " + v));
            for (Map.Entry<String, Query> entry : map.entrySet()) {
                queriesNewsMap.put(entry.getKey(), NewsApiDTOToListOfNewsObjectConverter.convert(apiClient.getNewsForQuery(entry.getValue())));
            }
//        kafkaSender.sendMessageToQueriesNewsTopic(queriesNewsMap);
            MapOfNews mapOfNews = new MapOfNews(queriesNewsMap);
            kafkaSender.sendMessageToQueriesNewsTopic(mapOfNews);
            log.info("Sent queriesNewsMap to Kafka topic \"queriesNews\". Size of the map is: " + mapOfNews.getQueriesNewsMap().size());
        }
    }
}
