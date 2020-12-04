package com.sinosoft.analyze.service;

import java.util.HashMap;
import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/6/23
 */
public interface ISysGisService {

    List<HashMap<String, Object>> getShpIds(String shpIds);
}