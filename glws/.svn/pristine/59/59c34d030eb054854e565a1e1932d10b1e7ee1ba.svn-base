package com.ruoyi.common.utils;

import it.geosolutions.geoserver.rest.GeoServerRESTManager;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.decoder.RESTDataStore;
import it.geosolutions.geoserver.rest.decoder.RESTLayer;
import it.geosolutions.geoserver.rest.encoder.datastore.GSShapefileDatastoreEncoder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author sunlei
 * @description
 * @date 2020/07/20/10:47
 */
public class GeoServerUtils {


    /**
     * 功能描述
     *
     * @param
     * @return [url 服务地址, username 用户名, password 密码, workspace 创建和发布图层的工作区名称, srs , filePath 文件路径, layername 图层名]
     * @author sunlei
     * @date 2020/7/22
     */
    public static boolean GeoserverPublishShapefileData(String url, String username, String password, String workspace, String srs, String filePath, String layername) throws IOException {
        boolean falg = false;
        String store_name = layername; //待创建和发布图层的数据存储名称store
        //压缩文件的完整路径
        File zipFile = new File(filePath + ".zip");
        //shp文件所在的位置
        String urlDatastore = "file:\\" + filePath + ".shp";
        //判断工作区（workspace）是否存在，不存在则创建
        URL u = new URL(url);
        //获取管理对象
        GeoServerRESTManager manager = new GeoServerRESTManager(u, username, password);
        //获取发布对象
        GeoServerRESTPublisher publisher = manager.getPublisher();
        //获取所有的工作空间名称
        List<String> workspaces = manager.getReader().getWorkspaceNames();
        //判断工作空间是否存在
        if (!workspaces.contains(workspace)) {
            //创建一个新的存储空间
            boolean createws = publisher.createWorkspace(workspace);
            System.out.println(workspace + "create ws : " + createws);
        } else {
            System.out.println("workspace已经存在了:" + workspace);
        }

        //判断数据存储（datastore）是否已经存在，不存在则创建
        URL urlShapefile = new URL(urlDatastore);
        RESTDataStore restStore = manager.getReader().getDatastore(workspace, store_name);
        if (restStore == null) {
            //创建shape文件存储
            GSShapefileDatastoreEncoder store = new GSShapefileDatastoreEncoder(store_name, urlShapefile);
            boolean createStore = manager.getStoreManager().create(workspace, store);
            System.out.println(store_name + "create store : " + createStore);
        } else {
            System.out.println("数据存储已经存在了:" + store_name);
        }
        //判断图层是否已经存在，不存在则创建并发布
        //图层是否存在
        RESTLayer layer = manager.getReader().getLayer(workspace, layername);
        if (layer == null) {
            //boolean publish = manager.getPublisher().publishShp(ws, store_name, layername, zipFile, srs,"burg");
            //创建并发布
            falg = manager.getPublisher().publishShp(workspace, store_name, layername, zipFile, srs);
            System.out.println(layername + "publish : " + falg);
        } else {

            System.out.println("表已经发布过了,table:" + store_name);
        }
        return falg;
    }

    /**
     * 功能描述 删除数据存储,
     *
     * @param
     * @return [store_name 数据存储]
     * @author sunlei
     * @date 2020/7/21
     */

    public static boolean remaveShapefile(String url, String username, String password, String workspace, String store_name) throws MalformedURLException {
        //shp文件所在的位置
        String urlDatastore = "file:data/" + workspace + store_name + "/" + store_name + ".shp";

        URL urlShapefile = new URL(urlDatastore);
        URL u = new URL(url);
        //获取geoserver 的操作对象
        GeoServerRESTManager manager = new GeoServerRESTManager(u, username, password);
        //删除
        GSShapefileDatastoreEncoder store = new GSShapefileDatastoreEncoder(store_name, urlShapefile);
        //删除数据存储
        manager.getStoreManager().remove(workspace, store, true);
        //删除图层
        return manager.getPublisher().removeLayer(workspace, store_name);
    }

    /**
     * 功能描述 图层颜色样式透明度,
     *
     * @param
     * @return [store_name 数据存储]
     * @author sunlei
     * @date 2020/7/21
     */

    public static boolean releaseShpStyle(String url, String username, String password, String styleName, String style) throws MalformedURLException {
        URL u = new URL(url);
        //获取geoserver 的操作对象
        GeoServerRESTManager manager = new GeoServerRESTManager(u, username, password);
        //发布颜色
        return manager.getPublisher().publishStyle(style,styleName);
    }

    public static void main(String[] args) throws IOException {
        String  a = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<StyledLayerDescriptor version=\"1.0.0\" \n" +
                " xsi:schemaLocation=\"http://www.opengis.net/sld StyledLayerDescriptor.xsd\" \n" +
                " xmlns=\"http://www.opengis.net/sld\" \n" +
                " xmlns:ogc=\"http://www.opengis.net/ogc\" \n" +
                " xmlns:xlink=\"http://www.w3.org/1999/xlink\" \n" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "  <!-- a Named Layer is the basic building block of an SLD document -->\n" +
                "  <NamedLayer>\n" +
                "    <Name>default_polygon</Name>\n" +
                "    <UserStyle>\n" +
                "    <!-- Styles can have names, titles and abstracts -->\n" +
                "      <Title>Default Polygon</Title>\n" +
                "      <Abstract>A sample style that draws a polygon</Abstract>\n" +
                "      <!-- FeatureTypeStyles describe how to render different features -->\n" +
                "      <!-- A FeatureTypeStyle for rendering polygons -->\n" +
                "      <FeatureTypeStyle>\n" +
                "        <Rule>\n" +
                "          <Name>rule1</Name>\n" +
                "          <Title>Gray Polygon with Black Outline</Title>\n" +
                "          <Abstract>A polygon with a gray fill and a 1 pixel black outline</Abstract>\n" +
                "          <PolygonSymbolizer>\n" +
                "            <Fill>\n" +
                "              <CssParameter name=\"fill\">#345223</CssParameter>\n" +
                "            </Fill>\n" +
                "            <Stroke>\n" +
                "              <CssParameter name=\"stroke\">#345223</CssParameter>\n" +
                "              <CssParameter name=\"stroke-width\">1</CssParameter>\n" +
                "            </Stroke>\n" +
                "          </PolygonSymbolizer>\n" +
                "        </Rule>\n" +
                "      </FeatureTypeStyle>\n" +
                "    </UserStyle>\n" +
                "  </NamedLayer>\n" +
                "</StyledLayerDescriptor>\n";
        String url = "http://127.0.0.1:8080/geoserver";
        String username = "admin";
        String workspace = "test";
        String passwd = "geoserver";

        releaseShpStyle(url,username,passwd,"lrd",a);
//        GeoserverPublishPostGISData(url, username, passwd);
        //GeoserverPublishShapefileData(url,username,passwd,workspace,"ESAP:4326","//C:/Users/Administrator/Desktop/测试/5", "5");

    }

}
