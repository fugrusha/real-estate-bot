package com.fugro.realestatebot.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AdvertImportDTO {

    @JsonProperty("id")
    private String externalId;

    @JsonProperty("business_type")
    private String businessType;

    private String city;

    private DistrictDTO district;

    @JsonProperty("microdistrict")
    private MicrodistrictDTO microDistrict;

    private StreetDTO street;

    @JsonProperty("rooms")
    private Integer roomsCount;

    @JsonProperty("floor")
    private Integer floorNumber;

    @JsonProperty("floors")
    private Integer totalFloorNumber;

    @JsonProperty("type")
    private String aptType;

    private BigDecimal price;

    private String currency;

    @JsonProperty("square_area")
    private BigDecimal squareArea;

    @JsonProperty("square_common")
    private BigDecimal squareCommon;

    @JsonProperty("square_kitchen")
    private BigDecimal squareKitchen;

    @JsonProperty("square_living")
    private BigDecimal squareLiving;

    @JsonProperty("text")
    private String description;

    @JsonProperty("repair")
    private String repairType;

    @JsonProperty("material_type")
    private String materialType;

    @JsonProperty("house_number")
    private String houseNumber;

    @JsonProperty("house_letter")
    private String houseLetter;

    @JsonProperty("house_hull")
    private String houseHull;

    private String title;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @JsonProperty("source")
    private String sourceType;

    @JsonProperty("is_angled")
    private Boolean isAngled;

    private String water;

    private String gas;

    private String sewers;

    private String heating;

    private List<ImageDTO> images;
}
