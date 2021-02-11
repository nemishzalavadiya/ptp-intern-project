package com.pirimidtech.ptp.controller.mutualfund;

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
class MutualFundDetailControllerTest {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    UrlHelper urlHelper = new UrlHelper();
    @LocalServerPort
    private int port;

    @Test
    void getAllMutualFundDetails() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                urlHelper.createURLWithPort("/mutualfunds/details?page=0&size=2", port),
                HttpMethod.GET, entity, String.class);
        String expected = "{\"content\":[{\"id\":\"9f1be004-4872-4343-be24-627fb7dc0d92\",\"launchDate\":\"1999-09-09T00:00:00\",\"fundManager\":\"manager\",\"assetDetail\":{\"id\":\"a70c4714-e1e5-40d4-b846-8fc6b943ecb5\",\"name\":\"ptp2\",\"logoUrl\":\"logo_url\",\"assetClass\":\"MUTUAL_FUND\",\"about\":\"about\",\"managingDirector\":\"darshan\",\"organization\":\"org\"}}],\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":0,\"pageSize\":2,\"pageNumber\":0,\"unpaged\":false,\"paged\":true},\"totalElements\":1,\"last\":true,\"totalPages\":1,\"size\":2,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"numberOfElements\":1,\"first\":true,\"empty\":false}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void getMutualFundDetailsById() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                urlHelper.createURLWithPort("/mutualfunds/details/9f1be004-4872-4343-be24-627fb7dc0d92", port),
                HttpMethod.GET, entity, String.class);
        String expected = "{\"id\":\"9f1be004-4872-4343-be24-627fb7dc0d92\",\"launchDate\":\"1999-09-09T00:00:00\",\"fundManager\":\"manager\",\"assetDetail\":{\"id\":\"a70c4714-e1e5-40d4-b846-8fc6b943ecb5\",\"name\":\"ptp2\",\"logoUrl\":\"logo_url\",\"assetClass\":\"MUTUAL_FUND\",\"about\":\"about\",\"managingDirector\":\"darshan\",\"organization\":\"org\"}}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void getMutualFundStatsById() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                urlHelper.createURLWithPort("/mutualfunds/stats/9f1be004-4872-4343-be24-627fb7dc0d92", port),
                HttpMethod.GET, entity, String.class);
        String expected = "{\"id\":\"9f1be004-4872-4343-be24-627fb7dc0d92\",\"risk\":\"high\",\"minSIP\":4.4,\"sipAllowed\":true,\"expenseRatio\":1.1,\"nav\":1000.1,\"fundStarted\":\"2019-01-21T05:47:08.644\",\"fundSize\":2.2,\"mutualFundDetail\":{\"id\":\"9f1be004-4872-4343-be24-627fb7dc0d92\",\"launchDate\":\"1999-09-09T00:00:00\",\"fundManager\":\"manager\",\"assetDetail\":{\"id\":\"a70c4714-e1e5-40d4-b846-8fc6b943ecb5\",\"name\":\"ptp2\",\"logoUrl\":\"logo_url\",\"assetClass\":\"MUTUAL_FUND\",\"about\":\"about\",\"managingDirector\":\"darshan\",\"organization\":\"org\"}}}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
}