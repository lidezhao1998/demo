package com.ruoyi.common.gis;

import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/5/12 15:42
 */
public class ShpNew {
    public static void main(String[] args) {

        ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();

        try {

            ShapefileDataStore sds = (ShapefileDataStore)dataStoreFactory.createDataStore(new File("D:/shp/生态.shp").toURI().toURL());

            sds.setCharset(Charset.forName("GBK"));

            SimpleFeatureSource featureSource = sds.getFeatureSource();

            SimpleFeatureIterator itertor = featureSource.getFeatures().features();



            while(itertor.hasNext()) {

                SimpleFeature feature = itertor.next();

                Iterator<Property> it = feature.getProperties().iterator();



                while(it.hasNext()) {

                    Property pro = it.next();

                    System.out.println(pro);

                }

            }

            itertor.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
