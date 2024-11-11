package com.apiclient.newsapiclient.kafka;

import com.apiclient.newsapiclient.entities.Query;
import com.apiclient.newsapiclient.kafka.entities.ListOfActiveQueries;
import com.apiclient.newsapiclient.services.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaActiveQueriesListener {
    QueryService queryService;

    @Autowired
    public KafkaActiveQueriesListener(QueryService queryService) {
        this.queryService = queryService;
    }

    @org.springframework.kafka.annotation.KafkaListener(topicPartitions = @TopicPartition(
            topic = "activeQueries", partitionOffsets = {@PartitionOffset(partition = "0", initialOffset = "0")}
    ), properties = {"spring.json.value.default.type=com.apiclient.newsapiclient.kafka.entities.ListOfActiveQueries"})
    public void consumeTransactionEvent(@Payload ListOfActiveQueries activeQueries
                                        /*@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key,
                                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp*/) {
        if (activeQueries.getActiveQueries().size() != 1) {
            log.info("Received initial active queries list. Size is: " + activeQueries.getActiveQueries().size());
        }
        else {
            log.info("Received new active query. The name is: " + activeQueries.getActiveQueries().get(0));
        }
        var queriesListFromDB = queryService.findAll();
        if (!queriesListFromDB.isEmpty()) {
            var finalListOfActiveQueries = activeQueries.getActiveQueries().stream().filter(queryString -> !queriesListFromDB.stream().map(Query::getName).toList()
                    .contains(queryString)).toList();
            finalListOfActiveQueries.forEach(updatedQueryString -> queryService.save(new Query(updatedQueryString)));
            log.info("Added all new active queries to DB. Size is: " + finalListOfActiveQueries.size());
        }
        else {
            activeQueries.getActiveQueries().forEach(updatedQueryString -> queryService.save(new Query(updatedQueryString)));
            log.info("Added all received active queries to DB. Size is: " + activeQueries.getActiveQueries().size());
        }
    }
}
