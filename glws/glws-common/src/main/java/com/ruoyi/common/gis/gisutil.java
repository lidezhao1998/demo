package com.ruoyi.common.gis;

import com.vividsolutions.jts.geom.Coordinate;
import org.geotools.data.FeatureWriter;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.shapefile.dbf.DbaseFileHeader;
import org.geotools.data.shapefile.dbf.DbaseFileReader;
import org.geotools.data.shapefile.files.ShpFiles;
import org.geotools.data.shapefile.shp.ShapefileReader;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.primitive.Point;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/5/12 15:46
 */
public class gisutil {
    public static void readShp(){
        try {
            ShpFiles sf = new ShpFiles("D:/shp/生态.shp");
            ShapefileReader r = new ShapefileReader( sf, false, false, JTSFactoryFinder.getGeometryFactory());
            while (r.hasNext()) {
                Geometry shape = (Geometry) r.nextRecord().shape();  //com.vividsolutions.jts.geom.Geometry;
                System.out.println(shape.toString());
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readDbf(){
        try {
            FileChannel in = new FileInputStream("D:\\Poi.dbf").getChannel();
            DbaseFileReader dbfReader =  new DbaseFileReader(in, false,  Charset.forName("GBK"));
            DbaseFileHeader header = dbfReader.getHeader();
            int fields = header.getNumFields();
            while ( dbfReader.hasNext() ){
                DbaseFileReader.Row row =  dbfReader.readRow();
//              System.out.println(row.toString());
                for (int i=0; i<fields; i++) {
                    System.out.println(header.getFieldName(i) + " : " + row.read(i));
                }
            }
            dbfReader.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeShp(String filepath){
        try {
            //创建shape文件对象
            File file = new File(filepath);
            Map<String, Serializable> params = new HashMap<String, Serializable>();
            params.put( ShapefileDataStoreFactory.URLP.key, file.toURI().toURL() );
            ShapefileDataStore ds = (ShapefileDataStore) new ShapefileDataStoreFactory().createNewDataStore(params);
            //定义图形信息和属性信息
            SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
            tb.setCRS(DefaultGeographicCRS.WGS84);
            tb.setName("shapefile");
            tb.add("the_geom", Point.class);
            tb.add("POIID", Long.class);
            tb.add("NAMEC", String.class);
            ds.createSchema(tb.buildFeatureType());
            ds.setCharset(Charset.forName("GBK"));
            //设置Writer
            FeatureWriter<SimpleFeatureType, SimpleFeature> writer = ds.getFeatureWriter(ds.getTypeNames()[0], Transaction.AUTO_COMMIT);
            //写下一条
            SimpleFeature feature = writer.next();
            feature.setAttribute("the_geom",JTSFactoryFinder.getGeometryFactory().createPoint(new Coordinate(116.123, 39.345)));
            feature.setAttribute("POIID", 1234567890l);
            feature.setAttribute("NAMEC", "某兴趣点1");
            feature = writer.next();
            feature.setAttribute("the_geom", JTSFactoryFinder.getGeometryFactory().createPoint(new Coordinate(116.456, 39.678)));
            feature.setAttribute("POIID", 1234567891l);
            feature.setAttribute("NAMEC", "某兴趣点2");
            writer.write();
            writer.close();
            ds.dispose();

            //读取刚写完shape文件的图形信息
            ShpFiles shpFiles = new ShpFiles(filepath);
            ShapefileReader reader = new ShapefileReader(shpFiles, false, true, JTSFactoryFinder.getGeometryFactory(), false);
            try {
                while (reader.hasNext()) {
                    System.out.println(reader.nextRecord().shape());
                }
            } finally {
                reader.close();
            }
        } catch (Exception e) { }
    }

    public static void main(String[] args) {
        writeShp("D:/shp/生态.shp");
    }

}
