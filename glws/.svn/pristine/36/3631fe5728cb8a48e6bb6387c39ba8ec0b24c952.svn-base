package com.sinosoft.integration.service.impl;

import com.ruoyi.common.gis.ShapeModel;
import com.ruoyi.common.gis.ShapeReader;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.SubGeometryUtil;
import com.sinosoft.integration.domain.RemoteSensing;
import com.sinosoft.integration.mapper.RemoteSensingMapper;
import com.sinosoft.integration.service.RemoteSensingService;
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
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/28 16:23
 */
@Service
public class RemoteSensingServiceImpl implements RemoteSensingService {

    @Autowired
    RemoteSensingMapper remoteSensingMapper;

    @Override
    public RemoteSensing selectRemoteSensing(RemoteSensing remoteSensing) {
        return remoteSensingMapper.selectRemoteSensing(remoteSensing);
    }

    @Override
    public List<RemoteSensing> selectRemoteSensingList(RemoteSensing remoteSensing) {
        return remoteSensingMapper.selectRemoteSensingList(remoteSensing);
    }

    @Override
    public int updateRemoteSensing(RemoteSensing remoteSensing) {
       return remoteSensingMapper.updateRemoteSensing(remoteSensing);
    }

    @Override
    public int updateRemoteSensingByIds(String[] ids) {
        return remoteSensingMapper.updateRemoteSensingByIds(ids);
    }

    @Override
    public List<HashMap> gisShow(String url) {
        ArrayList<HashMap> hashMaps = new ArrayList<>();
        //getFileCoordinate(url.replace("/root/", "E:\\项目\\林业项目\\"),new ShapeReader(),hashMaps);
        getFileCoordinate(url,new ShapeReader(),hashMaps);
        return hashMaps;
    }

    @Override
    public RemoteSensing selectRemoteSensingById(Long id) {
        return remoteSensingMapper.selectRemoteSensingById(id);
    }

    @Override
    public int examineRemoteSensing(Long id) {
        return remoteSensingMapper.examineRemoteSensing(id);
    }

    @Override
    public int unexamineRemoteSensing(Long id) {
        return remoteSensingMapper.unexamineRemoteSensing(id);
    }

    @Override
    public int deleteRemoteSensing(Long id) {
        return remoteSensingMapper.defFalgRemoteSensing(id);
    }

    @Override
    public int saveEditRemoteSensing(RemoteSensing remoteSensing) {
        return remoteSensingMapper.saveEditRemoteSensing(remoteSensing);
    }

    @Override
    public int insertShpFile(String filePath, String color) {
        ArrayList<RemoteSensing> list = readShapeFile(filePath);
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
                remoteSensingMapper.insertShpFile(list.subList(numberBatch * i , end)); //插入数据库
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }



        return 1;
    }
    public static ArrayList<RemoteSensing> readShapeFile(String filePath) {
        ArrayList<RemoteSensing> modelList = new ArrayList<RemoteSensing>();
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
                    ArrayList<RemoteSensing> models = getShapeFile(file);
                    modelList.addAll(models);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return modelList;
    }

    private static ArrayList<RemoteSensing> getShapeFile(File file) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", file.toURI().toURL());
        ArrayList<RemoteSensing> models = new ArrayList<>();
        DataStore dataStore = DataStoreFinder.getDataStore(map);
        //字符转码，防止中文乱码
        ((ShapefileDataStore) dataStore).setCharset(Charset.forName("GBK"));
        String typeName = dataStore.getTypeNames()[0];
        FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
        FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures();
        FeatureIterator<SimpleFeature> features = collection.features();
        while (features.hasNext()) {
            SimpleFeature feature = features.next();
            RemoteSensing model = new RemoteSensing();
            Iterator<? extends Property> iterator = feature.getValue().iterator();

            //经纬度集合
            List<String> list=null;
            //shp颜色
            String color="";
            while (iterator.hasNext()) {
                Property property = iterator.next();
                //property数据与实体类对
                if (property.getName().toString().equals("the_geom")) {
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
                     color = (property != null ? property.getValue().toString() : "");
                     color= color != null ? color : "";


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
    /**
     * 根据 文件地址获取数据并且加载到页面
     * @param sysShpFileName
     * @param shapeReader
     * @param hashMaps
     */
    private static void getFileCoordinate(String sysShpFileName, ShapeReader shapeReader, ArrayList<HashMap> hashMaps) {
        /**
         * 用于拼接SHP文件内容
         */
        StringBuilder wipeOffShp = new StringBuilder();
        StringBuffer shpFils = new StringBuffer();

        /**
         * 根据文件地址取shp值
         */
        ArrayList<ShapeModel> shapeModels = shapeReader.readShapeFile(sysShpFileName);
        shapeModels.stream().forEach(shp -> {
            String subShp = shp.getGeoStr().substring(0, shp.getGeoStr().length() - 3).replaceAll("MULTIPOLYGON \\(", "").replaceAll("\\(\\(", "");
            /**
             * 通过 )) 分割成具体的面
             */
            Arrays.stream(subShp.split("\\)\\),")).forEach(splitShp -> {

                /**
                 * 用于存储shp面和洞的数据
                 */
                List<List> shpList = new ArrayList<>();
                List<List> wipeOffList = new ArrayList<>();
                HashMap<String, List> hashMap = new HashMap<>();
                wipeOffShp.setLength(0);
                shpFils.setLength(0);
                String[] split = splitShp.replace("(", "").split("\\),");
                /**
                 * 在通过 ) 分割 看是否有洞
                 */
                if (split.length > 1) {
                    for (int i = 1; i < split.length; i++) {
                        /**
                         * 如果分割后大于1证明有洞,把洞单独存储
                         */
                        wipeOffShp.append(split[i]).append("-");
                    }
                    /**
                     * 把单独正常的面 单独存储
                     */
                    shpFils.append(split[0]).append("-");
                } else {
                    /**
                     * 把单独正常的面 单独存储
                     */
                    shpFils.append(split[0]).append("-");
                }
                /**
                 * 使用,进行分割遍历出座标 ---- 洞
                 */
                if (StringUtils.isNotEmpty(wipeOffShp)) {
                    List<String> splitWipeOffShps = Arrays.asList(wipeOffShp.substring(0, wipeOffShp.length() - 1).split("-"));
                    splitWipeOffShps.stream().forEach(splWipeOffShp -> {
                        ArrayList<String> shps = new ArrayList<>();
                        Arrays.stream(splWipeOffShp.split(",")).forEach(wipeOff -> {
                            String lat = "";
                            String[] wipeOffSplit = wipeOff.trim().split(" ");
                            DecimalFormat df = new DecimalFormat("0.0000");
                            lat = df.format(Double.parseDouble(wipeOffSplit[0])) + " " + df.format(Double.parseDouble(wipeOffSplit[1]));
                            shps.add(lat);
                        });
                        wipeOffList.add(shps);
                    });
                }

                /**
                 * 使用,进行分割遍历出座标 ---- 正常面
                 */
                if (StringUtils.isNotEmpty(shpFils)) {
                    List<String> splitShps = Arrays.asList(shpFils.substring(0, shpFils.length() - 1).split("-"));
                    splitShps.stream().forEach(splShp -> {
                        ArrayList<String> shps = new ArrayList<>();
                        Arrays.stream(splShp.split(",")).forEach(shpFil -> {
                            String lat = "";
                            String[] splShpSplit = shpFil.trim().split(" ");
                            DecimalFormat df = new DecimalFormat("0.0000");
                            lat = df.format(Double.parseDouble(splShpSplit[0])) + " " + df.format(Double.parseDouble(splShpSplit[1]));
                            shps.add(lat);
                        });
                        shpList.add(shps);
                    });
                }
                hashMap.put("wipeOffList", wipeOffList);
                hashMap.put("shpList", shpList);
                hashMaps.add(hashMap);
            });
        });
    }

    public static void main(String[] args) {
        String str="MULTIPOLYGON(((\n" +
                "89.17145538137623 47.82378283614834,\n" +
                "89.15025329397214 47.823954496226726, \n" +
                "89.13995361135417 47.82180683789695,\n" +
                "89.13127898977173 47.81733223178257,\n" +
                " 89.10019683644967 47.80512910708234, \n" +
                " 89.08986663625372 47.80182560404841,\n" +
                " 89.08116149709333 47.796195153084206,\n" +
                " 89.07431030080375 47.795146119189155,\n" +
                " 89.07360839650612 47.802916599279094,\n" +
                " 89.07855224416248 47.807723081729776,\n" +
                " 89.081924436545 47.81316661376543, \n" +
                " 89.0790328960167 47.82297031176905,\n" +
                " 89.07766723439474 47.83031354843538,\n" +
                " 89.08100127980498 47.834536386288185,\n" +
                " 89.12819671437916 47.83566552811073, \n" +
                " 89.13940429494664 47.836104214966376,\n" +
                " 89.15139007375632 47.83592111088811, \n" +
                " 89.17145538137623 47.82378283614834\n" +
                " )),((\n" +
                "89.17145538137623 47.82378283614834,\n" +
                "89.15025329397214 47.823954496226726, \n" +
                "89.13995361135417 47.82180683789695,\n" +
                "89.13127898977173 47.81733223178257,\n" +
                " 89.10019683644967 47.80512910708234, \n" +
                " 89.08986663625372 47.80182560404841,\n" +
                " 89.08116149709333 47.796195153084206,\n" +
                " 89.07431030080375 47.795146119189155,\n" +
                " 89.07360839650612 47.802916599279094,\n" +
                " 89.07855224416248 47.807723081729776,\n" +
                " 89.081924436545 47.81316661376543, \n" +
                " 89.0790328960167 47.82297031176905,\n" +
                " 89.07766723439474 47.83031354843538,\n" +
                " 89.08100127980498 47.834536386288185,\n" +
                " 89.12819671437916 47.83566552811073, \n" +
                " 89.13940429494664 47.836104214966376,\n" +
                " 89.15139007375632 47.83592111088811, \n" +
                " 89.17145538137623 47.82378283614834\n" +
                " )),((\n" +
                "89.17145538137623 47.82378283614834,\n" +
                "89.15025329397214 47.823954496226726, \n" +
                "89.13995361135417 47.82180683789695,\n" +
                "89.13127898977173 47.81733223178257,\n" +
                " 89.10019683644967 47.80512910708234, \n" +
                " 89.08986663625372 47.80182560404841,\n" +
                " 89.08116149709333 47.796195153084206,\n" +
                " 89.07431030080375 47.795146119189155,\n" +
                " 89.07360839650612 47.802916599279094,\n" +
                " 89.07855224416248 47.807723081729776,\n" +
                " 89.081924436545 47.81316661376543, \n" +
                " 89.0790328960167 47.82297031176905,\n" +
                " 89.07766723439474 47.83031354843538,\n" +
                " 89.08100127980498 47.834536386288185,\n" +
                " 89.12819671437916 47.83566552811073, \n" +
                " 89.13940429494664 47.836104214966376,\n" +
                " 89.15139007375632 47.83592111088811, \n" +
                " 89.17145538137623 47.82378283614834\n" +
                " )))";
        String s = str.replaceAll("MULTIPOLYGON\\(", "");
        String substring = s.substring(0, s.length() - 3);
        String[] split = substring.split("\\)\\),");
        for (String s1 : split) {
            System.out.println(s1);
        }
    }
}
