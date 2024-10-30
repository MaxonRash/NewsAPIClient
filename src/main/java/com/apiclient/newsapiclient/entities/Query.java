package com.apiclient.newsapiclient.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Query {
    private String _id;
    private String _class;
    private String name;
}
