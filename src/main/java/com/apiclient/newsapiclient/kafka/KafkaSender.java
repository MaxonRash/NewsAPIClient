package com.apiclient.newsapiclient.kafka;

import com.apiclient.newsapiclient.kafka.entities.ListOfNewsObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender {
    private KafkaTemplate<String, ListOfNewsObject> kafkaTemplate;

    @Autowired
    public KafkaSender(KafkaTemplate<String, ListOfNewsObject> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topicName, ListOfNewsObject newsList) {
        kafkaTemplate.send(topicName, newsList);
    }
}
