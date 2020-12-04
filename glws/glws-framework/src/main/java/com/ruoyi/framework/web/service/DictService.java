package com.ruoyi.framework.web.service;

import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.gis.GisFeatures;
import com.ruoyi.system.domain.gis.GrasslandEcology;
import com.ruoyi.system.mapper.gis.GisFeaturesMapper;
import com.ruoyi.system.mapper.gis.GresslandEcologyMapper;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * RuoYi首创 html调用 thymeleaf 实现字典读取
 *
 * @author ruoyi
 */
@Service("dict")
public class DictService
{
    @Autowired
    private GisFeaturesMapper gisFeaturesMapper;
    @Autowired
    private ISysDictDataService dictDataService;
    @Autowired
    private GresslandEcologyMapper gresslandEcologyMapper;

    public List<GrasslandEcology> getProvince() {
        return gresslandEcologyMapper.getProvince();
    }

    public List<GisFeatures> getGisFeatures(){
        return  gisFeaturesMapper.getListByType().stream().filter(gisFeature -> gisFeature.getParentId()!=0).collect(Collectors.toList());
    }
    /**
     * 根据字典类型查询字典数据信息
     * 
     * @param dictType 字典类型
     * @return 参数键值
     */
    public List<SysDictData> getType(String dictType)
    {
        return dictDataService.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String getLabel(String dictType, String dictValue)
    {
        return dictDataService.selectDictLabel(dictType, dictValue);
    }

    /**
     * 根据字典类型查询字典数据信息
     *
     *
     * @return 参数键值
     */
    public List<SysDictData> getCode(String dictCode)
    {
        return dictDataService.selectDictdictCodeList(dictCode);
    }
    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     *
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String getLabels( String dictValue)
    {
        return dictDataService.selectDictValueToLabels (dictValue);
    }

    public List<SysDictData> getTypeWz()
    {
        return dictDataService.selectDictDataByTypeWz();
    }

    public List<SysDictData> getTypeWzN(String id)

    {
        List<SysDictData> list=dictDataService.selectDictValueToLabelsWzN(id);
        return list;
    }
}
