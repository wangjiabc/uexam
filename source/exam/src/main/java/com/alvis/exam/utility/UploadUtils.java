package com.alvis.exam.utility;

import com.alvis.exam.base.RestResponse;
import com.alvis.exam.configuration.property.QnConfig;
import com.alvis.exam.configuration.property.UrlConfig;
import com.alvis.exam.domain.ArticleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 图片上传
 * @return 图片路径+原名称
 */
public class UploadUtils {


    public static Map<String,String> upload(MultipartFile file,String url) {
        if(file == null){
            HashMap<String, String> map = new HashMap<>();
            map.put("fileNameNew","aaa.png");
            return map;
        }
        ArticleType articleType = new ArticleType();
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String fileNameNew = UUID.randomUUID() + suffixName;
        // 图片存储地址，例如"E:/imagesServer/"
        String parent = "D:/manage-upload/images/";
        String imgName = "";
        try {
            File targetFile = new File(parent, fileNameNew);
            // 创建文件夹
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            // 将上传文件存储到服务器中
            file.transferTo(targetFile);
            // 背景图片地址
            imgName = targetFile.getName();
            // 图片显示地址，例如"http://localhost:8080/imgFiles/" + imgName
            imgName = parent + imgName;
//            System.out.println(imgName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("上传的文件的原名称:" + fileName);
//        String url = "http://192.168.100.185:8091/images/";
        String sqlSaveUrl = "url/images/" + fileNameNew;   //数据库存储地址
        articleType.setPathDeposit(sqlSaveUrl);
        System.out.println("本地图片的磁盘地址:" + imgName);
        System.out.println("服务器图片磁盘地址:" + url+fileNameNew);

        HashMap<String, String> map = new HashMap<>();
        map.put("fileName",fileName);
        map.put("sqlSaveUrl",sqlSaveUrl);
        map.put("fileNameNew",fileNameNew);
        map.put("url",url + fileNameNew);
        return map;
    }

    /**
     * wx自定义上传接口
     * @param file
     * @param address   文件存放磁盘地址
     * @return
     */
    public static Map<String,String> upload(MultipartFile file,String address,String url) {
        if(file == null){
            HashMap<String, String> map = new HashMap<>();
//            String url = "http://223.86.150.188:8091/images/wx/";
//            String url = "http://192.168.100.185:8091/images/wx/";
            map.put("fileNameNew",url + "wx/aaa.png");
            return map;
        }
        ArticleType articleType = new ArticleType();
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String fileNameNew = UUID.randomUUID() + suffixName;
        // 图片存储地址，例如"E:/imagesServer/"
        String parent = address;
        String imgName = "";
        try {
            File targetFile = new File(parent, fileNameNew);
            // 创建文件夹
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            // 将上传文件存储到服务器中
            file.transferTo(targetFile);
            // 背景图片地址
            imgName = targetFile.getName();
            // 图片显示地址，例如"http://localhost:8080/imgFiles/" + imgName
            imgName = parent + imgName;
//            System.out.println(imgName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("上传的文件的原名称:" + fileName);
//        String url = "http://223.86.150.188:8091/images/wx/";
//        String url = "http://192.168.100185:8091/images/wx/";
        String sqlSaveUrl = "url/images/" + fileNameNew;   //数据库存储地址
        articleType.setPathDeposit(sqlSaveUrl);
        System.out.println("本地图片的磁盘地址:" + imgName);
        System.out.println("服务器图片磁盘地址:" + url+fileNameNew);

        HashMap<String, String> map = new HashMap<>();
        map.put("fileName",fileName);
        map.put("sqlSaveUrl",sqlSaveUrl);
        map.put("fileNameNew",fileNameNew);
        map.put("url",url+fileNameNew);
        return map;
    }

}
