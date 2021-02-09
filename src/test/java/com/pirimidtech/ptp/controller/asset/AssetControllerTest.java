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
    public int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    UrlHelper urlHelper = new UrlHelper();

    @Test
    void getAssetDetail() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                urlHelper.createURLWithPort("/assets/51381618-1bc9-4c19-aab9-44994433b18c", port),
                HttpMethod.GET, entity, String.class);
        String expected = "{\"id\":\"51381618-1bc9-4c19-aab9-44994433b18c\",\"name\":\"ptp\",\"logoUrl\":\"logo_url\",\"assetClass\":\"STOCK\",\"about\":\"about\",\"managingDirector\":\"devesh\",\"organization\":\"org\"}";
        System.out.println(response.getBody());
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void assetDetailList() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                urlHelper.createURLWithPort("/assets?page=0&size=2", port),
                HttpMethod.GET, entity, String.class);
        String expected = "{\"content\":[{\"id\":\"51381618-1bc9-4c19-aab9-44994433b18c\",\"name\":\"ptp\",\"logoUrl\":\"logo_url\",\"assetClass\":\"STOCK\",\"about\":\"about\",\"managingDirector\":\"devesh\",\"organization\":\"org\"},{\"id\":\"a70c4714-e1e5-40d4-b846-8fc6b943ecb5\",\"name\":\"ptp2\",\"logoUrl\":\"logo_url\",\"assetClass\":\"MUTUAL_FUND\",\"about\":\"about\",\"managingDirector\":\"darshan\",\"organization\":\"org\"}],\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":0,\"pageNumber\":0,\"pageSize\":2,\"paged\":true,\"unpaged\":false},\"totalPages\":1,\"totalElements\":2,\"last\":true,\"size\":2,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"numberOfElements\":2,\"first\":true,\"empty\":false}\n";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void searchAssetList() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                urlHelper.createURLWithPort("/search/ptp?page=0&size=1", port),
                HttpMethod.GET, entity, String.class);
        String expected = "{\"content\":[{\"id\":\"51381618-1bc9-4c19-aab9-44994433b18c\",\"name\":\"ptp\",\"logoUrl\":\"logo_url\",\"assetClass\":\"STOCK\",\"about\":\"about\",\"managingDirector\":\"devesh\",\"organization\":\"org\"}],\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":0,\"pageSize\":1,\"pageNumber\":0,\"unpaged\":false,\"paged\":true},\"totalElements\":2,\"last\":false,\"totalPages\":2,\"size\":1,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"numberOfElements\":1,\"first\":true,\"empty\":false}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
}