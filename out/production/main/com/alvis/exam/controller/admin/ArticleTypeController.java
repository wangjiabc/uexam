package com.alvis.exam.controller.admin;


import com.alvis.exam.base.RestResponse;
import com.alvis.exam.configuration.property.SystemConfig;
import com.alvis.exam.configuration.property.UrlConfig;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.service.ArticleTypeService;
import com.alvis.exam.utility.UploadUtils;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/admin/articleType")
@RestController("ArticleTypeController")
public class ArticleTypeController {

    @Resource
    private ArticleTypeService articleTypeService;
    @Autowired
    private UrlConfig urlConfig;

    /**
     * 展示文章分类
     *
     * @param
     */
    @RequestMapping(value = "/typeList")
    public RestResponse<PageInfo<ArticleType>> typeList(@RequestBody MessagePageRequestVM model) {
        PageInfo<ArticleType> pageInfo = articleTypeService.pageList(model);
        List<ArticleType> list = pageInfo.getList();
        for (ArticleType articleType : list) {
            String origname = articleType.getOrigname();
//            articleType.setPathDeposit("http://223.86.150.188:8091/images/" + origname);
            articleType.setPathDeposit(urlConfig.getUrl() + origname);
        }
        return RestResponse.ok(pageInfo);
    }

    /**
     * 修改分类
     * @param file
     * @return 图片路径
     */
    @RequestMapping("updateType")
    public RestResponse updateType(MultipartFile file, String type, int id) {
        ArticleType articleType = new ArticleType();
        //file为空的时候
        if (file == null) {
            articleType.setId(id);
            articleType.setTypeName(type);
            articleTypeService.updateArticleType(articleType);
        }
        else {
            //图片上传
            Map<String, String> stringMap = UploadUtils.upload(file,urlConfig.getUrl());
            String fileNameNew = stringMap.get("fileNameNew");    //图片名称
            String sqlSaveUrl = stringMap.get("sqlSaveUrl");    //图片访问地址
            //存名称
            System.out.println("上传的文件原名称:" + fileNameNew);
            articleType.setOrigname(fileNameNew);
//            String url = "http://127.0.0.1:8091/images/";
            articleType.setPathDeposit(sqlSaveUrl);
            articleType.setTypeName(type);
            articleType.setId(id);
//            System.out.println(url + fileName);
            articleTypeService.updateType(articleType);
            System.out.println("图片修改成功:" + sqlSaveUrl);
        }
        return RestResponse.ok();
    }

    /**
     * 添加分类，图片存到磁盘
     * @param file
     * @param type  分类名字
     * @return
     */
    @RequestMapping("saveType")
    public RestResponse saveType(MultipartFile file, String type) {
        ArticleType name = articleTypeService.findByTypeName(type);
        if(name != null){
            return RestResponse.fail(500, "系统内部错误");
        }
        ArticleType articleType = new ArticleType();
        //图片上传
        Map<String, String> stringMap = UploadUtils.upload(file,urlConfig.getUrl());
        String fileNameNew = stringMap.get("fileNameNew");    //图片原名称
        String sqlSaveUrl = stringMap.get("sqlSaveUrl");    //图片访问地址

        //存名称
        articleType.setOrigname(fileNameNew);
//        String url = "http://127.0.0.1:8091/images/";
        articleType.setPathDeposit(sqlSaveUrl);
        articleType.setTypeName(type);
        articleType.setState(1);
//        System.out.println(url + fileName);
        articleTypeService.saveArticleType(articleType);
        System.out.println("图片上传成功:" + sqlSaveUrl);
        return RestResponse.ok();
    }

    /**
     * 删除文章分类
     * @param
     * @return 返回状态码
     */
    @RequestMapping("deleteType")
    public RestResponse deleteType(@RequestBody ArticleType articleType) {
        articleTypeService.deleteType(articleType);
//        String ip = systemConfig.getUrl().getIp();
//        System.out.println(ip);
        return RestResponse.ok();
    }

}
