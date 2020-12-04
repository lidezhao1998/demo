package com.ruoyi.common.gis;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
import org.apache.commons.lang3.StringUtils;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import com.vividsolutions.jts.geom.Geometry;
 
/**
 * 解析shp文件
 * @author xujiajia
 *
 */
public class ShapeUtils {

	private static final Logger logger = LoggerFactory.getLogger(ShapeUtils.class);
public static void main(String[] args) throws IOException {
	String shpFilePath = "D:\\shp\\生态.shp";
	long time = System.currentTimeMillis();
	List<Map<String,Object>> readFile = readFile(shpFilePath, null);
	long time2 = System.currentTimeMillis();
	System.out.println("耗时：" + (time2 - time));
	System.out.println(readFile);
	
}

/**
 * 读取shp文件
 * 
 * @Description
 * @author gxx
 * @date: 2019年7月3日 上午9:33:43
 */
public static List<Map<String, Object>> readFile(String shapePath, String prjPath) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	ShapefileDataStoreFactory factory = new ShapefileDataStoreFactory();
	ShapefileDataStore dataStore = null;
	try {
		dataStore = (ShapefileDataStore) factory.createDataStore(new File(shapePath).toURI().toURL());
		if (dataStore != null) {
			dataStore.setCharset(Charset.forName("UTF-8"));
		}
		String wkt = "";
//		if(StringUtils.isNotBlank(prjPath)) {
//			wkt = TxtUtils.readTxtFile(prjPath);
//			logger.info("获取到shp文件坐标系：{}", wkt);
//		}
		boolean change = false;
		//如果是投影坐标系，则进行坐标转换
		if(wkt.startsWith("PROJCS")) {
			change = true;
		}
		SimpleFeatureSource featureSource = dataStore.getFeatureSource();
		SimpleFeatureIterator itertor = featureSource.getFeatures().features();
//		一个用于处理FeatureCollection的实用工具类。提供一个获取FeatureCollection实例的机制
//  		FeatureCollection<SimpleFeatureType, SimpleFeature> result=featureSource.getFeatures();
//  		
//  		FeatureIterator<SimpleFeature> iterator = result.features();
		while (itertor.hasNext()) {
			SimpleFeature feature = itertor.next();
			Iterator<Property> it = feature.getProperties().iterator();
			Map<String, Object> map = new HashMap<String, Object>();
			while (it.hasNext()) {
				Property pro = it.next();
				if(change && "the_geom".equals(String.valueOf(pro.getName()))) {
					map.put(String.valueOf(pro.getName()), CoordConverter.convert((Geometry)pro.getValue(), wkt));
				} else {
					map.put(String.valueOf(pro.getName()), String.valueOf(pro.getValue()));
				}
			}
			list.add(map);
//			SimpleFeature feature = (SimpleFeature) iterator.next();
//            // Feature转GeoJSON
//            FeatureJSON fjson = new FeatureJSON();
//            StringWriter writer = new StringWriter();
//            fjson.writeFeature(feature, writer);
//            String sjson = writer.toString();
//            System.out.println("sjson=====  >>>>  "  + sjson);
		}
		itertor.close();
		return list;
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
	} finally {
		if(dataStore != null) {
			dataStore.dispose();
		}
	}
	return null;
}

/**
 * 丢给从一个文件中获取Shape文件路径
 *
 * @param path 文件夹路径
 * @param path 需要获得的shape文件路径
 */
public static void getShapePath(String path, List<String> fielPath) {
	File file = new File(path);
	if (file.exists() && file.isDirectory()) {
		File[] files = file.listFiles();
		for (File file1 : files) {
			if (file1.isDirectory()) {
				getShapePath(file1.getPath(), fielPath);
			} else {
				String fileName = file1.getName();
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				if (".shp".equals(suffix)) {
					fielPath.add(file1.getAbsolutePath());
					break;
				}
			}
		}
	}
}


/**
 * 获取prj文件路径
 * @Description
 * @author gxx
 * @date: 2019年8月8日 下午6:12:51
 */
public static void getPrjPath(String path, List<String> fielPath) {
	File file = new File(path);
	if (file.exists() && file.isDirectory()) {
		File[] files = file.listFiles();
		for (File file1 : files) {
			if (file1.isDirectory()) {
				getShapePath(file1.getPath(), fielPath);
			} else {
				String fileName = file1.getName();
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				if (".prj".equals(suffix)) {
					fielPath.add(file1.getAbsolutePath());
					break;
				}
			}
		}
	}
}


/**
 * 获取Shape文件的坐标系信息,GEOGCS表示这个是地址坐标系,PROJCS则表示是平面投影坐标系
 * @Description
 * @author gxx
 * @date: 2019年7月5日 上午9:04:07
 */
public static String getCoordinateSystemWKT(String path) {
	ShapefileDataStoreFactory factory = new ShapefileDataStoreFactory();
	ShapefileDataStore dataStore = null;
	try {
		dataStore = (ShapefileDataStore) factory.createDataStore(new File(path).toURI().toURL());
		return dataStore.getSchema().getCoordinateReferenceSystem().toWKT();
	} catch (UnsupportedOperationException | IOException e) {
		logger.error(e.getMessage(), e);
	} finally {
		dataStore.dispose();
	}
	return "";
}

}
