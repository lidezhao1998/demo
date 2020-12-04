package com.ruoyi.system.service.impl.gis;

import com.ruoyi.common.gis.EntCoordSyncJob;
import com.ruoyi.system.domain.gis.Region;
import com.ruoyi.system.mapper.gis.RegionMapper;
import com.ruoyi.system.service.gis.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/11/5
 */
@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    RegionMapper regionMapper;
    @Override
    public Region getRegionById(int id) {
        return regionMapper.getRegionById(id);
    }

    @Override
    public void updateAll() {
        regionMapper.getRegionList().stream().forEach(region -> {
            region.setLon_lat(EntCoordSyncJob.getCoordinate(region.getCity()+region.getName()));
            regionMapper.updateById(region);
        });
    }

    @Override
    public List<Region> provinceList() {
        return regionMapper.provinceList();
    }

    @Override
    public List<Region> cityList(int provinceCode) {
        return regionMapper.cityList(provinceCode);
    }

    @Override
    public List<Region> countyList(int cityCode) {
        return regionMapper.countyList(cityCode);
    }
}
