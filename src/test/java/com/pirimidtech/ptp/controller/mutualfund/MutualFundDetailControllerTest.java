package com.pirimidtech.ptp.controller.mutualfund;

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

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    void getAllMutualFundDetails() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/mutualfund/details"),
                HttpMethod.GET, entity, String.class);
        String expected = "[{\"id\":\"c9f27cd5-98e8-4277-b086-4595783f4761\",\"launchDate\":\"2019-01-21T05:47:08.644\",\"fundManager\":\"director\",\"companyDetail\":{\"id\":\"a4f5c114-02c0-4340-bca6-7dbee2702e25\",\"name\":\"Axis Mutual Fund\",\"logoUrl\":\"logo_url\",\"assetClass\":\"MUTUAL_FUND\",\"about\":\"Axis MF about\",\"managingDirector\":\"darshan\",\"organization\":\"Axis bank\"}}]";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    void getMutualFundDetailsById() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/mutualfund/details/c9f27cd5-98e8-4277-b086-4595783f4761"),
                HttpMethod.GET, entity, String.class);
        String expected = "{\"id\":\"c9f27cd5-98e8-4277-b086-4595783f4761\",\"launchDate\":\"2019-01-21T05:47:08.644\",\"fundManager\":\"director\",\"companyDetail\":{\"id\":\"a4f5c114-02c0-4340-bca6-7dbee2702e25\",\"name\":\"Axis Mutual Fund\",\"logoUrl\":\"logo_url\",\"assetClass\":\"MUTUAL_FUND\",\"about\":\"Axis MF about\",\"managingDirector\":\"darshan\",\"organization\":\"Axis bank\"}}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void getMutualFundStatsById() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/mutualfund/stats/c9f27cd5-98e8-4277-b086-4595783f4761"),
                HttpMethod.GET, entity, String.class);
        String expected = "{\"id\":\"c9f27cd5-98e8-4277-b086-4595783f4761\",\"risk\":\"high\",\"minSIP\":65.3,\"sipAllowed\":true,\"expenseRatio\":2.3,\"nav\":12.39,\"fundStarted\":\"2019-01-22T05:47:08.644\",\"fundSize\":1000.3,\"mutualFundDetail\":{\"id\":\"c9f27cd5-98e8-4277-b086-4595783f4761\",\"launchDate\":\"2019-01-21T05:47:08.644\",\"fundManager\":\"director\",\"companyDetail\":{\"id\":\"a4f5c114-02c0-4340-bca6-7dbee2702e25\",\"name\":\"Axis Mutual Fund\",\"logoUrl\":\"logo_url\",\"assetClass\":\"MUTUAL_FUND\",\"about\":\"Axis MF about\",\"managingDirector\":\"darshan\",\"organization\":\"Axis bank\"}}}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
}