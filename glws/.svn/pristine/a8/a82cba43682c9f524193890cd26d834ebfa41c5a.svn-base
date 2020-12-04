package com.ruoyi.system.mapper.gis;

import com.ruoyi.common.datasource.DataSource;
import com.ruoyi.system.domain.gis.Region;

import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/11/5
 */
@DataSource("gis")
public interface RegionMapper {

    List<Region> getRegionList();

    void updateById(Region region);

    Region getRegionById(int id);

    List<Region> provinceList();

    List<Region> cityList(int provinceCode);

    List<Region> countyList(int cityCode);
}
