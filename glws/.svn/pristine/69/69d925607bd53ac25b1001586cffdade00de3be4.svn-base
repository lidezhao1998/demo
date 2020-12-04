package com.ruoyi.framework.util;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.security.Md5Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sunlei
 * @description 文件上传
 * @date 2020/09/22/11:14
 */
public class UpFile {
    public static Map<String,String> uploadFile(MultipartFile[] files){
        String deposeFilesDir = Global.getProfile();
        HashMap<String, String> stringHashMap = new HashMap<>();
        // 获取附件原名(有的浏览器如chrome获取到的是最基本的含 后缀的文件名,如myImage.png)
        String fileName = files[0].getOriginalFilename();
        String[] fileNameSplitArray = fileName.split("\\.");
        String newFileName=Md5Utils.encrypt16(fileNameSplitArray[0])+ (int) (Math.random() * 100000);


        // 获取附件原名(有的浏览器如ie获取到的是含整个路径的含后缀的文件名，如C:\\Users\\images\\myImage.png)
        // 如果是获取的含有路径的文件名，那么截取掉多余的，只剩下文件名和后缀名
        File dir = new File(deposeFilesDir+"/"+newFileName);
        if (!dir.isDirectory()){
            dir.mkdirs();
        }
        for (MultipartFile file : files) {
            fileName=  newFileName+"."+ file.getOriginalFilename().split("\\.")[1];

            // 当文件无后缀名时(如C盘下的hosts文件就没有后缀名)
            if (fileName.indexOf(".") < 0) {
                // 加上random戳,防止附件重名覆盖原文件
                fileName = fileName + (int) (Math.random() * 100000);
            }
            System.out.println("fileName:" + fileName);
            // 根据文件的全路径名字(含路径、后缀),new一个File对象dest

            File dest = new File(deposeFilesDir+"/"+newFileName+"/"+ fileName);
            // 如果该文件的上级文件夹不存在，则创建该文件的上级文件夹及其祖辈级文件夹;
            try {
                // 将获取到的附件file,transferTo写入到指定的位置(即:创建dest时，指定的路径)
                file.transferTo(dest);
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//            if (fileName.endsWith(".shp")){
//                //文件打成zip
//                FileUtils.fileToZip(deposeFilesDir+"/"+newFileName+"/",deposeFilesDir+"/"+newFileName+"/",newFileName);
//            }
        }
        stringHashMap.put("filePath",deposeFilesDir+"/"+newFileName);
        stringHashMap.put("fileName",newFileName);
        return  stringHashMap;
    }
}
