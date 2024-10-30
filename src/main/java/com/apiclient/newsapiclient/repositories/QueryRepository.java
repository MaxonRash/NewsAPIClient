package com.apiclient.newsapiclient.repositories;

import com.apiclient.newsapiclient.entities.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends MongoRepository<Query, String>/*, QueryDslPredicateExecutor<Query>*/ {
    public Query findByName(String name);
}
