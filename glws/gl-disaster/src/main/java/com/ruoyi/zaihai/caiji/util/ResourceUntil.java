package com.ruoyi.zaihai.caiji.util;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.zaihai.common.domain.CResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 资源处理公用方法
 * @author liuhongfei
 * @createTime 2020/5/28
 */
public class ResourceUntil {

    /**
     * 资源附件下载
     * @param cResource
     * @param request
     * @param response
     * @throws Exception
     */
    public static void download(CResource cResource , HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        if(cResource == null){
            return;
        }
        // 本地资源路径
        String localPath = Global.getProfile();
        //数据库资源地址
        String optPath = cResource.getUrl();
        optPath=optPath.replace("/profile","");
        // 数据库资源地址
        String downloadPath = localPath + optPath;
        // 下载名称
        String downloadName = cResource.getFilename();
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }

    /**
     * 批量删除附件
     * @param paths
     */
    public static void deleteLpdFilesByIds(List<String> paths){
        String rootPath = Global.getProfile();
        try {
            for (String path : paths) {
                FileUtils.deleteFile(rootPath+"/"+path);//删除附件
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
