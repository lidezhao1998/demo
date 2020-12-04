package com.ruoyi.web.controller;

import it.geosolutions.geoserver.rest.GeoServerRESTManager;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.decoder.RESTDataStore;
import it.geosolutions.geoserver.rest.decoder.RESTLayer;
import it.geosolutions.geoserver.rest.encoder.GSLayerEncoder;
import it.geosolutions.geoserver.rest.encoder.datastore.GSPostGISDatastoreEncoder;
import it.geosolutions.geoserver.rest.encoder.datastore.GSShapefileDatastoreEncoder;
import it.geosolutions.geoserver.rest.encoder.feature.GSFeatureTypeEncoder;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GeoserverPostGIS {
    public static void main(String[] args) throws IOException {
        //GeoServer的连接配置
        String url = "http://localhost:8080/geoserver";
        String username = "admin";
        String passwd = "geoserver";
        //GeoserverPublishPostGISData(url, username, passwd);
//    GeoserverPublishShapefileData(url, username, passwd);
    }

    //发布postgis中的数据
    public static void GeoserverPublishPostGISData(String url, String username, String passwd) throws IOException {
	
    //postgis连接配置
    String postgisHost = "localhost" ;
    int postgisPort = 5432 ;//端口号
    String postgisUser = "admin" ;//用户名
    String postgisPassword = "postgres" ;//用户密码
    String postgisDatabase = "sun_test" ;//数据库名称

    String ws = "testNew" ;     //待创建和发布图层的工作区名称workspace
    String store_name = "testGeoserver" ; //待创建和发布图层的数据存储名称store
    String table_name = "roa_4m" ; // 数据库要发布的表名称,后面图层名称和表名保持一致
    //判断工作区（workspace）是否存在，不存在则创建
    URL u = new URL(url);
    GeoServerRESTManager manager = new GeoServerRESTManager(u, username, passwd);
   
    GeoServerRESTPublisher publisher = manager.getPublisher() ;
    List<String> workspaces = manager.getReader().getWorkspaceNames();
    if(!workspaces.contains(ws)){
        boolean createws = publisher.createWorkspace(ws);
        System.out.println("create ws : " + createws);
    }else {
        System.out.println("workspace已经存在了,ws :" + ws);
    }


    //判断数据存储（datastore）是否已经存在，不存在则创建
    RESTDataStore restStore = manager.getReader().getDatastore(ws, store_name);
    if(restStore == null){
        GSPostGISDatastoreEncoder store = new GSPostGISDatastoreEncoder(store_name);
        store.setHost(postgisHost);//设置url
        store.setPort(postgisPort);//设置端口
        store.setUser(postgisUser);// 数据库的用户名
        store.setPassword(postgisPassword);// 数据库的密码
        store.setDatabase(postgisDatabase);// 那个数据库;
        store.setSchema("public"); //当前先默认使用public这个schema
        store.setConnectionTimeout(20);// 超时设置
        //store.setName(schema);
        store.setMaxConnections(20); // 最大连接数
        store.setMinConnections(1);     // 最小连接数
        store.setExposePrimaryKeys(true);
        boolean createStore = manager.getStoreManager().create(ws, store);
        System.out.println("create store : " + createStore);
    } else {
        System.out.println("数据存储已经存在了,store:" + store_name);
    }

    //判断图层是否已经存在，不存在则创建并发布
    RESTLayer layer = manager.getReader().getLayer(ws, table_name);
    if(layer == null){
        GSFeatureTypeEncoder pds = new GSFeatureTypeEncoder();
        pds.setTitle(table_name);
        pds.setName(table_name);
        pds.setSRS("EPSG:4326");
        GSLayerEncoder layerEncoder = new GSLayerEncoder();
        
        boolean publish = manager.getPublisher().publishDBLayer(ws, store_name,  pds, layerEncoder);
        System.out.println("publish : " + publish);
    }else {
        System.out.println("表已经发布过了,table:" + table_name);
    }
	
}
//发布shapefile数据
public static void GeoserverPublishShapefileData(String url,String username,String passwd) throws IOException{
  
   /* String ws = "testshape" ;     //待创建和发布图层的工作区名称workspace
    String store_name = "testShapeStore" ; //待创建和发布图层的数据存储名称store
    String srs="EPSG:4326";
    //压缩文件的完整路径
    File zipFile=new File("C:/Users/Administrator/Desktop/122/草地生态系统.zip");
    String layername="sun_test";//图层名称
    //shp文件所在的位置
    String urlDatastore="file:data/shapefiles/states.shp";
	//判断工作区（workspace）是否存在，不存在则创建
    URL u = new URL(url);
    //获取管理对象
    GeoServerRESTManager manager = new GeoServerRESTManager(u, username, passwd);
   //获取发布对象
    GeoServerRESTPublisher publisher = manager.getPublisher() ;
    //获取所有的工作空间名称
    List<String> workspaces = manager.getReader().getWorkspaceNames();
    //判断工作空间是否存在
    if(!workspaces.contains(ws)){
    	//创建一个新的存储空间
        boolean createws = publisher.createWorkspace(ws);
        System.out.println("create ws : " + createws);
    }else {
        System.out.println("workspace已经存在了,ws :" + ws);
    }

    //判断数据存储（datastore）是否已经存在，不存在则创建
    URL urlShapefile = new URL(urlDatastore);
    RESTDataStore restStore = manager.getReader().getDatastore(ws, store_name);
    if(restStore == null){  
    	//创建shape文件存储
        GSShapefileDatastoreEncoder store = new GSShapefileDatastoreEncoder(store_name, urlShapefile);
        boolean createStore = manager.getStoreManager().create(ws, store);
        System.out.println("create store : " + createStore);
    } else {
        System.out.println("数据存储已经存在了,store:" + store_name);
    }

    //判断图层是否已经存在，不存在则创建并发布
    RESTLayer layer = manager.getReader().getLayer(ws, layername);
    if(layer == null){
    	//发布图层
        boolean publish = manager.getPublisher().publishShp(ws, store_name, layername, zipFile, srs);
        System.out.println("publish : " + publish);
    }else {
    	
        System.out.println("表已经发布过了,table:" + store_name);
    }
	*/
    }

}


