package com.sinosoft.extinterface.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.gis.EntCoordSyncJob;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.SubGeometryUtil;
import com.sinosoft.analyze.mapper.ProductionAnalyzeMapper;
import com.sinosoft.analyze.service.ProductionAnalyzeService;
import com.sinosoft.extinterface.domain.LandSurvey;
import com.sinosoft.extinterface.domain.SpatialPlanning;
import com.sinosoft.extinterface.mapper.LandSurveyMapper;
import com.sinosoft.extinterface.service.LandSurveyService;
import com.sinosoft.integration.domain.RemoteSensing;
import com.vividsolutions.jts.geom.Geometry;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.Charset;
import java.util.*;

/**
 * author  lhf
 * date  2020/8/19 17:57
 * version 1.0
 */
@Service
public class LandSurveyServiceImpl implements LandSurveyService {
     static  Map<String,Integer> map=new HashMap<String, Integer>();
    @Autowired
    LandSurveyMapper landSurveyMapper;
    @Autowired
    ProductionAnalyzeMapper productionAnalyzeMapper;
    @Override
    public LandSurvey selectLandSurvey(Long parentId) {
        return landSurveyMapper.selectLandSurvey(parentId);
    }

    @Override
    public List<LandSurvey> selectLandSurveyList(LandSurvey landSurvey) {
        return landSurveyMapper.selectLandSurveyList(landSurvey);
    }

    @Override
    public int insertShpFile(String filePath,String color) {
        List<LandSurvey> list = readShapeFile(filePath);
//        postgresql对于sql语句的参数数量是有限制的，最大为32767 需要分步插入数据
        int numberBatch = 1000; //每一次插入的最大行数
        double number = list.size() * 1.0 / numberBatch;
        int n = ((Double)Math.ceil(number)).intValue(); //向上取整
        for(int i = 0; i < n; i++){
            int end = numberBatch * (i + 1);
            if(end > list.size()){
                end = list.size(); //如果end不能超过最大索引值
            }
            try {
                landSurveyMapper.insertlandSurvey(list.subList(numberBatch * i , end)); //插入数据库
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 1;
    }
    /**
     *功能描述  读shp文件
     * @author sunlei
     * @date 2020/9/23
     * @param
     * @return [filePath]
     */
    public static ArrayList<LandSurvey> readShapeFile(String filePath) {
        ArrayList<LandSurvey> modelList = new ArrayList<LandSurvey>();
        File folder = new File(filePath);
        if (!folder.isDirectory()) {
            if (folder.toString().endsWith(".shp")) {
                try {
                    modelList = getShapeFile(folder);
                    return modelList;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("选择的文件后缀名不是.shp");
            }
        } else {
            File[] files = folder.listFiles();
            if (!(files.length > 0)) {
                System.out.println("目录文件为空");
                return modelList;
            }

            for (File file : files) {
                if (!file.toString().endsWith(".shp")) {
                    continue;
                }
                try {
                    ArrayList<LandSurvey> models = getShapeFile(file);
                    modelList.addAll(models);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return modelList;
    }
    //读shp属性
    private static ArrayList<LandSurvey> getShapeFile(File file) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", file.toURI().toURL());
        ArrayList<LandSurvey> models = new ArrayList<LandSurvey>();
        DataStore dataStore = DataStoreFinder.getDataStore(map);
        //字符转码，防止中文乱码
        ((ShapefileDataStore) dataStore).setCharset(Charset.forName("GBK"));
        String typeName = dataStore.getTypeNames()[0];
        FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
        FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures();
        FeatureIterator<SimpleFeature> features = collection.features();
        while (features.hasNext()) {
            SimpleFeature feature = features.next();
            LandSurvey model = new LandSurvey();
            Iterator<? extends Property> iterator = feature.getValue().iterator();
            //经纬度集合
            List<String> list=null;
            //shp颜色
            String color="";
            while (iterator.hasNext()) {
                Property property = iterator.next();
                //property数据与实体类对
                if (property.getName().toString().equals("the_geom")) {
                    //集合经纬度
                    list  = SubGeometryUtil.SubGeometry(property.getValue().toString());
                }

                if (property.getName().toString().equals("目代码")) {

                    model.setItemCode(Long.parseLong(property.getValue().toString()));
                }
                if (property.getName().toString().equals("目")) {

                    model.setItem(property.getValue().toString());
                }
                if (property.getName().toString().equals("科代码")) {
                    model.setSectionCode(Long.parseLong(property.getValue().toString()));
                }

                if (property.getName().toString().equals("科")) {
                    model.setSection(property.getValue().toString());
                }

                if (property.getName().toString().equals("属代码")) {

                    model.setGenericCode(Long.parseLong(property.getValue().toString()));
                }
                if (property.getName().toString().equals("属")) {

                    model.setGeneric(property.getValue().toString());
                }
                if (property.getName().toString().equals("丛代码")) {

                    model.setBundleCode(Long.parseLong(property.getValue().toString()));
                }
                if (property.getName().toString().equals("丛")) {

                    model.setBundle(property.getValue().toString());
                }
                if (property.getName().toString().equals("color")) {
                    if(property != null ||property.getValue()!=null){
//                        color=property.getValue().toString();

                    }


                }

            }
            for (String s : list) {
                model.setGeomData("POLYGON"+s+"))");
                model.setColor(color);
                models.add(model);
            }
        }
        features.close();
        return models;
    }

    @Override
    public int updateunexamine(Long id) {
        return landSurveyMapper.unExamineSpatialPlanning(id);
    }

    @Override
    public int examine(Long id) {
        return landSurveyMapper.examineSpatialPlanning(id);
    }

    @Override
    public int deleteShpFileById(Long id) {
        return landSurveyMapper.defFalgSpatialPlanning(id);
    }

    @Override
    public int addFields(long id, String centroid) {
        int count=0;
        LandSurvey landSurvey = new LandSurvey();
        if (map==null||map.size()==0){
            map.put("count",1);
        }
        count = map.get("count");
        int i = count % 2;
        if (i==0){
            landSurvey.setOwnershipProperty("国家");
            landSurvey.setGrasslandType("天然牧草地");
        }else {
            landSurvey.setGrasslandType("人工牧草地");
            landSurvey.setOwnershipProperty("集体");
        }
        landSurvey.setLandType("草地");
        centroid=  centroid.replace("POINT(","").replace(")","").replace(" ",",");
        String[] split = centroid.split(",");
        JSONObject jsonObject = reverseGeocode(split[0], split[1]);
        String province = jsonObject.getString("province");
        String city = jsonObject.getString("city");
        String district = jsonObject.getString("district");

        landSurvey.setProvince(jsonObject.getString("province"));
        landSurvey.setCity(jsonObject.getString("city"));
        landSurvey.setCounty(jsonObject.getString("district"));
        landSurvey.setId(id);
        String provincesCode= productionAnalyzeMapper.getProvincesCode(province);
        String cityCode=productionAnalyzeMapper.getCityCode(provincesCode,city);
        if (cityCode==null){
            city= city.replaceAll("市", "地区");
            cityCode=productionAnalyzeMapper.getCityCode(provincesCode,city);
        }
        String countyCode=productionAnalyzeMapper.getCityCode(cityCode ,district);
        landSurvey.setCountyCode(countyCode);
        landSurvey.setProvinceCode(provincesCode);
        landSurvey.setCityCode(cityCode);
        int upCount=   landSurveyMapper.updateaddFields(landSurvey);

        return upCount;
    }

    @Override
    public int updateLandSurvey(LandSurvey landSurvey) {
        return landSurveyMapper.updateLandSurvey(landSurvey);
    }

    public static JSONObject reverseGeocode(String lon,String lat){
        String location=lat+","+lon;
        String url="http://api.map.baidu.com/reverse_geocoding/v3/?ak=t2sytIzKzZ5UQAGqMfchUmYSPjwqGCUV&output=json&coordtype=wgs84ll&location="+location;
        String res=EntCoordSyncJob.doGet(url);
        JSONObject jsonObject = JSON.parseObject(res).getJSONObject("result").getJSONObject("addressComponent");
//        String city = jsonObject.getString("city");
//        String district = jsonObject.getString("district");
        return jsonObject;
    }
    /**
     *功能描述 省市区名称查看编码
     * @author sunlei
     * @date 2020/11/4
     * @param
     * @return [provinces, city, county]
     */
    public  Map getProvincesCityCountyCode(String provinces,String  city,String county){
       String provincesCode= productionAnalyzeMapper.getProvincesCode(provinces);
       String cityCode=productionAnalyzeMapper.getCityCode(provincesCode,city);
       String countyCode=productionAnalyzeMapper.getCityCode(cityCode ,county);


        return  null;
    }
    public static void main(String[] args) {
        String location="41.2"+","+116.2;
        String url="http://api.map.baidu.com/reverse_geocoding/v3/?ak=t2sytIzKzZ5UQAGqMfchUmYSPjwqGCUV&output=json&coordtype=wgs84ll&location="+location;
        String res=EntCoordSyncJob.doGet(url);
        JSONObject jsonObject = JSON.parseObject(res).getJSONObject("result").getJSONObject("addressComponent");
//        String city = jsonObject.getString("city");
//        String district = jsonObject.getString("district");
    }

}
