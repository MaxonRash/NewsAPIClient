package com.apiclient.newsapiclient.kafka;

import com.apiclient.newsapiclient.kafka.entities.ListOfNewsObject;
import com.apiclient.newsapiclient.kafka.entities.MapOfNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaSender {
    private KafkaTemplate<String, MapOfNews> kafkaTemplate;

    @Autowired
    public KafkaSender(KafkaTemplate<String, MapOfNews> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topicName, MapOfNews newsMap) {
        kafkaTemplate.send(topicName, newsMap);
    }
}
