package com.fugro.realestatebot.client.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Builder
@Getter
public class QueryArgs {

    private final String type;

    private final Integer page;

    public Map<String, Object> populateQueries() {
        Map<String, Object> queries = new HashMap<>();

        if (Objects.nonNull(page)) {
            queries.put("page", page);
        }

        if (Objects.nonNull(type)) {
            queries.put("type", type);
        }

        return queries;
    }
}
