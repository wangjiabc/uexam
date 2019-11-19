package com.alvis.exam.controller.wx.student;

import com.alvis.exam.base.RestResponse;
import com.alvis.exam.controller.wx.BaseWXApiController;
import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ReadState;
import com.alvis.exam.domain.User;
import com.alvis.exam.domain.ViewPager;
import com.alvis.exam.domain.dto.article.ArticleDTO;
import com.alvis.exam.service.ArticleService;
import com.alvis.exam.service.ReadService;
import com.alvis.exam.service.UploadService;
import com.alvis.exam.service.ViewPagerService;
import com.alvis.exam.utility.DateTimeUtil;
import com.alvis.exam.utility.ListUtils;
import com.alvis.exam.utility.PageInfoHelper;
import com.alvis.exam.utility.UploadUtils;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/api/wx/student/article" )
@AllArgsConstructor
@ResponseBody
public class ArticleController extends BaseWXApiController {

    @Resource
    private ReadService readService;
    @Resource
    private ArticleService articleService;
    @Resource
    private ViewPagerService viewPagerService;

    /**
     * 返回文章列表
     * @param
     */
    @RequestMapping(value = "/article")
    public RestResponse<PageInfo<ArticleDTO>> pageList(Integer typeId,MessageRequestVM messageRequestVM) {
        //根据分类id查询该类型下所有的文章
        messageRequestVM.setReceiveUserId(getCurrentUser().getId());
        PageInfo<ArticleDTO> pageInfo = articleService.studentPage(typeId,messageRequestVM);
//        for (ArticleDTO articleDTO : pageInfo.getList()) {
//            String readState = articleDTO.getReadState();
//            if("已读".equals(readState)){
//                articleDTO.setReadState("1");
//            }
//            else if("在读".equals(readState)){
//                articleDTO.setReadState("2");
//            }
//            else if(readState == null){
//                articleDTO.setReadState("3");
//            }
//        }
        return RestResponse.ok(pageInfo);
    }

    /**
     * 返回文章详情
     * @param article  含文章id
     */
    @RequestMapping(value = "details" ,produces = {"application/json;charset=utf-8"})
    public Article pageList(Article article) {
        Integer id = article.getId();
        String details = articleService.findDetails(id);
        //拿到下限时长
        Article article1 = articleService.find(id);
        Integer lowerLimit = article1.getReadTimeL();    //下限时长
        //查询readState表中该用户读取这篇文章总时长
        User user = getCurrentUser();
        Integer useId = user.getId();  //得到useId
        //根据useId和articleId 查询该用户阅读该文章的最大时长
        int count = readService.findIntegrate(useId,id);

        Article article2 = new Article();
        article2.setContent(details);       //文章详情
        article2.setReadTimeU(lowerLimit*1000 - count);  //用户阅读该文章，已读时长
        article2.setReadTimeL(lowerLimit*1000);       //下限时长
        article2.setId(id);
        return article2;
    }



    /**
     * 结束文章阅读存储
     * @param article  含文章id
     * @param time     用户阅读时长
     */
    @RequestMapping(value = "saveRead" ,produces = {"application/json;charset=utf-8"})
    public RestResponse saveRead(Article article,Integer time) {

        Integer id = article.getId();
        //拿到下限时长
        Article article1 = articleService.find(id);
        Integer lowerLimit = article1.getReadTimeL();     //下限时长
        Integer integrate = article1.getReadTimeU();    //积分

        //阅读总时长 小于  下限时长时  积分为0
        ReadState readState = new ReadState();
        readState.setArticleId(id);        //文章id
        readState.setReadTime(time);          //阅读时长
        readState.setUserId(getCurrentUser().getId());
        readState.setStartTime(new Date());     //阅读结束时间
        if(time < lowerLimit*1000){
            //将阅读信息存到read表中
            readState.setCount(0);             //文章积分
            readState.setReadState("在读");
            readService.saveReadState(readState);   //存储到read表
        }else{
            //判断用户阅读该文章是否已经获取了积分
            Integer userId = getCurrentUser().getId();
            Integer jiFen = readService.findJiFen(userId, id);
            if(jiFen == 0){
                //将阅读信息存到read表中
                readState.setCount(integrate);     //文章积分
                readState.setReadState("已读");
                readService.saveReadState(readState);   //存储到read表
            }


        }
//        //查询readState表中该用户读取这篇文章总时长
//        User user = getCurrentUser();
//        Integer useId = user.getId();  //得到useId
//        //根据useId,articleId查询该用户阅读该文章的最大时长
//        int count = readService.findIntegrate(useId,id);
//        //积分满时存一次数据，后面再阅读此文章不进行数据存储
//        if(count != integrate){
//            readService.saveReadState(readState);   //存储到read表
//        }

        return RestResponse.ok();
    }




    /**
     * 根据阅读状态返回文章列表
     * 阅读状态：已读1，在读2，未读3
     * @param
     */
    @RequestMapping(value = "typeArticleList")
    public RestResponse<PageInfo<Article>> typeArticleList(Integer state,MessageRequestVM messageRequestVM) {

        messageRequestVM.setReceiveUserId(getCurrentUser().getId());
        PageInfo<Article> articlePageInfo = articleService.articlePage(state,messageRequestVM);
        List<Article> list = articlePageInfo.getList();
        return RestResponse.ok(articlePageInfo);
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

}
