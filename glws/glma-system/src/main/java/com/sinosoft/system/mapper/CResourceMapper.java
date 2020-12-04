package com.sinosoft.system.mapper;

import com.sinosoft.system.domain.CResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 附件Mapper接口
 * 
 * @author sudongdong
 * @date 2020-05-28
 */
public interface CResourceMapper 
{
    /**
     * 查询附件
     * 
     * @param id 附件ID
     * @return 附件
     */
        CResource selectCResourceById(Long id);

    /**
     * 查询附件列表
     * 
     * @param cResource 附件
     * @return 附件集合
     */
    List<CResource> selectCResourceList(CResource cResource);

    /**
     * 新增附件
     * 
     * @param cResource 附件
     * @return 结果
     */
    int insertCResource(CResource cResource);

    /**
     * 修改附件
     * 
     * @param cResource 附件
     * @return 结果
     */
    int updateCResource(CResource cResource);

    /**
     * 删除附件
     * 
     * @param id 附件ID
     * @return 结果
     */
    int deleteCResourceById(Long id);

    /**
     * 批量删除附件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCResourceByIds(String[] ids);

    String selectCResourcePothoUrl(@Param("id") String id);

}
