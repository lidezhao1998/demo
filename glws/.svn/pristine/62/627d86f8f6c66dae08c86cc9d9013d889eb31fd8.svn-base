package com.ruoyi.zaihai.common.domain;

import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.ServletUtils;


/**
 * 附件对象 c_resource
 * 
 * @author sudongdong
 * @date 2020-05-28
 */
public class InitialPreviewConfig extends BaseEntity
{


    private static final long serialVersionUID = 1L;


    private String caption;


    private Long size;


    private  final String  url=ServerConfig.getDomain(ServletUtils.getRequest())+"system/survey/delete";

    private Long key;





    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
