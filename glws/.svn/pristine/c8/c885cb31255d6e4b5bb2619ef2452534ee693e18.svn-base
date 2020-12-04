package com.sinosoft.integration.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysRole;
import com.sinosoft.integration.domain.SysShpFile;
import com.sinosoft.integration.mapper.SysShpFileMapper;
import com.sinosoft.integration.service.ISysShpFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * shpFile管理 服务实现
 *
 * @author ruoyi
 */
@Service
public abstract class SysShpFileServiceImpl implements ISysShpFileService
{
    @Autowired
    private SysShpFileMapper shpFileMapper;

    /**
     * 查询shpFile管理数据
     *
     * @param shpFile shpFile信息
     * @return shpFile信息集合
     */
    @Override
    public List<SysShpFile> selectShpFileList(SysShpFile shpFile)
    {
        return shpFileMapper.selectShpFileList(shpFile);
    }

    /**
     * 查询shpFile信息数
     *
     * @param shpFile shpFile信息
     * @return shpFile信息数
     */
    @Override
    public List<SysShpFile> selectShpTreeList(SysShpFile shpFile)
    {
        return shpFileMapper.selectShpTreeList(shpFile);
    }

    /**
     * 查询shpFile管理树
     *
     * @param shpFile shpFile信息
     * @return 所有shpFile信息
     */
    @Override
    public List<Ztree> selectShpFileTree(SysShpFile shpFile)
    {
        List<SysShpFile> shpFileList = shpFileMapper.selectShpTreeList(shpFile);
        List<Ztree> ztrees = initZtree(shpFileList);
        return ztrees;
    }

    /**
     * 根据角色ID查询shpFile（数据权限）
     *
     * @param role 角色对象
     * @return shpFile列表（数据权限）
     */
    public List<Ztree> roleShpFileTreeData(SysRole role)
    {
        Long roleId = role.getRoleId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<SysShpFile> shpFileList = selectShpTreeList(new SysShpFile());
        if (StringUtils.isNotNull(roleId))
        {
            List<String> roleShpFileList = shpFileMapper.selectRoleShpFileTree(roleId);
            ztrees = initZtree(shpFileList, roleShpFileList);
        }
        else
        {
            ztrees = initZtree(shpFileList);
        }
        return ztrees;
    }

    /**
     * 对象转shpFile树
     *
     * @param shpFileList shpFile列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysShpFile> shpFileList)
    {
        return initZtree(shpFileList, null);
    }

    /**
     * 对象转shpFile树
     *
     * @param shpFileList shpFile列表
     * @param roleShpFileList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysShpFile> shpFileList, List<String> roleShpFileList)
    {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (SysShpFile shpFile : shpFileList)
        {
            if (UserConstants.DEPT_NORMAL.equals(shpFile.getStatus()))
            {
                Ztree ztree = new Ztree();
                ztree.setId(shpFile.getShpFileId());
                ztree.setpId(shpFile.getParentId());
                ztree.setName(shpFile.getShpFileName());
                ztree.setTitle(shpFile.getShpFileName());
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    /**
     * 查询shpFile人数
     *
     * @param parentId shpFileID
     * @return 结果
     */
    @Override
    public int selectShpFileCount(Long parentId)
    {
        SysShpFile shpFile = new SysShpFile();
        shpFile.setParentId(parentId);
        return shpFileMapper.selectShpFileCount(shpFile);
    }
    /**
     * 删除shpFile管理信息
     *
     * @param shpFileId shpFileID
     * @return 结果
     */
    @Override
    public int deleteShpFileById(Long shpFileId)
    {
        return shpFileMapper.deleteShpFileById(shpFileId);
    }

    /**
     * 新增保存shpFile信息
     *
     * @param shpFile shpFile信息
     * @return 结果
     */
    @Override
    public int insertShpFile(SysShpFile shpFile)
    {
        SysShpFile info = shpFileMapper.selectShpFileById(shpFile.getParentId());
        // 如果父节点不为"正常"状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus()))
        {
            throw new BusinessException("shpFile停用，不允许新增");
        }
        shpFile.setAncestors(info.getAncestors() + "," + shpFile.getParentId());
        shpFile.setShpFilePath(shpFile.getShpFilePath());
        return shpFileMapper.insertShpFile(shpFile);
    }

    /**
     * 修改保存shpFile信息
     *
     * @param shpFile shpFile信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateShpFile(SysShpFile shpFile)
    {
        SysShpFile newParentShpFile = shpFileMapper.selectShpFileById(shpFile.getParentId());
        SysShpFile oldShpFile = selectShpFileById(shpFile.getShpFileId());
        if (StringUtils.isNotNull(newParentShpFile) && StringUtils.isNotNull(oldShpFile))
        {
            if(shpFile.getShpFilePath().contains(".shp")){
                shpFile.setShpFilePath(shpFile.getShpFilePath());
            }else {
                shpFile.setShpFilePath(shpFile.getShpFilePath()+"/"+shpFile.getShpFileName());
            }
            String newAncestors = newParentShpFile.getAncestors() + "," + newParentShpFile.getShpFileId();
            String oldAncestors = oldShpFile.getAncestors();
            shpFile.setAncestors(newAncestors);
            updateShpFileChildren(shpFile.getShpFileId(), newAncestors, oldAncestors);
        }
        int result = shpFileMapper.updateShpFile(shpFile);
        if (UserConstants.DEPT_NORMAL.equals(shpFile.getStatus()))
        {
            // 如果该shpFile是启用状态，则启用该shpFile的所有上级shpFile
            updateParentShpFileStatus(shpFile);
        }
        return result;
    }

    /**
     * 修改该shpFile的父级shpFile状态
     *
     * @param shpFile 当前shpFile
     */
    private void updateParentShpFileStatus(SysShpFile shpFile)
    {
        String updateBy = shpFile.getUpdateBy();
        shpFile = shpFileMapper.selectShpFileById(shpFile.getShpFileId());
        shpFile.setUpdateBy(updateBy);
        shpFileMapper.updateShpFileStatus(shpFile);
    }

    /**
     * 修改子元素关系
     *
     * @param shpFileId 被修改的shpFileID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateShpFileChildren(Long shpFileId, String newAncestors, String oldAncestors)
    {
        List<SysShpFile> children = shpFileMapper.selectChildrenShpFileById(shpFileId);
        for (SysShpFile child : children)
        {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            shpFileMapper.updateShpFileChildren(children);
        }
    }

    /**
     * 根据shpFileID查询信息
     *
     * @param shpFileId shpFileID
     * @return shpFile信息
     */
    @Override
    public SysShpFile selectShpFileById(Long shpFileId)
    {
        return shpFileMapper.selectShpFileById(shpFileId);
    }

    /**
     * 校验shpFile名称是否唯一
     *
     * @param shpFile shpFile信息
     * @return 结果
     */
    @Override
    public String checkShpFileNameUnique(SysShpFile shpFile)
    {
        Long shpFileId = StringUtils.isNull(shpFile.getShpFileId()) ? -1L : shpFile.getShpFileId();
        SysShpFile info = shpFileMapper.checkShpFileNameUnique(shpFile.getShpFileName(), shpFile.getParentId());
        if (StringUtils.isNotNull(info) && info.getShpFileId().longValue() != shpFileId.longValue())
        {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    @Override
    public int updateSysShpFileByIds(String[] ids) {
        return shpFileMapper.updateSysShpFileByIds(ids);
    }
}
