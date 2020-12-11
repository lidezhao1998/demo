package com.ruoyi.system.service.impl;

import cn.hutool.core.convert.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysDictDataServiceImpl implements ISysDictDataService
{
    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 根据字典数据查询市对应的省
     *
     * @param deptName
     * @return 字典数据
     */
    public SysDictData selectProvinceByCity(String deptName){
        return dictDataMapper.selectProvinceByCity(deptName);
    }

    /**
     * 根据条件分页查询字典数据
     * 
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData)
    {
        return dictDataMapper.selectDictDataList(dictData);
    }

    /**
     * 根据字典类型查询字典数据
     * 
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType)
    {
        return dictDataMapper.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue)
    {
        String dictLabel=null;
        if(StringUtils.isNotEmpty(dictValue)&&dictValue.contains(",")&&dictValue.split(",").length>1){
            String[] labelArry=dictValue.split(",");
           for(int i=0; i<labelArry.length;i++){
              String label=labelArry[i];
              if(dictLabel==null){
                  dictLabel=dictDataMapper.selectDictLabel(dictType, label);
              }else{
                  dictLabel=dictLabel+","+dictDataMapper.selectDictLabel(dictType, label);
              }

           }
        }else{
            dictLabel=dictDataMapper.selectDictLabel(dictType, dictValue);
        }





        return dictLabel;
    }

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictData selectDictDataById(Long dictCode)
    {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    /**
     * 根据字典键值查询字典标签
     *
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String selectDictValueToLabel(String dictValue){
        return dictDataMapper.selectDictValueToLabel(dictValue);
    }
    /**
     * 根据字典编号查询字典标签
     *
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String selectDictValueToLabels(String dictValue){
        return dictDataMapper.selectDictValueToLabels(dictValue);
    }
    public List<SysDictData> selectDictDataByTypeWz(){
        return dictDataMapper.selectDictValueToLabelsWz();
    }
    public List<SysDictData> selectDictValueToLabelsWzN(String id){
        return dictDataMapper.selectDictValueToLabelsWzN(id);
    }
    /**
     * 通过字典ID删除字典数据信息
     * 
     * @param dictCode 字典数据ID
     * @return 结果
     */
    @Override
    public int deleteDictDataById(Long dictCode)
    {
        return dictDataMapper.deleteDictDataById(dictCode);
    }

    /**
     * 批量删除字典数据
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Override
    public int deleteDictDataByIds(String ids)
    {
        return dictDataMapper.deleteDictDataByIds(Convert.toStrArray(ids));
    }

    /**
     * 新增保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(SysDictData dictData)
    {
        return dictDataMapper.insertDictData(dictData);
    }

    /**
     * 修改保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(SysDictData dictData)
    {
        return dictDataMapper.updateDictData(dictData);
    }

    /**
     * 根据字典键值查询字典dictcode
     *
     * @param  provinceLabel 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictValueCode(String provinceLabel) {
        return dictDataMapper.selectDictValueCode(provinceLabel);
    }

    /**
     * 根据条件分页查询字典数据
     *
     * @param provinceCode 省份code
     * @return 省数据集合信息
     */
    @Override
    public List<SysDictData> getCities(String provinceCode) {
        return dictDataMapper.getCities(provinceCode);
    }
    /**
     * 根据条件分页查询字典数据
     *
     * @param cityCode 城市code
     * @return 市集合信息
     */
    @Override
    public List<SysDictData> getAreas(String cityCode) {
        return dictDataMapper.getAreas(cityCode);
    }


    /**
     * 根据所有字典名称
     *
     * @return 字典名称集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList() {
        return dictDataMapper.selectDictDataList();
    }

    /**
     * 根据对应字典名称
     *
     * @return 对应字典名称集合信息
     */
    @Override
    public List<SysDictData> selectDictSonDataList(SysDictData dictData) {
        return dictDataMapper.selectDictSonDataList(dictData);
    }

    @Override
    public int insertDictSonData(SysDictData dict) {
        return dictDataMapper.insertDictSonData(dict);
    }

    @Override
    public List<SysDictData> selectDictdictCodeList(String dictCode) {
        return dictDataMapper.selectDictdictCodeList(dictCode);
    }

    /* 查询当前用户所属地区下级城市 */
    public List<SysDictData> selectBelongAddressByDeptName(String deptName){
        return dictDataMapper.selectBelongAddressByDeptName(deptName);
    }

    /**
     * 根据部门名称查询代码
     * */
    public String selectDictCodeByDictLabel(String dictLabel){
        return dictDataMapper.selectDictCodeByDictLabel(dictLabel);
    }
    /**
     * 直辖市查询区级
     * */
    @Override
    public String selectDictValueCityCode(String cityCode) {
        return dictDataMapper.selectDictValueCityCode(cityCode);
    }
}
