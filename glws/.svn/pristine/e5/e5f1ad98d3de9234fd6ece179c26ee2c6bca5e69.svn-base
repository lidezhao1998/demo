package com.sinosoft.analyze.service.impl;

import com.ruoyi.common.gis.ShapeReader;
import com.ruoyi.common.utils.StringUtils;
import com.sinosoft.integration.domain.SysShpFile;
import com.sinosoft.integration.mapper.SysShpFileMapper;
import com.sinosoft.analyze.service.ISysGisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/6/23
 */
@Service
public class SysSysGisServiceImpl implements ISysGisService {
    private static final Logger log = LoggerFactory.getLogger(SysSysGisServiceImpl.class);

    @Autowired
    private SysShpFileMapper sysShpFileMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 根据shpId 返回对应的文件地址
     *
     * @param shpIds
     * @return
     */
    @Override
    public List<HashMap<String, Object>> getShpIds(String shpIds) {
        List<Long> shpIdList = new ArrayList<>();
        List<SysShpFile> sysShps = sysShpFileMapper.getShpByInShpId(shpIds);
        List<HashMap<String, Object>> hashMaps = new ArrayList<>();
        /**
         * 返回所有id
         */
        sysShps.stream().forEach(shp -> {
            showList(sysShps, shp.getAncestors(), shpIdList, shp.getShpFileId());
        });
        shpIdList.stream().forEach(shpId -> {
            hashMaps.add(getShpList(String.valueOf(shpId)));
        });
        return hashMaps;
    }

    /**
     * 取出最小级别的图层id
     *
     * @param sysShps
     * @param shpIdList
     * @param sysAncestors
     * @param shpId
     */
    private static void showList(List<SysShpFile> sysShps, String sysAncestors, List<Long> shpIdList, Long shpId) {
        int count = 0;
        for (SysShpFile sysShp : sysShps) {
            if (!sysShp.getAncestors().equals(sysAncestors) && sysShp.getAncestors().contains(sysAncestors)) {
                count++;
            }
        }
        if (count < 1) {
            shpIdList.add(shpId);
        }
    }

    /**
     * @Describe 根据shpId读取 SHP 文件
     * @Params [filePath]
     * @DATE 2020/6/5
     * @Author LiRenDong
     */
    public HashMap<String, Object> getShpList(String id) {
        /**
         * 返回读取详情
         */
        HashMap<String, Object> hashMap = new HashMap<>();
        ShapeReader shapeReader = new ShapeReader();
        SysShpFile sysShpFile = sysShpFileMapper.selectShpFileById(Long.parseLong(id));

        /**
         * 存储shp文件读取详情
         */
        ArrayList<HashMap> hashMaps = new ArrayList<>();

        Object obj = redisTemplate.opsForList().index(id, 0);
        if (obj == null) {
            log.info("取文件" + id);
            getFileCoordinate(sysShpFile.getShpFilePath(), shapeReader, hashMaps);
            //getFileCoordinate(sysShpFile.getShpFilePath().replace("/root/", "E:\\项目\\林业项目\\").replace("\\", "/"), shapeReader, hashMaps);
            redisTemplate.opsForList().leftPush(id, hashMaps);
            hashMap.put("shpData", hashMaps);
        } else {
            log.info("取缓存" + id);
            hashMap.put("shpData", obj);
        }
        long endTime = System.currentTimeMillis();
        hashMap.put("shpColor", sysShpFile.getShpColor());
        //String[] splitShpFilePath = sysShpFile.getShpFilePath().replace("/root/", "E:\\项目\\林业项目\\").replace("\\", "/").replace(".shp", "").split("/");
        String[] splitShpFilePath = sysShpFile.getShpFilePath().replace("\\", "/").replace(".shp", "").split("/");
        hashMap.put("shpName", splitShpFilePath[splitShpFilePath.length - 2] + "-" + splitShpFilePath[splitShpFilePath.length - 1]);
        return hashMap;
    }

    private static void getFileCoordinate(String sysShpFileName, ShapeReader shapeReader, ArrayList<HashMap> hashMaps) {
        /**
         * 用于拼接SHP文件内容
         */
        StringBuilder wipeOffShp = new StringBuilder();
        StringBuffer shpFils = new StringBuffer();

        /**
         * 根据文件地址取shp值
         */
        shapeReader.readShapeFile(sysShpFileName).stream().forEach(shp -> {
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
                            DecimalFormat df = new DecimalFormat("0.000000");
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
                            DecimalFormat df = new DecimalFormat("0.000000");
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
}
