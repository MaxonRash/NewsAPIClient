package com.apiclient.newsapiclient;

import com.apiclient.newsapiclient.DTOs.NewsApiDTO;
import com.apiclient.newsapiclient.entities.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@Component
@PropertySource("classpath:credentials.properties")
public class ApiClient {

    private final String newsToken;
    private final WebClient webClient;

    public ApiClient(@Value("${news.token}") String newsToken) {
        this.newsToken = newsToken;
        this.webClient = WebClient.create("https://newsapi.org");
    }

    public NewsApiDTO getNewsForQuery(Query query) {
        //setting HTTP method
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.GET);
        //setting HTTP body
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/v2/everything")
                .queryParam("q", "{queue}")
                .queryParam("language", "{lang}")
                .queryParam("sortBy", "{sort}")
                .queryParam("from", "{date}")
                .build(query.getName(), "ru", "publishedAt", "2024-09-30")
        );
        //uri example without builder:
//        uriSpec.uri(String.format("/v2/everything?q=%s&language=ru&sortBy=publishedAt&from=2024-09-30", query.getName()))
        //example of assigning a body
//        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.body(Mono.just(new NewsApiDTO()), NewsApiDTO.class);
        //setting HTTP headers
        WebClient.ResponseSpec responseSpec = ((WebClient.RequestHeadersSpec<?>) bodySpec).header(
                HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON) //another way of app_json header
                .header("Authorization", newsToken)
                .retrieve();
        Mono<NewsApiDTO> response = responseSpec.bodyToMono(NewsApiDTO.class);

        return response.block();
        //directly on webClient:
/*        return webClient.get().uri(String.format("/v2/everything?q=%s&from=2024-09-30&apiKey=", query.getName()))
                .retrieve()
                .bodyToMono(String.class)
                .block();   */
    }
}
