package com.ruoyi.common.gis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.aspectj.apache.bcel.generic.RET;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;


/**
 * @ClassName: EntCoordSyncJob
 * @Description: 根据地区名字, 获取坐标
 */
public class EntCoordSyncJob {
    static String AK = "t2sytIzKzZ5UQAGqMfchUmYSPjwqGCUV"; // 百度地图密钥

    // 调用百度地图API根据地址，获取坐标
    public static String getCoordinate(String address) {
        if (address != null && !"".equals(address)) {
            address = address.replaceAll("\\s*", "").replace("#", "栋");
            String url = "http://api.map.baidu.com/geocoding/v3/?address=" + address + "&output=json&ak=" + AK + "&callback=showLocation";
            String json = loadJSON(url);
            if (json != null && !"".equals(json)) {
                String replaceJson = json.replace("showLocation&&showLocation(", "");
                JSONObject obj = JSONArray.parseObject(replaceJson.substring(0, replaceJson.length() - 1));
                if ("0".equals(obj.getString("status"))) {
                    double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng"); // 经度
                    double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat"); // 纬度
                    DecimalFormat df = new DecimalFormat("#.######");
                    return df.format(lng) + "," + df.format(lat);
                }
            }
        }
        return null;
    }

    // 调用百度地图API根据地址，获取点点信息
    public static String reverseGeocode(String lng, String lat) {
        String location = lat + "," + lng;
        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=" + AK + "&output=json&coordtype=wgs84ll&location=" + location;
        String res = doGet(url);
        return JSON.parseObject(res).getJSONObject("result").getString("formatted_address");
//        String city = jsonObject.getString("city");
//        String district = jsonObject.getString("district");
//        return city+","+district;
    }

    // 调用百度地图API根据地址，获取点点信息
    public static String addGeocode(String lng, String lat) {
        String location = lat + "," + lng;
        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=" + AK + "&output=json&coordtype=wgs84ll&location=" + location;
        String res = doGet(url);
        JSONObject jsonObject = JSON.parseObject(res).getJSONObject("result").getJSONObject("addressComponent");
        String city = jsonObject.getString("city");
        String district = jsonObject.getString("district");
        return city + "," + district;
    }

    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }

    public static String doGet(String url) {
        //创建一个Http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //创建一个get请求
        HttpGet httpGet = new HttpGet(url);
        //响应模型
        CloseableHttpResponse response = null;
        try {
            //由客户端发送get请求
            response = httpClient.execute(httpGet);
            //从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                return EntityUtils.toString(responseEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static String provinces(String address) {
        if (address.contains("省")) {
            String replace = address.replace("省", "省,").replace("市", "市,");
            if (replace.contains("区")) {
                String[] split = replace.split("区");
                return split[0] + "区";
            }
            String[] split = replace.split("市,");
            return split[0] + "市";

        }
        if (address.contains("自治区")) {
            String replace = address.replace("自治区", "%s").replace("市", "市,");
            if (replace.contains("区")) {
                String[] split = replace.split("区");
                return String.format(split[0], "自治区,") + "区";
            }
            String[] split = replace.split("市,");
            return String.format(split[0], "自治区,") + "市";

        }
        if (address.contains("市")) {
            String replace = address.replace("市", "市,");
            if (replace.contains("区")) {
                String[] split = replace.split("区");
                return split[0] + "区";
            }
            String[] split = replace.split("市,");
            return split[0] + "市";
        }
        return null;
    }

    public static void main(String[] args) {

        System.out.println(provinces("内蒙古自治区哈哈市孙磊区"));

    }

}