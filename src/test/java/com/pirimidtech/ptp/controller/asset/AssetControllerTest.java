package com.pirimidtech.ptp.controller.asset;

import com.pirimidtech.ptp.controller.UrlHelper;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AssetControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    UrlHelper urlHelper;

    @Test
    void getAssetDetail() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                urlHelper.createURLWithPort("/assets/51381618-1bc9-4c19-aab9-44994433b18c", port),
                HttpMethod.GET, entity, String.class);
        String expected = "{\"id\":\"51381618-1bc9-4c19-aab9-44994433b18c\",\"name\":\"ptp\",\"logoUrl\":\"logo_url\",\"assetClass\":\"STOCK\",\"about\":\"about\",\"managingDirector\":\"devesh\",\"organization\":\"org\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void assetDetailList() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                urlHelper.createURLWithPort("/assets?page=0&size=2", port),
                HttpMethod.GET, entity, String.class);
        String expected = "[{\"id\":\"51381618-1bc9-4c19-aab9-44994433b18c\",\"name\":\"ptp\",\"logoUrl\":\"logo_url\",\"assetClass\":\"STOCK\",\"about\":\"about\",\"managingDirector\":\"devesh\",\"organization\":\"org\"},{\"id\":\"a4f5c114-02c0-4340-bca6-7dbee2702e25\",\"name\":\"Axis Mutual Fund\",\"logoUrl\":\"logo_url\",\"assetClass\":\"MUTUAL_FUND\",\"about\":\"Axis MF about\",\"managingDirector\":\"darshan\",\"organization\":\"Axis bank\"}]";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void searchAssetList() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                urlHelper.createURLWithPort("/search/Axis?page=0&size=1", port),
                HttpMethod.GET, entity, String.class);
        String expected = "[{\"id\":\"a4f5c114-02c0-4340-bca6-7dbee2702e25\",\"name\":\"Axis Mutual Fund\",\"logoUrl\":\"logo_url\",\"assetClass\":\"MUTUAL_FUND\",\"about\":\"Axis MF about\",\"managingDirector\":\"darshan\",\"organization\":\"Axis bank\"}]";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
}