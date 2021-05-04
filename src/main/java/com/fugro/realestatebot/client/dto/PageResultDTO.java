package com.fugro.realestatebot.client.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResultDTO {

    private Integer count;

    private Integer next;

    private Integer previous;

    private List<AdvertImportDTO> results;
}
