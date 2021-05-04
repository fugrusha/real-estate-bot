package com.fugro.realestatebot.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fugro.realestatebot.client.EasyBaseClient;
import com.fugro.realestatebot.client.EasyBaseClientImpl;
import com.fugro.realestatebot.client.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EasyBaseClientTest {

    @Autowired
    private ObjectMapper objectMapper;

    private final EasyBaseClient client = new EasyBaseClientImpl(
            "https://api.easybase.com.ua/v1/rieltors/11405/realties/",
            "https://api.easybase.com.ua/v1/reference/cities/search");

    @Test
    public void shouldReturnFirstPage() {
        // given
        QueryArgs args = QueryArgs.builder().page(1).type("apts").build();

        // when
        PageResultDTO actualResult = client.getPageResults(args);

        // then
        Assertions.assertNotNull(actualResult);
        assertEquals(2, actualResult.getNext());
        assertTrue(actualResult.getResults().size() > 0);
    }

    @Test
    public void shouldReturnPageOfApartments() throws JsonProcessingException {
        // given
        QueryArgs args = QueryArgs.builder().page(1).type("apts").build();

        // when
        String actualResult = client.getPageResultsAString(args);

        // then
        PageResultDTO resultDTO = objectMapper.readValue(actualResult, PageResultDTO.class);
        Assertions.assertNotNull(resultDTO);
    }

    @Test
    public void shouldReturnListOfDistricts() {
        List<DistrictDTO> actualResult = client.getAllDistricts();

        Assertions.assertNotNull(actualResult);
        assertTrue(actualResult.size() > 0);
        assertTrue(actualResult.stream().anyMatch(s -> s.getName().equals("Индустриальный район")));
    }

    @Test
    public void shouldReturnListOfStreets() {
        List<StreetDTO> actualResult = client.getAllStreets();

        Assertions.assertNotNull(actualResult);
        assertTrue(actualResult.size() > 0);
        assertTrue(actualResult.stream().anyMatch(s -> s.getName().equals("8 Марта ул.")));
    }

    @Test
    public void shouldReturnListOfMicroDistricts() {
        List<MicrodistrictDTO> actualResult = client.getAllMicroDistricts();

        Assertions.assertNotNull(actualResult);
        assertTrue(actualResult.size() > 0);
        assertTrue(actualResult.stream().anyMatch(s -> s.getName().equals("Амур")));
    }

    @Test
    public void testDeserializeDateTime() throws Exception {
        String json = "{ \"date\": \"04/05/2021 11:48\" }";
        JsonType instance = objectMapper.readValue(json, JsonType.class);

        LocalDateTime expected = LocalDateTime.parse("04/05/2021 11:48",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        assertEquals(expected, instance.getDate());
    }

    private static class JsonType {
        private LocalDateTime date;

        public LocalDateTime getDate() {
            return date;
        }

        public void setDate(LocalDateTime date) {
            this.date = date;
        }
    }

}
