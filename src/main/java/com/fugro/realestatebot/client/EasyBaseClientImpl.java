package com.fugro.realestatebot.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fugro.realestatebot.client.dto.*;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;


@Component
public class EasyBaseClientImpl implements EasyBaseClient {

    private final String API_URL;

    private final String REFERENCE_API_URL;

    @Autowired
    private ObjectMapper objectMapper;

    public EasyBaseClientImpl(@Value("${easy.base.api.url}") String apiURL,
                              @Value("${easy.base.reference.api.url}") String referenceUrl) {
        this.API_URL = apiURL;
        this.REFERENCE_API_URL = referenceUrl;

        configureClient();
    }

    private void configureClient() {
        Unirest.config().setObjectMapper(new kong.unirest.ObjectMapper() {
            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return objectMapper.readValue(value, valueType);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public String writeValue(Object value) {
                try {
                    return objectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @PostConstruct
    private void configureClient() {
        Unirest.config().setObjectMapper(new kong.unirest.ObjectMapper() {
            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return objectMapper.readValue(value, valueType);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public String writeValue(Object value) {
                try {
                    return objectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public PageResultDTO getPageResults(QueryArgs args) {
        return Unirest.get(API_URL)
                .queryString(args.populateQueries())
                .asObject(PageResultDTO.class)
                .getBody();
    }


    public String getPageResultsAString(QueryArgs args) {
        return Unirest.get(API_URL)
                .queryString(args.populateQueries())
                .asString().getBody();
    }

    @Override
    public List<DistrictDTO> getAllDistricts() {
        DistrictDTO[] dtos = Unirest.get(REFERENCE_API_URL + "/district")
                .asObject(DistrictDTO[].class)
                .getBody();
        return Arrays.asList(dtos);
    }

    @Override
    public List<StreetDTO> getAllStreets() {
        StreetDTO[] dtos = Unirest.get(REFERENCE_API_URL + "/street")
                .asObject(StreetDTO[].class)
                .getBody();
        return Arrays.asList(dtos);
    }

    @Override
    public List<MicrodistrictDTO> getAllMicroDistricts() {
        MicrodistrictDTO[] dtos = Unirest.get(REFERENCE_API_URL + "/microdistrict")
                .asObject(MicrodistrictDTO[].class)
                .getBody();
        return Arrays.asList(dtos);
    }
}
