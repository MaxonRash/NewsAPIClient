package com.apiclient.newsapiclient.kafka;

import com.apiclient.newsapiclient.kafka.entities.ListOfNewsObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaQueriesNewsSender extends KafkaSender{
    public KafkaQueriesNewsSender(KafkaTemplate<String, ListOfNewsObject> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    public void sendMessage(String topicName, ListOfNewsObject newsList) {
        super.sendMessage(topicName, newsList);
    }

    public void sendMessageToQueriesNewsTopic(ListOfNewsObject newsList) {
        super.sendMessage("queriesNews", newsList);
    }
}
