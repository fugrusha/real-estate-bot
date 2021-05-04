package com.fugro.realestatebot.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "advert")
public class Advert {

    @Id
    private String id;

    private String externalId;

    private BusinessType businessType;

    private String city;

    private String district;

    private String microDistrict;

    private String street;

    private Integer roomsCount;

    private Integer floorNumber;

    private Integer totalFloorNumber;

    private AptType aptType;

    private BigDecimal price;

    private String currency;

    private BigDecimal squareArea;

    private BigDecimal squareCommon;

    private BigDecimal squareKitchen;

    private BigDecimal squareLiving;

    private String description;

    private RepairType repairType;

    private MaterialType materialType;

    private String houseNumber;

    private String houseLetter;

    private String houseHull;

    private String title;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private SourceType sourceType;

    private Boolean isAngled;

    private String water;

    private String gas;

    private String sewers;

    private String heating;

    private List<String> images;
}
