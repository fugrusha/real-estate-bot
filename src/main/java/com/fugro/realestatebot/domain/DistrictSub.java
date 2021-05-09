package com.fugro.realestatebot.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "district_sub")
public class DistrictSub {

    @Id
    private String id;

    private String chatId;

    private Integer districtId;

    private String districtName;

    private Set<Integer> sentAdIds = new HashSet<>();
}
