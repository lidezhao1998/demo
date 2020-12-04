package com.ruoyi.system.service.impl.gis;

import com.ruoyi.system.domain.gis.SysCity;
import com.ruoyi.system.mapper.gis.ISysCityMapper;
import com.ruoyi.system.service.gis.ISysCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/7/15
 */
@Service
public class SysCityServiceImpl implements ISysCityService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ISysCityMapper iSysCityMapper;

    @Override
    public HashMap<String, Object> getCityById(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        SysCity sysCity = iSysCityMapper.getCityById(id);
        hashMap.put("city", sysCity);
        return hashMap;
    }

    @Override
    public List<SysCity> getDistrictSheng() {
        return iSysCityMapper.getDistrictSheng();
    }

    @Override
    public HashMap<String, Object> getDistrictShi(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("city", iSysCityMapper.getCityById(id));
        hashMap.put("citys", iSysCityMapper.getDistrictShi(id));
        return hashMap;
    }

    @Override
    public HashMap<String, Object> getDistrictQu(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("city", iSysCityMapper.getCityById(id));
        return hashMap;
    }

    private List<Object> getObjects(Long id) {
        List<Object> objects;
        SysCity sysCity = iSysCityMapper.getCityById(id);
        if (sysCity.getPid() > 100) {
            objects = redisTemplate.opsForList().range("city_Shi:" + sysCity.getId(), 0, -1);
        } else {
            objects = redisTemplate.opsForList().range("city_Sheng:" + sysCity.getId(), 0, -1);
        }
        return objects;
    }
}
