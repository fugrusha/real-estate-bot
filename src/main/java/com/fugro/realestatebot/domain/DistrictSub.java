package com.fugro.realestatebot.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "district_sub")
public class DistrictSub {

    @Id
    private String id;

    private String chatId;

    private Integer districtId;

    private String districtName;
}
