package com.bren.qa.utils;

import java.util.Random;

public class UrlUtil {
    public static int getPort() {
        Random r = new Random();
        int[] ports={ 8200, 8201 }; 
        return ports[r.nextInt(ports.length)];
    }
}
