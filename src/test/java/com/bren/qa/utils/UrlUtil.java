package com.bren.qa.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UrlUtil {
    public static int getAPort(List portsList) {
        Random r = new Random();
        int num = (int) portsList.get(r.nextInt(portsList.size()));
        portsList.remove(Integer.valueOf(num));
        return num;
    }
    public static List<Integer> getPortsList() {
        List<Integer> portsList = new ArrayList<>();
        for (int i = 8200; i < 8400; i++) {
            portsList.add(i);
        }
        return portsList;
    }
}
