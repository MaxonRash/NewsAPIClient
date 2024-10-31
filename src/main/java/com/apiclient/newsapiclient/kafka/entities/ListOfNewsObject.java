package com.apiclient.newsapiclient.kafka.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListOfNewsObject {
    private List<NewsObject> newsObjectList;
}
