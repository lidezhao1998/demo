package com.ruoyi.zaihai.common.utils;


import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.zaihai.common.domain.CResource;
import com.ruoyi.zaihai.common.service.ICResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UploadFile {

    @Autowired
    private  ServerConfig serverConfig;

    @Autowired
    private ICResourceService CResourceService;

    public  String  upload(MultipartFile[] files, String name,int sum, AjaxResult ajax) {
        String CRids="";
        int sum1=0;//图片上传完添加图片id结果
        if(files!=null&&files.length>0){
        for (int i = 0; i < files.length; i++) {
            sum=sum+1;
            MultipartFile file=files[i];
            String fileUrl=null;
            try
            {
                Long len = file.getSize();
                String realFileName = file.getOriginalFilename();
                int index=realFileName.lastIndexOf(".");
                if(StringUtils.isNotEmpty(name)){
                    realFileName=name+"-"+sum+realFileName.substring(index,realFileName.length()-1);
                }
                // 上传文件路径
                String filePath = Global.getUploadPath();
                System.out.println("一次"+1111);
                // 上传并返回新文件名称

                fileUrl = FileUploadUtils.upload(filePath, file);
                Integer ci=indexOfStr(fileUrl,"/",3);
                String fileNamenew=fileUrl.substring(ci);
                String wuUrl=filePath+fileNamenew;
                String url = serverConfig.getUrl() + fileUrl;
                CResource CResource=new CResource();
                CResource.setUrl(fileUrl);
                CResource.setFileurl(wuUrl);
                CResource.setSize(len);
                CResource.setFilename(realFileName);
                int sumCr=CResourceService.insertCResource(CResource);
                if(sumCr<=0){
                    //补充删除已上传照片，

                    ajax=AjaxResult.error("保存图片失败");
                }else{
                    if(i==0){
                        CRids=CRids+(Long.toString(CResource.getId()));
                    }else{
                        CRids=CRids+","+(Long.toString(CResource.getId()));

                    }


                }



            }
            catch (Exception e)
            {
                 AjaxResult.error(e.getMessage());
            }finally {
                //图片上传和保存异常删除异常图片
                if(fileUrl!=null){
                    //删除逻辑

                }


            }
        }


        }
        return CRids;
    }

    public  Integer indexOfStr(String str,String findStr, int index){
        Pattern pattern = Pattern.compile(findStr);
        Matcher findMatcher = pattern.matcher(str);
        int number = 0;
        while(findMatcher.find()) {
            number++;
            if(number == index){
                break;
            }
        }
        try {
            return findMatcher.start();
        } catch (Exception e) {
            return null;
        }
    }
}
