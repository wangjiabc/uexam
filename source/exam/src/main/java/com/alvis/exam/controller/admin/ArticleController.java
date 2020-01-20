package com.alvis.exam.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.configuration.property.UrlConfig;
import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.domain.Chapter;
import com.alvis.exam.domain.ViewPager;
import com.alvis.exam.domain.dto.KvobjDTO;
import com.alvis.exam.service.ArticleService;
import com.alvis.exam.service.ArticleTypeService;
import com.alvis.exam.service.ChapterService;
import com.alvis.exam.service.ViewPagerService;
import com.alvis.exam.utility.UploadUtils;
import com.alvis.exam.viewmodel.admin.article.ArticleVM;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.alvis.exam.viewmodel.wx.student.KeyObjDTO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/admin/article")
@RestController("ArticleController")
@Api(value = "文章controller")
public class ArticleController {


    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleTypeService articleTypeService;
    @Resource
    private ViewPagerService viewPagerService;
    @Autowired
    private UrlConfig urlConfig;
    @Resource
    private ChapterService chapterService;


    /**
     * 返回文章分类+章节
     *
     * @param
     */
    @ApiOperation(value = "返回文章分类+章节")
    @RequestMapping(value = "typeList")
    public RestResponse typeList() {
//        List<String> arrayList = new ArrayList<>();
//        List<ArticleType> articleTypes = articleTypeService.findArticleType();
//        for (ArticleType articleType : articleTypes) {
//            String typeName = articleType.getTypeName();
//            arrayList.add(typeName);
//        }
        List<Object> arrayList = new ArrayList<>();
        List<ArticleType> articleTypes = articleTypeService.findArticleType();
        for (ArticleType articleType : articleTypes) {

            String typeName = articleType.getTypeName();
            Integer typeId = articleType.getId();
            List<Chapter> chapters = articleTypeService.findAllByTypeId(typeId);
            KvobjDTO k1 = new KvobjDTO();
            k1.setValue(typeId);
            k1.setLabel(typeName);
            List<Object> arrayList1 = new ArrayList<>();
            for (Chapter chapter : chapters) {
                Integer id = chapter.getId();
                String name = chapter.getName();
                KvobjDTO k2 = new KvobjDTO();
                k2.setValue(id);
                k2.setLabel(name);
                arrayList1.add(k2);
            }
            k1.setChildren(arrayList1);
            arrayList.add(k1);
        }
        return RestResponse.ok(arrayList);
    }


    /**
     * 存储上传文章
     *
     * @param jsonObject
     */
    @RequestMapping("saveArticle")
    public RestResponse save(@RequestBody JSONObject jsonObject) {
//        List<Integer> list  = articleService.findList();
//        if(list != null && list.size() > 0 ){
//            for (Integer integer : list) {
//                if(integer == null){
//                    return RestResponse.fail(500, "请添加与之前文章相对应的试卷");
//                }
//            }
//        }
        Article article = new ArticleVM();
        String name = jsonObject.getString("title");            //文章标题
        Article byName = articleService.findByName(name);
        if (byName != null) {
            return RestResponse.fail(500, "系统内部错误");
        } else {
            String author = jsonObject.getString("author");         //文章作者
            String doc = jsonObject.getString("content");           //文章内容
            String a = jsonObject.getString("readTimeL");           //下限时长
            int down = Integer.parseInt(a);
            JSONArray arr = jsonObject.getJSONArray("typeName");    //文章类型+章节的id
            String b = jsonObject.getString("maxIntegral");           //文章积分
            int count = Integer.parseInt(b);

            String plainText = jsonObject.getString("plainText");            //存储纯文本
            article.setPlainText(plainText);

            article.setTitle(name);
            article.setAuthor(author);
            article.setContent(doc);
            article.setReadTimeL(down*60);
            article.setMaxIntegral(count);
            article.setState(1);

            int size = arr.size();
            if (size == 1) {
                article.setTypeId((Integer) arr.get(0));
            }
            if (size == 2) {
                article.setTypeId((Integer) arr.get(0));
                article.setChapterId((Integer) arr.get(1));
//                Article exit = articleService.findIsExitByChapterId((Integer) arr.get(1)); //文章章节只能被使用一次
//                if (exit != null) {
//                    return RestResponse.fail(500, "该章节目录已使用");
//                }
//                else {
//                    articleService.saveArticle(article);
//                    return RestResponse.ok();
//                }
            }
//            else {
//                return RestResponse.fail(500, "文章应对应相应章节");
//            }
            articleService.saveArticle(article);
            return RestResponse.ok();
        }
    }

    /**
     * 编辑文章
     *
     * @param
     */
    @RequestMapping("updateArticle")
    public RestResponse updateArticle(@RequestBody JSONObject jsonObject) {
        Article article = new ArticleVM();
        Integer id = jsonObject.getInteger("id");
        String name = jsonObject.getString("title");            //文章标题
        String author = jsonObject.getString("author");         //文章作者
        String doc = jsonObject.getString("content");           //文章内容
        String a = jsonObject.getString("readTimeL");           //下限时长
        int down = Integer.parseInt(a);
        JSONArray arr = jsonObject.getJSONArray("typeName");    //文章类型+章节的id
        String b = jsonObject.getString("maxIntegral");           //文章积分
        int count = Integer.parseInt(b);

        String plainText = jsonObject.getString("plainText");            //存储纯文本
        article.setPlainText(plainText);

        article.setId(id);
        article.setTitle(name);
        article.setAuthor(author);
        article.setContent(doc);
        article.setReadTimeL(down*60);
        article.setMaxIntegral(count);
        article.setState(1);

        int size = arr.size();
        if (size == 1) {
            article.setTypeId((Integer) arr.get(0));
        }
        if (size == 2) {
            article.setTypeId((Integer) arr.get(0));
            article.setChapterId((Integer) arr.get(1));
        }
        articleService.updateArticle(article);
        return RestResponse.ok();

    }

    /**
     * 展示文章信息
     *
     * @param
     */
    @ApiOperation(value = "展示文章信息")
    @RequestMapping(value = "articleList")
    public RestResponse<PageInfo<Article>> pageList(@RequestBody MessagePageRequestVM model) {
        PageInfo<Article> pageInfo = articleService.page(model);
        for (Article article : pageInfo.getList()) {
            Integer typeId = article.getTypeId();
            List<Integer> arrayList = new ArrayList<>();
            arrayList.add(typeId);
            arrayList.add(article.getChapterId());
            article.setTypeName(arrayList);
            //秒变成分
            Integer readTimeL = article.getReadTimeL();
            article.setReadTimeL(readTimeL/60);

            ArticleType articleType = articleTypeService.findByTypeId(typeId);
            Chapter chapter = chapterService.findChapterById(article.getChapterId());
            if(articleType != null){
                article.setArticleTypeName(articleType.getTypeName());
            }
            if(chapter != null){
                article.setChapterName(chapter.getName());
            }

        }
        return RestResponse.ok(pageInfo);
    }

    /**
     * 删除文章
     *
     * @param article state 隐藏为0 显示为1  从前端传过来的
     * @return 返回状态码
     */
    @RequestMapping("deleteArticle")
    public RestResponse deleteArticle(@RequestBody Article article) {
        articleService.deleteArticle(article);
        return RestResponse.ok();
    }


    /**
     * 自定义上传图片:一张图片存一个地址
     *
     * @param
     */
    @RequestMapping(value = "uploadImages")
    public RestResponse uploadImages(MultipartFile file) {

        //查询所有取size
        int size = viewPagerService.findAll().size();

        String fileNameNew = null;
        if (file != null) {
            //自定义上传位置
            Map<String, String> map = UploadUtils.upload(file, "D:/manage-upload/images/wx/", urlConfig.getUrl());
            fileNameNew = map.get("fileNameNew");
        }
        ViewPager viewPager = new ViewPager();
        viewPager.setName("轮播图_" + (size + 1));
        viewPager.setUploadTime(new Date());
        viewPager.setAddress(fileNameNew);
        viewPagerService.insert(viewPager);  //插入数据

        return RestResponse.ok();
    }


    /**
     * 删除上传图片:单个删除
     *
     * @param
     */
    @RequestMapping(value = "delImages")
    public RestResponse delImages(@RequestBody ViewPager viewPager) {
        String url = viewPager.getAddress();
        for (ViewPager viewPager1 : viewPagerService.findAll()) {
            String address = viewPager1.getAddress();
            String s = address;
//            viewPager1.setAddress("http://223.86.150.188:8091/images/wx/" + address);
            viewPager1.setAddress(urlConfig.getUrl() + "wx/" + address);
            String address1 = viewPager1.getAddress();
            if (address1.equals(url)) {
                Integer id = viewPager1.getId();
                viewPagerService.deleteImages(id);  //数据库删除数据
                new File("D:/manage-upload/images/wx/" + s).delete();//删除本地文件
            }
        }
        return RestResponse.ok();
    }

    /**
     * 展示上传图片
     *
     * @param
     */
    @RequestMapping(value = "showImages")
    public List<ViewPager> showImages() {
        List<ViewPager> viewPagers = viewPagerService.findAll();
        List<ViewPager> arrayList = new ArrayList<>();
        for (ViewPager viewPager : viewPagers) {
            String address = viewPager.getAddress();
            viewPager.setAddress(urlConfig.getUrl() + "wx/" + address);
            arrayList.add(0, viewPager);
        }

        return arrayList;
    }

    /**
     * 设为首图
     *
     * @param
     */
    @RequestMapping(value = "setPopImages")
    public RestResponse setPopImages(@RequestBody ViewPager viewPager1) {
        String url = viewPager1.getAddress();
        List<ViewPager> viewPagers = viewPagerService.findAll();
        ViewPager viewPagerFirst = new ViewPager();
        for (ViewPager viewPager : viewPagers) {
            String address = viewPager.getAddress();
            Integer id = viewPager.getId();
            viewPager.setAddress(urlConfig.getUrl() + "wx/" + address);
            String address1 = viewPager.getAddress();
            if (address1.equals(url)) {
                viewPagerService.deleteImages(id);
                viewPagerFirst = viewPager;
                viewPagerFirst.setAddress(address);
                viewPagerFirst.setUploadTime(new Date());
                viewPagerFirst.setId(null);
            }
        }
        viewPagerService.insert(viewPagerFirst);
        return RestResponse.ok();
    }


}
