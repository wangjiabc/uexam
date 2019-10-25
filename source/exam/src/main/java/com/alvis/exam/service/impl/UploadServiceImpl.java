package com.alvis.exam.service.impl;

import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.domain.Message;
import com.alvis.exam.domain.MessageUser;
import com.alvis.exam.repository.ArticleMapper;
import com.alvis.exam.repository.ArticleTypeMapper;
import com.alvis.exam.service.UploadService;
import com.alvis.exam.viewmodel.admin.article.ArticleVM;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import javax.annotation.Resource;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {


    @Resource
    private ArticleTypeMapper articleTypeMapper;
    @Resource
    private ArticleMapper articleMapper;


    /**
     * 动态查看文章类型
     * @return
     */
    @Override
    public List<ArticleType> findArticleType() {
        List<ArticleType> types = articleTypeMapper.findAll();
        return types;
    }

    @Override
    public String saveArticle(ArticleVM articleVM) {
        articleVM.setUploadDay(new Date());     //上传时间
        int insert = articleMapper.insert(articleVM);
        if(insert != 0){
            return "上传成功";
        }
        return "上传失败";
    }

    /**
     * 动态查看所有数据
     * @return
     */
    @Override
    public PageInfo<ArticleType> pageList(MessagePageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                articleTypeMapper.page(requestVM)
        );
    }

    @Override
    public PageInfo<Article> page(MessagePageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                articleMapper.page(requestVM)
        );
    }


    @Override
    public String saveArticle(ArticleType articleType) {

        int insert = articleTypeMapper.insert(articleType);
        if(insert != 0){
            return "上传成功";
        }
        return "上传失败";
    }
}
