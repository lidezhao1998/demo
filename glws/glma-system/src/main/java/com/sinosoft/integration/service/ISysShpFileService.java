package com.sinosoft.integration.service;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.system.domain.SysRole;
import com.sinosoft.integration.domain.SysShpFile;

import java.util.List;

/**
 * shpFile管理 服务层
 * 
 * @author ruoyi
 */
public interface ISysShpFileService
{
    /**
     * 查询shpFile管理数据
     *
     * @param shpFile shpFile信息
     * @return shpFile信息集合
     */
    List<SysShpFile> selectShpFileList(SysShpFile shpFile);

    /**
     * 查询shpFile信息数
     *
     * @param shpFile shpFile信息
     * @return shpFile信息数
     */
    List<SysShpFile> selectShpTreeList(SysShpFile shpFile);

    /**
     * 查询shpFile管理树
     * 
     * @param shpFile shpFile信息
     * @return 所有shpFile信息
     */
    List<Ztree> selectShpFileTree(SysShpFile shpFile);

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    List<Ztree> roleShpFileTreeData(SysRole role);

    /**
     * 查询shpFile人数
     * 
     * @param parentId 父shpFileID
     * @return 结果
     */
    int selectShpFileCount(Long parentId);


    /**
     * 删除shpFile管理信息
     * 
     * @param shpFileId shpFileID
     * @return 结果
     */
    int deleteShpFileById(Long shpFileId);

    /**
     * 新增保存shpFile信息
     * 
     * @param shpFile shpFile信息
     * @return 结果
     */
    int insertShpFile(SysShpFile shpFile);

    /**
     * 修改保存shpFile信息
     * 
     * @param shpFile shpFile信息
     * @return 结果
     */
    int updateShpFile(SysShpFile shpFile);

    /**
     * 根据shpFileID查询信息
     * 
     * @param shpFileId shpFileID
     * @return shpFile信息
     */
    SysShpFile selectShpFileById(Long shpFileId);

    /**
     * 校验shpFile名称是否唯一
     * 
     * @param shpFile shpFile信息
     * @return 结果
     */
    String checkShpFileNameUnique(SysShpFile shpFile);
    int updateSysShpFileByIds(String[] ids);
}
