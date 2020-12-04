package com.ruoyi.common.utils;




import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author sunlei
 * @description 调用python函数
 * @date 2020/07/27/17:22
 */
@Service
public class JavaRunPython {
    public static String[] areaPython(String LonLatList, String ip) throws IOException, InterruptedException {
        Map<String, Object> map = new HashMap<>();


        //设置命令行传入参数 strings 参数1、是搭建python的运行脚本命令，参数2、是python的路径、参数3/4、是参数传给python运算的参数
        String[] strings = new String[]{"python", "/usr/local/python/shp.py", LonLatList, ip};
        Process pr = Runtime.getRuntime().exec(strings);
//        TODO:该方法只能传递字符串
//        Process praa = Runtime.getRuntime().exec("C:\\Users\\Desktop\\test3.py");
        String[] areaArray = new String[3];
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;

        for (int i = 0; i < 3; i++) {
            areaArray[i]=in.readLine();
        }
        in.close();
        pr.waitFor();
        return areaArray;
    }


    public static double[] get_coordinate(double latitude, double longitude, double altitude) {
        double B = Math.toRadians(latitude), L = Math.toRadians(longitude), H = altitude, x, y, z;
        double f = 1 / 298.257223563, r = 6378137;
        double b = r * (1 - f), e = Math.sqrt(2 * f - f * f);
        double N = r / Math.sqrt(1 - e * e * Math.sin(B) * Math.sin(B));
        x = (N + H) * Math.cos(B) * Math.cos(L);
        y = (N + H) * Math.cos(B) * Math.sin(L);
        z = (N * (1 - e * e) + H) * Math.sin(B);
        double[] data = {x, y, z};
        return data;
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        String ptsarrayl = "86.9774,49.0001;86.9803,48.9957;86.9844,48.9900;87.0044,48.9800;87.0126,48.9743;87.0209,48.9692;87.0303,48.9635;87.0414,48.9541;87.0317,48.9528;87.0228,48.9519;87.0162,48.9509;87.0100,48.9505;87.0070,48.9517;87.0006,48.9537;86.9946,48.9535;86.9886,48.9496;86.9716,48.9395;86.9588,48.9293;86.9500,48.9167;86.9437,48.9072;86.9381,48.9067;86.9355,48.9084;86.9363,48.9116;86.9379,48.9153;86.9400,48.9189;86.9415,48.9238;86.9419,48.9277;86.9410,48.9306;86.9387,48.9325;86.9357,48.9335;86.9318,48.9335;86.9286,48.9328;86.9244,48.9304;86.9184,48.9270;86.9131,48.9254;86.9089,48.9249;86.9054,48.9259;86.9033,48.9281;86.9194,48.9336;86.9349,48.9401;86.9453,48.9474;86.9591,48.9551;86.9686,48.9621;86.9743,48.9682;86.9762,48.9721;86.9771,48.9750;86.9775,48.9813;86.9775,48.9862;86.9767,48.9906;86.9738,48.9945;86.9706,48.9996;86.9774,49.0001";
        String ip = "47.94.105.131";
        //设置命令行传入参数
        String[] strings = new String[]{"python", "D:\\svn\\forestry\\dev\\untitled2\\shp.py", ptsarrayl, ip};
        Process pr = Runtime.getRuntime().exec(strings);
//        TODO:该方法只能传递字符串
//        Process praa = Runtime.getRuntime().exec("C:\\Users\\Desktop\\test3.py")

        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        String[] areaArray = new String[3];
        for (int i = 0; i < 3; i++) {
            areaArray[i]=in.readLine();
        }

        while ((line = in.readLine()) != null) {
//                line = decodeUnicode(line);
            System.out.println(line);
        }
        in.close();
        int i = pr.waitFor();
        System.out.println(i);
    }

}

