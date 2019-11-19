package com.alvis.exam.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.domain.ViewPager;
import com.alvis.exam.repository.ViewPagerMapper;
import com.alvis.exam.service.ArticleService;
import com.alvis.exam.service.ArticleTypeService;
import com.alvis.exam.service.ViewPagerService;
import com.alvis.exam.utility.UploadUtils;
import com.alvis.exam.viewmodel.admin.article.ArticleVM;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RequestMapping("/api/admin/article")
@RestController("ArticleController")
public class ArticleController {


    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleTypeService articleTypeService;
    @Resource
    private ViewPagerService viewPagerService;

    /**
     * 返回文章分类
     *
     * @param
     */
    @RequestMapping(value = "typeList")
    public List<String> typeList() {
        List<String> arrayList = new ArrayList<>();
        List<ArticleType> articleTypes = articleTypeService.findArticleType();
        for (ArticleType articleType : articleTypes) {
            String typeName = articleType.getTypeName();
            arrayList.add(typeName);
        }
        return arrayList;
    }


    /**
     * 存储上传文章
     *
     * @param jsonObject
     */
    @RequestMapping("saveArticle")
    public void save(@RequestBody JSONObject jsonObject) {
        List<ArticleType> lists = articleService.findArticleType();
        ArticleVM articleVM = new ArticleVM();
        String name = jsonObject.getString("title");     //文章标题
        String author = jsonObject.getString("author"); //文章作者
        String doc = jsonObject.getString("content");       //文章内容
        String a = jsonObject.getString("readTimeL");     //下限时长
        String type = jsonObject.getString("selectType");   //文章类型
        int down = Integer.parseInt(a);

        String b = jsonObject.getString("readTimeU");   //文章积分
        int count = Integer.parseInt(b);

        articleVM.setTitle(name);
        articleVM.setAuthor(author);
        articleVM.setContent(doc);
        articleVM.setReadTimeL(down);
        articleVM.setReadTimeU(count);
        articleVM.setState(1);

        int typeId = 0; //接收typeId
        for (ArticleType articleType : lists) {
            String typeName = articleType.getTypeName();
            if (typeName.equals(type)) {
                Integer id = articleType.getId();
                typeId = id;
            }
        }
        articleVM.setTypeId(typeId);
        articleService.saveArticle(articleVM);
    }

    /**
     * 展示文章信息
     *
     * @param
     */
    @RequestMapping(value = "articleList", method = RequestMethod.POST)
    public RestResponse<PageInfo<Article>> pageList(@RequestBody MessagePageRequestVM model) {
        PageInfo<Article> pageInfo = articleService.page(model);
        return RestResponse.ok(pageInfo);
    }

    /**
     * 删除文章
     *
     * @param
     * @return 返回状态码
     */
    @RequestMapping("deleteArticle")
    public RestResponse deleteArticle(@RequestBody Article article) {
        articleService.deleteArticle(article);
        return RestResponse.ok();
    }


    /**
     * 编辑文章
     *
     * @param
     */
    @RequestMapping("updateArticle")
    public RestResponse updateArticle(@RequestBody Article article) {
        articleService.updateArticle(article);
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
            Map<String, String> map = UploadUtils.upload(file, "D:/manage-upload/images/wx/");
            fileNameNew = map.get("fileNameNew");
        }
        ViewPager viewPager = new ViewPager();
        viewPager.setName("轮播图_"+(size+1));
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
            viewPager1.setAddress("http://192.168.100.185:8091/images/wx/" + address);
            String address1 = viewPager1.getAddress();
            if(address1.equals(url)){
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
            viewPager.setAddress("http://192.168.100.185:8091/images/wx/" + address);
            arrayList.add(0,viewPager);
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
            viewPager.setAddress("http://192.168.100.185:8091/images/wx/" + address);
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
