package com.pirimidtech.ptp.controller;

public class UrlHelper {
    public String createURLWithPort(String uri, int port) {
        return "http://localhost:" + port + uri;
    }
}
