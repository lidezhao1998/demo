package com.ruoyi.system.domain.gis;

import java.util.HashMap;

public class Codes {


    private static final HashMap<String, Double> map;

    static {
        map = new HashMap<String, Double>();
        map.put("10301", 4.78);
        map.put("10302", 8.20);
        map.put("10303", 9.37);
        map.put("10304", 18.27);
    }

    public static double conversionCoefficient(String key) {
        return map.get(key);
    }
}  