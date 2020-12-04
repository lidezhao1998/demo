package com.sinosoft.web.externalInterface;

import com.ruoyi.common.core.controller.BaseController;
import com.sinosoft.externalInterface.domain.GlmaGisFeatures;
import com.sinosoft.externalInterface.domain.GlmaGisMap;
import com.sinosoft.externalInterface.service.GlmaGisFeaturesServic;
import com.sinosoft.externalInterface.service.GlmaGisMapServic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunlei
 * @description 接收共享专题树数据
 * @date 2020/11/16/9:47
 */
@RestController
@RequestMapping("/api")
public class SharedProjectController extends BaseController {
    @Autowired
    GlmaGisFeaturesServic gisFeaturesServic;
    @Autowired
    GlmaGisMapServic glmaGisMapServic;
    /**
     *功能描述  工程、虫害、评价系统推送的专题树
     * @author sunlei
     * @date 2020/11/16
     * @param
     * @return [glmaGisFeatures]
     */
    @PostMapping("/addSharedProjectTree")
    public String addSharedProjectTree(GlmaGisFeatures glmaGisFeatures) {
        return gisFeaturesServic.addSharedProjectTree(glmaGisFeatures);
    }
    /**
     *功能描述  工程、虫害、评价系统推送的专题数据
     * @author sunlei
     * @date 2020/11/16
     * @param
     * @return [gisMap]
     */
    @PostMapping("/addGisMap")
    public String addSharedGisMap(GlmaGisMap gisMap) {
        return glmaGisMapServic.addSharedGisMap(gisMap);
    }
    /**
     *功能描述  工程、虫害、评价系统推送的专题数据共享到工程、虫害、评价系统
     * @author sunlei
     * @date 2020/11/16
     * @param
     * @return
     */
    @PostMapping("/forwardingShared")
    public String forwardingSharedThematicData(String sharedDataId){
        return glmaGisMapServic.getGisMapByID(sharedDataId);
    }

}
