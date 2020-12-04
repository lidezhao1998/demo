package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysDictData;

/**
 * 字典 业务层
 * 
 * @author ruoyi
 */
public interface ISysDictDataService
{
    /**
     * 根据字典数据查询市对应的省
     *
     * @param deptName
     * @return 字典数据
     */
    SysDictData selectProvinceByCity(String deptName);

    /**
     * 根据条件分页查询字典数据
     * 
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    List<SysDictData> selectDictDataList(SysDictData dictData);

    /**
     * 根据字典类型查询字典数据
     * 
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    List<SysDictData> selectDictDataByType(String dictType);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    String selectDictLabel(String dictType, String dictValue);

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    SysDictData selectDictDataById(Long dictCode);

    /**
     * 根据字典键值查询字典标签
     *
     * @param dictValue 字典键值
     * @return 字典标签
     */
    String selectDictValueToLabel(String dictValue);

    /**
     * 根据字典编号查询字典标签
     *
     * @param dictCode 字典键值
     * @return 字典标签
     */
    String selectDictValueToLabels(String dictCode);

    List<SysDictData> selectDictDataByTypeWz();
    List<SysDictData>  selectDictValueToLabelsWzN(String id);

    /**
     * 通过字典ID删除字典数据信息
     * 
     * @param dictCode 字典数据ID
     * @return 结果
     */
    int deleteDictDataById(Long dictCode);

    /**
     * 批量删除字典数据
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteDictDataByIds(String ids);

    /**
     * 新增保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    int insertDictData(SysDictData dictData);

    /**
     * 修改保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    int updateDictData(SysDictData dictData);

    /**
     * 根据字典键值查询字典标签
     *
     * @param  dictLabel字典键值
     * @return 字典标签
     */
    String selectDictValueCode(String provinceLabel);


    /**
     * 根据查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    List<SysDictData> getCities(String provinceCode);

    /**
     * 根据字典数据
     *
     * @param dictType 字典
     * @return 字典数据集合信息
     */
    List<SysDictData> getAreas(String cityCode);


    /**
     * 根据所有字典名称
     *
     * @return 字典名称集合信息
     */
    List<SysDictData> selectDictDataList();

    /**
     * 根据对应字典名称
     *
     * @return 对应字典名称集合信息
     */
    List<SysDictData> selectDictSonDataList(SysDictData dictData);

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    int insertDictSonData(SysDictData dict);


    List<SysDictData> selectDictdictCodeList(String dictCode);

    /* 查询当前用户所属地区下级城市 */
    List<SysDictData> selectBelongAddressByDeptName(String deptName);

    /**
     * 根据部门名称查询代码
     * */
    String selectDictCodeByDictLabel(String dictLabel);
}
