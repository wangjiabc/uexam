package com.alvis.exam.controller.admin;

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
import net.sf.json.JSONObject;
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
     * @param
     */
    @RequestMapping("updateArticle")
    public RestResponse updateArticle(@RequestBody Article article) {
        articleService.updateArticle(article);
        return RestResponse.ok();
    }

    /**
     * 自定义上传图片:一张图片存一个地址
     * @param
     */
    @RequestMapping(value = "uploadImages")
    public void uploadImages(MultipartFile[] file) {
        //清除数据库以前的图片
        viewPagerService.trunCate();
        //删除文件夹下所有文件


        int count = 0;
        for (MultipartFile multipartFile : file) {
            String fileNameNew = null;
            if(multipartFile != null){
                //自定义上传位置
                Map<String, String> map = UploadUtils.upload(multipartFile,"D:/manage-upload/images/wx/");
                fileNameNew = map.get("fileNameNew");
            }
            else {
                fileNameNew = "http://192.168.100.185:8091/images/wx/aaa.png";
            }
            ViewPager viewPager = new ViewPager();
            viewPager.setName("轮播图_"+ (count++));
            viewPager.setUploadTime(new Date());
            viewPager.setAddress(fileNameNew);
            viewPagerService.insert(viewPager);  //插入数据
        }
    }

    /**
     * 删除上传图片:单个删除
     * @param
     */
    @RequestMapping(value = "delImages")
    public void delImages(String file) {
        File file1 = new File("D:/manage-upload/images/wx/" + file);
        file1.delete();
    }

    /**
     * 删除上传图片:全部删除
     * @param
     */
    @RequestMapping(value = "delAllImages")
    public void delAllImages(String[] file) {
        for (String s : file) {
            File file1 = new File("D:/manage-upload/images/wx/" + s);
            file1.delete();
        }
    }



    /**
     * 展示上传图片
     * @param
     */
    @RequestMapping(value = "showImages")
    public List<String>  showImages() {
        List<ViewPager> viewPagers = viewPagerService.findAll();
        List<String> arrayList = new ArrayList<>();
        for (ViewPager viewPager : viewPagers) {
            String address = viewPager.getAddress();
            arrayList.add(address);
        }
        return arrayList;
    }

    /**
     * 设为首图
     * @param
     */
    @RequestMapping(value = "setPopImages")
    public List<String>  setPopImages(String imagesAddress) {
        List<ViewPager> viewPagers = viewPagerService.findAll();
        List<String> arrayList = new ArrayList<>();
        for (ViewPager viewPager : viewPagers) {
            String address = viewPager.getAddress();
            if(address.equals(imagesAddress)){
                arrayList.add(0,address);
            }
            else {
                arrayList.add(address);
            }
        }

        return arrayList;
    }
}
