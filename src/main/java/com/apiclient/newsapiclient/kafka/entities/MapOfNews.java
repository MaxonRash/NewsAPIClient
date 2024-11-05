package com.apiclient.newsapiclient.kafka.entities;

import lombok.Data;

import java.util.LinkedHashMap;

@Data
public class MapOfNews {
    private LinkedHashMap<String, ListOfNewsObject> queriesNewsMap;

    public MapOfNews(LinkedHashMap<String, ListOfNewsObject> queriesNewsMap) {
        this.queriesNewsMap = queriesNewsMap;
    }
}
