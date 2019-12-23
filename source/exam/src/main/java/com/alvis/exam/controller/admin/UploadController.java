package com.alvis.exam.controller.admin;

import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.configuration.property.UrlConfig;
import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.service.UploadService;
import com.alvis.exam.utility.UploadUtils;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@CrossOrigin
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/admin/upload")
@RestController("AdminUploadController")
public class UploadController extends BaseApiController {

    @Autowired
    private UploadService uploadService;

    /**
     * 存储上传文章
     */
    @RequestMapping("save")
    public void save(@RequestBody Article article) {
        List<ArticleType> lists = uploadService.findArticleType();
        int typeId = 0; //接收typeId
        for (ArticleType articleType : lists) {
            String typeName = articleType.getTypeName();
            if (typeName.equals(article.getTitle())) {
                typeId = articleType.getId();
            }
        }
        article.setTypeId(typeId);
        uploadService.saveArticle(article);
    }

    /**
     * 返回文章信息
     *
     * @param
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<Article>> pageList(@RequestBody MessagePageRequestVM model) {
        PageInfo<Article> pageInfo = uploadService.page(model);
        return RestResponse.ok(pageInfo);
    }

    /**
     * 返回文章分类
     *
     * @param
     */
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public RestResponse<PageInfo<ArticleType>> typeList(@RequestBody MessagePageRequestVM model) {
        PageInfo<ArticleType> pageInfo = uploadService.pageList(model);
        List<ArticleType> list = pageInfo.getList();
        for (ArticleType articleType : list) {
            String origname = articleType.getOrigname();
            articleType.setPathDeposit("http://192.168.100.:8091/images/" + origname);
        }
        return RestResponse.ok(pageInfo);
    }

    @Autowired
    private UrlConfig urlConfig;

    /**
     * 修改分类
     * @param file
     * @return 图片路径
     */
    @RequestMapping("updateType")
    public RestResponse updateType(MultipartFile file, String type, int id) {

        //file为空的时候
        if (file == null) {
                ArticleType articleType = new ArticleType();
                articleType.setId(id);
                articleType.setTypeName(type);
                uploadService.updateArticleType(articleType);
        }
        else {
            ArticleType articleType = new ArticleType();
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            fileName = UUID.randomUUID() + suffixName;
            // 图片存储地址，例如"E:/imagesServer/"
            String parent = "D:/manage-upload/images/";
            String imgName = "";
            try {
                File targetFile = new File(parent, fileName);
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
                System.out.println(imgName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("上传的文件原名称:" + fileName);
            //存名称
            articleType.setOrigname(fileName);
            //file:///D:/manage-upload/static/fdc65ea0-dbe1-4ced-983f-55e14778d2dc.png
//            String url = "http://223.86.150.188:8091/images/";
            String url = urlConfig.getUrl();
            articleType.setPathDeposit("url/images/" + fileName);
            articleType.setTypeName(type);
            articleType.setId(id);
            System.out.println(url + fileName);
            uploadService.updateType(articleType);
            System.out.println("图片修改成功:" + imgName);
        }
        return RestResponse.ok();
    }



    /**
     * 图片传到服务器，添加分类
     *
     * @param file
     * @return 图片路径
     */
    @RequestMapping("saveType")
    public RestResponse saveType(MultipartFile file, String type) {
        ArticleType articleType = new ArticleType();

        //图片上传
        Map<String, String> stringMap = UploadUtils.upload(file,urlConfig.getUrl());
//        String fileName = stringMap.get("fileName");    //图片原名称
        String sqlSaveUrl = stringMap.get("sqlSaveUrl");    //图片访问地址
        String fileNameNew = stringMap.get("fileNameNew");

        //存名称
        articleType.setOrigname(fileNameNew);
//        String url = "http://223.86.150.188:8091/images/";
        articleType.setPathDeposit(sqlSaveUrl);
        articleType.setTypeName(type);
        articleType.setState(1);
        uploadService.saveArticleType(articleType);
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
        uploadService.deleteType(articleType);
        return RestResponse.ok();
    }

    /**
     * 删除文章
     * @param
     * @return 返回状态码
     */
    @RequestMapping("deleteArticle")
    public RestResponse deleteArticle(@RequestBody Article article) {
        uploadService.deleteArticle(article);
        return RestResponse.ok();
    }





}
