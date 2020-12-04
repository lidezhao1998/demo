package com.ruoyi.zaihai.ReserveManagement.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.zaihai.ReserveManagement.domain.KMaterialType;
import com.ruoyi.zaihai.ReserveManagement.mapper.KMaterialTypeMapper;
import com.ruoyi.zaihai.ReserveManagement.service.IKMaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-17
 */
@Service
public class KMaterialTypeServiceImpl implements IKMaterialTypeService
{
    @Autowired
    private KMaterialTypeMapper kMaterialTypeMapper;

    /**
     * 查询物资类型
     * 
     * @param id 物资类型ID
     * @return 物资类型
     */
    @Override
    public KMaterialType selectKMaterialTypeById(Long id)
    {
        return kMaterialTypeMapper.selectKMaterialTypeById(id);
    }

    /**
     * 查询物资类型列表
     * 
     * @param kMaterialType 物资类型
     * @return 物资类型
     */
    @Override
    public List<KMaterialType> selectKMaterialTypeList(KMaterialType kMaterialType)
    {
        return kMaterialTypeMapper.selectKMaterialTypeList(kMaterialType);
    }

    /**
     * 新增物资类型
     * 
     * @param kMaterialType 物资类型
     * @return 结果
     */
    @Override
    public int insertKMaterialType(KMaterialType kMaterialType)
    {
        kMaterialType.setCreateTime(DateUtils.getNowDate());
        return kMaterialTypeMapper.insertKMaterialType(kMaterialType);
    }

    /**
     * 修改物资类型
     * 
     * @param kMaterialType 物资类型
     * @return 结果
     */
    @Override
    public int updateKMaterialType(KMaterialType kMaterialType)
    {
        kMaterialType.setUpdateTime(DateUtils.getNowDate());
        return kMaterialTypeMapper.updateKMaterialType(kMaterialType);
    }

    /**
     * 删除物资类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKMaterialTypeByIds(String ids)
    {
        return kMaterialTypeMapper.deleteKMaterialTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物资类型信息
     * 
     * @param id 物资类型ID
     * @return 结果
     */
    @Override
    public int deleteKMaterialTypeById(Long id)
    {
        return kMaterialTypeMapper.deleteKMaterialTypeById(id);
    }

    @Override
    public Object selectDictTypeById(Long dictId) {
        return kMaterialTypeMapper.selectDictTypeById(dictId);
    }

    @Override
    public List<KMaterialType> selectDictTypeAll() {
        return kMaterialTypeMapper.selectDictTypeAll();
    }

    @Override
    public boolean checkCode(String code) {
        List<String> list = kMaterialTypeMapper.checkCode(code);
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 删除物资类型信息
     *
     * @param id 物资类型ID
     * @return 结果
     */
    @Override
    public KMaterialType selectKMaterialTypeByType(String type) {
        return kMaterialTypeMapper.selectKMaterialTypeByType(type);
    }


}
