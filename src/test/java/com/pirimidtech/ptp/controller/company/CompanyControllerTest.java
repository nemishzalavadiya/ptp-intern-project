package com.pirimidtech.ptp.controller.company;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompanyControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();


    @Test
    void getCompanyDetail() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/company/51381618-1bc9-4c19-aab9-44994433b18c"),
                HttpMethod.GET, entity, String.class);
        String expected = "{\"id\":\"51381618-1bc9-4c19-aab9-44994433b18c\",\"name\":\"ptp\",\"logoUrl\":\"logo_url\",\"assetClass\":\"STOCK\",\"about\":\"about\",\"managingDirector\":\"devesh\",\"organization\":\"org\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void companyDetailList() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/company"),
                HttpMethod.GET, entity, String.class);
        String expected = "[{\"id\":\"51381618-1bc9-4c19-aab9-44994433b18c\",\"name\":\"ptp\",\"logoUrl\":\"logo_url\",\"assetClass\":\"STOCK\",\"about\":\"about\",\"managingDirector\":\"devesh\",\"organization\":\"org\"},{\"id\":\"a4f5c114-02c0-4340-bca6-7dbee2702e25\",\"name\":\"Axis Mutual Fund\",\"logoUrl\":\"logo_url\",\"assetClass\":\"MUTUAL_FUND\",\"about\":\"Axis MF about\",\"managingDirector\":\"darshan\",\"organization\":\"Axis bank\"}]";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;

    }

    @Test
    void searchCompanyList() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/search/Axis"),
                HttpMethod.GET, entity, String.class);
        String expected = "[{\"id\":\"a4f5c114-02c0-4340-bca6-7dbee2702e25\",\"name\":\"Axis Mutual Fund\",\"logoUrl\":\"logo_url\",\"assetClass\":\"MUTUAL_FUND\",\"about\":\"Axis MF about\",\"managingDirector\":\"darshan\",\"organization\":\"Axis bank\"}]";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
}