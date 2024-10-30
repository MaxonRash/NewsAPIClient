package com.apiclient.newsapiclient.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class NewsApiDTO {
    private String status;
    private int totalResults;
    private List<ArticleDTO> articles;
}
