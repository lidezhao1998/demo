package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysDictData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 字典表 数据层
 * 
 * @author ruoyi
 */
public interface SysDictDataMapper
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
    String selectDictLabel(@Param("dictType") String dictType, @Param("dictValue") String dictValue);

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    SysDictData selectDictDataById(Long dictCode);

    /**
     * 查询字典数据
     * 
     * @param dictType 字典类型
     * @return 字典数据
     */
    int countDictDataByType(String dictType);

    /**
     * 根据字典键值查询字典标签
     *
     * @param dictValue 字典键值
     * @return 字典标签
     */
    String selectDictValueToLabel(@Param("dictValue") String dictValue);
    /**
     * 根据字典编号查询字典标签
     *
     * @param dictCode 字典键值
     * @return 字典标签
     */
    String selectDictValueToLabels(@Param("dictCode") String dictCode);

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
    int deleteDictDataByIds(String[] ids);

    /**
     * 新增字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    int insertDictData(SysDictData dictData);

    /**
     * 修改字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    int updateDictData(SysDictData dictData);

    /**
     * 同步修改字典类型
     * 
     * @param oldDictType 旧字典类型
     * @param newDictType 新旧字典类型
     * @return 结果
     */
    int updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);

    /**
     * 根据字典键值查询字典dictcode
     *
     * @param
     * @return 字典标签
     */
    @Select("select dict_code from sys_dict_data where dict_label like '%${provinceLabel}%' limit 1")
    String selectDictValueCode(@Param("provinceLabel")String provinceLabel);

    /**
     * 直辖市根据字典键值查询字典dictcode
     *
     * @param
     * @return 直辖市字典标签
     */
    @Select("select dict_code from sys_dict_data where  dict_code=(select max(dict_code) from sys_dict_data where dict_label like'%${provinceLabel}%')  limit 1")
    String selectDictValueCityCode(@Param("provinceLabel")String provinceLabel);

    List<SysDictData> getCities(String provinceCode);

    List<SysDictData> getAreas(String cityCode);

    List<SysDictData> selectDictDataList();

    List<SysDictData> selectDictSonDataList(SysDictData dictData);

    int insertDictSonData(SysDictData dictData);

    List<SysDictData> selectDictdictCodeList(String dictCode);

    /* 查询当前用户所属地区下级城市 */
    List<SysDictData> selectBelongAddressByDeptName(String deptName);

    /**
     * 根据部门名称查询代码
     * */
    String selectDictCodeByDictLabel(String dictLabel);

    List<SysDictData>  selectDictValueToLabelsWz();

    List<SysDictData>  selectDictValueToLabelsWzN(String id);
}
