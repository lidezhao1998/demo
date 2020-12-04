package com.sinosoft.integration.mapper;

import com.sinosoft.integration.domain.SysShpFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * shpFile管理 数据层
 * 
 * @author ruoyi
 */
public interface SysShpFileMapper
{
    /**
     * 查询shpFile人数
     * 
     * @param shpFile shpFile信息
     * @return 结果
     */
    int selectShpFileCount(SysShpFile shpFile);


    /**
     * 查询shpFile管理数据
     * 
     * @param shpFile shpFile信息
     * @return shpFile信息集合
     */
    List<SysShpFile> selectShpFileList(SysShpFile shpFile);

    /**
     * 查询shpFile信息树
     *
     * @param shpFile shpFile信息
     * @return shpFile信息树
     */
    List<SysShpFile> selectShpTreeList(SysShpFile shpFile);

    /**
     * 删除shpFile管理信息
     * 
     * @param shpFileId shpFileID
     * @return 结果
     */
    int deleteShpFileById(Long shpFileId);

    /**
     * 新增shpFile信息
     * 
     * @param shpFile shpFile信息
     * @return 结果
     */
    int insertShpFile(SysShpFile shpFile);

    /**
     * 修改shpFile信息
     * 
     * @param shpFile shpFile信息
     * @return 结果
     */
    int updateShpFile(SysShpFile shpFile);

    /**
     * 修改子元素关系
     * 
     * @param shpFiles 子元素
     * @return 结果
     */
    int updateShpFileChildren(@Param("shpFiles") List<SysShpFile> shpFiles);

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
     * @param shpFileName shpFile名称
     * @param parentId 父shpFileID
     * @return 结果
     */
    SysShpFile checkShpFileNameUnique(@Param("shpFileName") String shpFileName, @Param("parentId") Long parentId);

    /**
     * 根据角色ID查询shpFile
     *
     * @param roleId 角色ID
     * @return shpFile列表
     */
    List<String> selectRoleShpFileTree(Long roleId);

    /**
     * 修改所在shpFile的父级shpFile状态
     * 
     * @param shpFile shpFile
     */
    void updateShpFileStatus(SysShpFile shpFile);

    /**
     * 根据ID查询所有子shpFile
     * @param shpFileId shpFileID
     * @return shpFile列表
     */
    List<SysShpFile> selectChildrenShpFileById(Long shpFileId);

    List<SysShpFile> getFilebyShpAddress(List<SysShpFile> sysShpList);

    List<SysShpFile> getShpByInShpId(@Param("shpIds") String shpIds);

    int updateSysShpFileByIds(String[] ids);
}
