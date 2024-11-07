package com.apiclient.newsapiclient.entities;

import lombok.*;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Query {
    private String _id;
    private String _class;
    private String name;

    public Query(String name) {
        this.name = name;
    }
}
