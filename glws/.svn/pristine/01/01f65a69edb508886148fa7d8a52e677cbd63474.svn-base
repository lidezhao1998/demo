package com.sinosoft.extinterface.service.impl;

import com.ruoyi.system.domain.gis.SysCity;
import com.sinosoft.extinterface.domain.A5gInterface;
import com.sinosoft.extinterface.domain.LandSurvey;
import com.sinosoft.extinterface.domain.MobileDevice;
import com.sinosoft.extinterface.mapper.MobileDeviceDataMapper;
import com.sinosoft.extinterface.service.MobileDeviceDataServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author sunlei
 * @description 扩展接口
 * @date 2020/08/26/9:40
 */
@Service
public class MobileDeviceDataServerImpl implements MobileDeviceDataServer {
    @Autowired
    MobileDeviceDataMapper mobileDeviceDataMapper;
    @Override
    public List<SysCity> getDistrictSheng() {
        return  mobileDeviceDataMapper.getDistrictSheng();
    }

    @Override
    public HashMap<String, Object> getDistrictShi(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("city", mobileDeviceDataMapper.getCityById(id));
        hashMap.put("citys", mobileDeviceDataMapper.getDistrictShi(id));
        return hashMap;
    }

    @Override
    public HashMap<String, Object> getDistrictQu(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("city", mobileDeviceDataMapper.getCityById(id));
        hashMap.put("citys", mobileDeviceDataMapper.getDistrictShi(id));
        return hashMap;
    }

    @Override
    public HashMap<String, Object> getVideo(String code) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<A5gInterface> a5gInterface=mobileDeviceDataMapper.getA5gInterface(code);
        hashMap.put("a5g",a5gInterface);
        return hashMap;
    }

    @Override
    public List<MobileDevice> selectmobileDeviceList(MobileDevice mobileDevice) {
        return mobileDeviceDataMapper.selectmobileDeviceListMapper(mobileDevice);
    }

    @Override
    public int insertmobileDevice(MobileDevice mobileDevice) {

        return mobileDeviceDataMapper.insertmobileDevice(mobileDevice);
    }

    @Override
    public MobileDevice getMobileDeviceById(Long id) {
        return mobileDeviceDataMapper.getMobileDeviceById(id);
    }


}
