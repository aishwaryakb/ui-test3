package com.bren.qa.utils;

import java.util.Random;

public class UrlUtil {
    public static String getUrl() {
        Random r = new Random();
        String[] ports={"4723", "4724", "4725", "4726", "4727"}; 
        String baseUrl = "http://127.0.0.1:4723/wd/hub";
        String url = baseUrl.substring(0, baseUrl.lastIndexOf(":"))+":"+ports[r.nextInt(ports.length)]+"/wd/hub";
        return url;
    }
}
