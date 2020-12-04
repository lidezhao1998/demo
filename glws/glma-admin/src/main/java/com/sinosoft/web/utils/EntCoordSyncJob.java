package com.sinosoft.web.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author sunlei
 * @description 百度地图
 * @date 2020/10/28/11:38
 */
public class EntCoordSyncJob {
    static String AK = "t2sytIzKzZ5UQAGqMfchUmYSPjwqGCUV";

    /**
     * @param addr * 查询的地址 * @return * @throws IOException
     */
    public Object[] getCoordinate(String addr) throws IOException {
        String lng = null;
        //经度

        String lat = null;
        //纬度

        String address = null;
        try {
            address = java.net.URLEncoder.encode(addr, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        String url = String.format("http://api.map.baidu.com/geocoder?address=%s&output=json&key=%s", address, AK);
        URL myURL = null;
        URLConnection httpsConn = null;
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader insr = null;
        BufferedReader br = null;
        try {
            httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
            if (httpsConn != null) {
                insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                String data = null;
                int count = 1;
                while ((data = br.readLine()) != null) {
                    if (count == 5) {
                        lng = (String) data.subSequence(data.indexOf(":") + 1, data.indexOf(","));
                        //经度

                        count++;
                    } else if (count == 6) {
                        lat = data.substring(data.indexOf(":") + 1);
                        //纬度

                        count++;
                    } else {
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (insr != null) {
                insr.close();
            }
            if (br != null) {
                br.close();
            }
        }
        return new Object[]{lng, lat};
    }

    public static void main(String[] args) throws IOException {
        EntCoordSyncJob getLatAndLngByBaidu = new EntCoordSyncJob();
        Object[] o = getLatAndLngByBaidu.getCoordinate("山东省烟台市机场路2号东方电子");
        // 纬度 } }
    }
}
