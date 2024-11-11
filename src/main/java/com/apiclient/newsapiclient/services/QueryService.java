package com.apiclient.newsapiclient.services;

import com.apiclient.newsapiclient.entities.Query;
import com.apiclient.newsapiclient.repositories.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {
    private final QueryRepository queryRepository;

    @Autowired
    public QueryService(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    public Query save(Query query) {
        return queryRepository.save(query);
    }

    public List<Query> findAll() {
        return queryRepository.findAll();
    }

    public Query findOne(Query query) {
        return queryRepository.findByName(query.getName());
    }

    public void delete(Query query) {
        queryRepository.delete(query);
    }

    public Query deleteQueryByNameIgnoreCase(String name) {
        return queryRepository.deleteQueryByNameIgnoreCase(name);
    }
}
