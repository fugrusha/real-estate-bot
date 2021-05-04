package com.fugro.realestatebot.client;

import com.fugro.realestatebot.client.dto.*;

import java.util.List;

public interface EasyBaseClient {

    PageResultDTO getPageResults(QueryArgs args);

    String getPageResultsAString(QueryArgs args);

    List<DistrictDTO> getAllDistricts();

    List<StreetDTO> getAllStreets();

    List<MicrodistrictDTO> getAllMicroDistricts();
}
