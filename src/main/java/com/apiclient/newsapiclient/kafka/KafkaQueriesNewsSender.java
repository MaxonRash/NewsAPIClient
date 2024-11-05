package com.apiclient.newsapiclient.kafka;

import com.apiclient.newsapiclient.kafka.entities.ListOfNewsObject;
import com.apiclient.newsapiclient.kafka.entities.MapOfNews;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaQueriesNewsSender extends KafkaSender{
    public KafkaQueriesNewsSender(KafkaTemplate<String, MapOfNews> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    public void sendMessage(String topicName, MapOfNews newsMap) {
        super.sendMessage(topicName, newsMap);
    }

    public void sendMessageToQueriesNewsTopic(MapOfNews newsMap) {
        super.sendMessage("queriesNews", newsMap);
    }
}
