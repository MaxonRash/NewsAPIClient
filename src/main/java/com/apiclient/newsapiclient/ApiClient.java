package com.apiclient.newsapiclient;

import com.apiclient.newsapiclient.entities.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:credentials.properties")
public class ApiClient {

    private final String newsToken;

    public ApiClient(@Value("${news.token}") String newsToken) {
        this.newsToken = newsToken;
    }

    public void getNewsForQuery(Query query) {

    }
}
