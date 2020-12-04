package com.sinosoft.extinterface.service;


import com.ruoyi.system.domain.gis.SysCity;
import com.sinosoft.extinterface.domain.LandSurvey;
import com.sinosoft.extinterface.domain.MobileDevice;

import java.util.HashMap;
import java.util.List;

/**
 * @author sunlei
 * @description
 * @date 2020/08/26/9:40
 */
public interface MobileDeviceDataServer {
    List<SysCity> getDistrictSheng();

    HashMap<String,Object> getDistrictShi(Long id);

    HashMap<String,Object> getDistrictQu(Long id);

    HashMap<String,Object> getVideo(String code);

    List<MobileDevice> selectmobileDeviceList(MobileDevice mobileDevice);
    /**
     *功能描述  保存数据
     * @author sunlei
     * @date 2020/11/12
     * @param
     * @return [mobileDevice]
     */
    int insertmobileDevice(MobileDevice mobileDevice);
    /**
     *功能描述 查看详情
     * @author sunlei
     * @date 2020/11/18
     * @param
     * @return [id]
     */
    MobileDevice getMobileDeviceById(Long id);
}
