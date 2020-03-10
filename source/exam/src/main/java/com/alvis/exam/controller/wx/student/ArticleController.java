package com.alvis.exam.controller.wx.student;

import com.alvis.exam.base.RestResponse;
import com.alvis.exam.configuration.property.UrlConfig;
import com.alvis.exam.controller.wx.BaseWXApiController;
import com.alvis.exam.domain.*;
import com.alvis.exam.domain.dto.ExamPaperDTO;
import com.alvis.exam.domain.dto.VisitUsersDTO;
import com.alvis.exam.domain.dto.article.ArticleDTO;
import com.alvis.exam.service.*;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@CrossOrigin
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
    @Resource
    private ExamPaperService examPaperService;
    @Autowired
    private UrlConfig urlConfig;


    /**
     * 文章二级目录 : 章
     * @param
     */
    @RequestMapping(value = "/secondLevel")
    public RestResponse pageList(String typeId,MessageRequestVM messageRequestVM) {
        //根据章typeId查询章
        messageRequestVM.setReceiveUserId(getCurrentUser().getId());
        PageInfo<ExamPaperDTO> pageInfo = null;
        pageInfo = articleService.secondLevel(Integer.parseInt(typeId),messageRequestVM);
        List<ExamPaperDTO> list = pageInfo.getList();
        if(list.size() == 0){
            PageInfo<ExamPaperDTO> pageInfo1 = articleService.secondLevel1(Integer.parseInt(typeId), messageRequestVM);
            List<ExamPaperDTO> list1 = pageInfo1.getList();
            for (ExamPaperDTO examPaperDTO : list1) {
                Integer chapterId = examPaperDTO.getChapterId();
                Article article = new Article();
                article.setChapterId(chapterId);
                article.setTypeId(Integer.parseInt(typeId));
                ExamPaper examPaper = examPaperService.findByTypeIdAndChapterId(article);
                Integer passScore = examPaper.getPassScore();
                if(passScore == null){
                    passScore = 0;
                }
                examPaperDTO.setPassScore(passScore/10);    //试卷合格分
                examPaperDTO.setExamPaperId(examPaper.getId()); //考试试卷id
            }

            Comparator<ExamPaperDTO> comparator = new Comparator<ExamPaperDTO>() {
                @Override
                public int compare(ExamPaperDTO o1, ExamPaperDTO o2) {
                    return o1.getName().charAt(1) - o2.getName().charAt(1);
                }
            };
            Collections.sort(list1,comparator);

            return RestResponse.ok(pageInfo1);
        }
        else {
            pageInfo = articleService.secondLevel2(Integer.parseInt(typeId),messageRequestVM);
            List<ExamPaperDTO> list2 = pageInfo.getList();
            for (ExamPaperDTO examPaperDTO : list2) {
                Integer chapterId = examPaperDTO.getChapterId();
                Article article = new Article();
                article.setChapterId(chapterId);
                article.setTypeId(Integer.parseInt(typeId));
                ExamPaper examPaper = examPaperService.findByTypeIdAndChapterId(article);
                Integer passScore = examPaper.getPassScore();
                if(passScore == null){
                    passScore = 0;
                }
                examPaperDTO.setPassScore(passScore/10);    //试卷合格分
                examPaperDTO.setExamPaperId(examPaper.getId()); //考试试卷id
            }

            Comparator<ExamPaperDTO> comparator = new Comparator<ExamPaperDTO>() {
                @Override
                public int compare(ExamPaperDTO o1, ExamPaperDTO o2) {
                    return o1.getName().charAt(1) - o2.getName().charAt(1);
                }
            };
            Collections.sort(list2,comparator);


            return RestResponse.ok(pageInfo);
        }
    }


    /**
     * 文章部分信息 :节
     * @param
     */
    @RequestMapping(value = "/threeLevel")
    public RestResponse<PageInfo<ArticleDTO>> pageList(String typeId,String chapterId,MessageRequestVM messageRequestVM) {
        //根据章id查询该章下所有的节
        messageRequestVM.setReceiveUserId(getCurrentUser().getId());
        PageInfo<ArticleDTO> pageInfo = articleService.chapterPage(Integer.parseInt(typeId),Integer.parseInt(chapterId),messageRequestVM);
        List<ArticleDTO> list = pageInfo.getList();
        for (ArticleDTO articleDTO : list) {
            Article article = new Article();
            article.setChapterId(Integer.parseInt(chapterId));
            article.setTypeId(Integer.parseInt(typeId));
            ExamPaper examPaper = examPaperService.findByTypeIdAndChapterId(article);
            articleDTO.setExamPaperId(examPaper.getId()); //考试试卷id
        }

        Comparator<ArticleDTO> comparator = new Comparator<ArticleDTO>() {
            @Override
            public int compare(ArticleDTO o1, ArticleDTO o2) {
                return o1.getTitle().charAt(1) - o2.getTitle().charAt(1);
            }
        };
        Collections.sort(list,comparator);

        return RestResponse.ok(pageInfo);
    }


    /**
     * 返回文章详情
     * @param article  含文章id
     */
    @RequestMapping(value = "details" ,produces = {"application/json;charset=utf-8"})
    public RestResponse pageList(Article article) {
        Integer id = article.getId();
        //去考试试卷中找该文章绑定的试卷
        Article article1 = articleService.find(id);
        String details = articleService.findDetails(id);
        //拿到下限时长
        Integer lowerLimit = article1.getReadTimeL();    //下限时长
        //查询readState表中该用户读取这篇文章总时长
        User user = getCurrentUser();
        Integer useId = user.getId();  //得到useId
        //根据useId和articleId 查询该用户阅读该文章的最大时长
        int count = readService.findIntegrate(useId,id);

        Article article2 = new Article();
        article2.setContent(details);       //文章详情
        article2.setMaxIntegral(lowerLimit*1000 - count);  //用户阅读该文章，已读时长
        article2.setReadTimeL(lowerLimit*1000);       //下限时长
        article2.setId(id);
        article2.setPlainText(article1.getPlainText());
        return RestResponse.ok(article2);
    }


    /**
     * 结束文章阅读存储
     * @param article  含文章id
     * @param time     用户阅读时长
     */
    @RequestMapping(value = "saveRead" ,produces = {"application/json;charset=utf-8"})
    public RestResponse saveRead(Article article,String time) {
        int timeNew = Integer.parseInt(time);
        Integer id = article.getId();
        //拿到下限时长
        Article article1 = articleService.find(id);
        Integer lowerLimit = article1.getReadTimeL();     //下限时长
        Integer integrate = article1.getMaxIntegral();    //积分

        //阅读总时长 小于  下限时长时  积分为0
        ReadState readState = new ReadState();
        readState.setArticleId(id);        //文章id
        readState.setReadTime(timeNew);          //阅读时长
        readState.setUserId(getCurrentUser().getId());
        readState.setStartTime(new Date());     //阅读结束时间
        if(timeNew < lowerLimit*1000){
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
            viewPager.setAddress(urlConfig.getUrl() + "wx/" + address);
            arrayList.add(0,viewPager);
        }
        return arrayList;
    }


}
