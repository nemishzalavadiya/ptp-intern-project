package com.pirimidtech.ptp.controller.stock;

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
class StockDetailControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    UrlHelper urlHelper = new UrlHelper();

    @Test
    void getAllStockDetails() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                urlHelper.createURLWithPort("/stocks/details?page=0&size=2", port),
                HttpMethod.GET, entity, String.class);
        String expected = "{\"content\":[{\"id\":\"2ffedff5-70c5-45cd-9c35-b36c25d77362\",\"yearFounded\":\"2019-01-21T05:47:08.644\",\"managingDirector\":\"dir\",\"assetDetail\":{\"id\":\"51381618-1bc9-4c19-aab9-44994433b18c\",\"name\":\"ptp\",\"logoUrl\":\"logo_url\",\"assetClass\":\"STOCK\",\"about\":\"about\",\"managingDirector\":\"devesh\",\"organization\":\"org\"}}],\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":0,\"pageSize\":2,\"pageNumber\":0,\"unpaged\":false,\"paged\":true},\"totalElements\":1,\"last\":true,\"totalPages\":1,\"size\":2,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"numberOfElements\":1,\"first\":true,\"empty\":false}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void getStockDetailsById() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                urlHelper.createURLWithPort("/stocks/details/2ffedff5-70c5-45cd-9c35-b36c25d77362", port),
                HttpMethod.GET, entity, String.class);
        String expected = "{\"id\":\"2ffedff5-70c5-45cd-9c35-b36c25d77362\",\"yearFounded\":\"2019-01-21T05:47:08.644\",\"managingDirector\":\"dir\",\"assetDetail\":{\"id\":\"51381618-1bc9-4c19-aab9-44994433b18c\",\"name\":\"ptp\",\"logoUrl\":\"logo_url\",\"assetClass\":\"STOCK\",\"about\":\"about\",\"managingDirector\":\"devesh\",\"organization\":\"org\"}}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void getStockStatsById() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                urlHelper.createURLWithPort("/stocks/stats/2ffedff5-70c5-45cd-9c35-b36c25d77362", port),
                HttpMethod.GET, entity, String.class);
        String expected = "{\"id\":\"2ffedff5-70c5-45cd-9c35-b36c25d77362\",\"numberOfStackHolders\":5,\"pbRatio\":0.5,\"peRatio\":1.5,\"industryPE\":4.4,\"divYield\":2.2,\"bookValue\":1.1,\"marketCap\":1000.1,\"returnOnEquity\":50.4,\"earningPerShareTTM\":3.3,\"stockDetail\":{\"id\":\"2ffedff5-70c5-45cd-9c35-b36c25d77362\",\"yearFounded\":\"2019-01-21T05:47:08.644\",\"managingDirector\":\"dir\",\"assetDetail\":{\"id\":\"51381618-1bc9-4c19-aab9-44994433b18c\",\"name\":\"ptp\",\"logoUrl\":\"logo_url\",\"assetClass\":\"STOCK\",\"about\":\"about\",\"managingDirector\":\"devesh\",\"organization\":\"org\"}}}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
}