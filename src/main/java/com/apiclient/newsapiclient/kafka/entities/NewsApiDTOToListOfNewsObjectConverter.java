package com.apiclient.newsapiclient.kafka.entities;

import com.apiclient.newsapiclient.DTOs.NewsApiDTO;

import java.util.ArrayList;

public class NewsApiDTOToListOfNewsObjectConverter {
    public static ListOfNewsObject convert(NewsApiDTO newsApiDTO) {
        var list = newsApiDTO.getArticles().stream().map(article ->{
            NewsObject newsObject = new NewsObject();
            newsObject.setAuthor(article.getAuthor());
            newsObject.setDescription(article.getDescription());
            newsObject.setUrlToImage(article.getUrlToImage());
            newsObject.setContent(article.getContent());
            newsObject.setUrl(article.getUrl());
            newsObject.setTitle(article.getTitle());
            newsObject.setPublishedAt(article.getPublishedAt());
            newsObject.setSourceName(article.getSource().getName());
            return newsObject;
        }).toList();
        ListOfNewsObject returnList = new ListOfNewsObject();
        returnList.setNewsObjectList(new ArrayList<>(list));
        return returnList;
    }
}
