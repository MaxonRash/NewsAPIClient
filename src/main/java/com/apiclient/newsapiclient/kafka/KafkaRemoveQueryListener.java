package com.apiclient.newsapiclient.kafka;

import com.apiclient.newsapiclient.entities.Query;
import com.apiclient.newsapiclient.kafka.entities.RemoveQueryObject;
import com.apiclient.newsapiclient.services.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaRemoveQueryListener {
    private QueryService queryService;

    @Autowired
    public KafkaRemoveQueryListener(QueryService queryService) {
        this.queryService = queryService;
    }

    @KafkaListener(topicPartitions = @TopicPartition(
            topic = "removeQuery", partitionOffsets = {@PartitionOffset(partition = "0", initialOffset = "0")}
    ), properties = {"spring.json.value.default.type=com.apiclient.newsapiclient.kafka.entities.RemoveQueryObject"})
    public void consumeTransactionEvent(@Payload RemoveQueryObject removeQueryObject) {
        log.info("Received query to remove from active. Name: " + removeQueryObject.getName());
        Query query = queryService.deleteQueryByNameIgnoreCase(removeQueryObject.getName());
        if (query != null) {
            log.info(String.format("Query with name %s successfully deleted from db", removeQueryObject.getName()));
        } else {
            log.info(String.format("Query with name %s is not found for deletion", removeQueryObject.getName()));
        }
    }
}
