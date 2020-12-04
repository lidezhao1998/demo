package com.ruoyi.framework.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sunlei
 * @description  shp 经纬度分割
 * @date 2020/09/27/15:36
 */
public class SubGeometryUtil {
     public static List<String> SubGeometry(String geometry){
             geometry = geometry.replaceAll("MULTIPOLYGON \\(", "");
         String geometrySubstring = geometry.substring(0, geometry.length() - 3);
         String[] split = geometrySubstring.split("\\)\\),");
         return  Arrays.asList(split);
    }



}
