package com.alvis.exam.service.impl;

import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.repository.ArticleMapper;
import com.alvis.exam.repository.ArticleTypeMapper;
import com.alvis.exam.service.ArticleTypeService;
import com.alvis.exam.viewmodel.admin.article.ArticleVM;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ArticleTypeServiceImpl implements ArticleTypeService {
    @Resource
    private ArticleTypeMapper articleTypeMapper;



    /**
     * 动态查看文章分类
     * @return
     */
    @Override
    public List<ArticleType> findArticleType() {
        List<ArticleType> types = articleTypeMapper.findAll();
        return types;
    }

    /**
     * 动态查看所有分类信息
     * @return
     */
    @Override
    public PageInfo<ArticleType> pageList(MessagePageRequestVM requestVM) {
//        List<ArticleType> list = pageInfo.getList();
////        for (ArticleType articleType : list) {
////            String origname = articleType.getOrigname();
////            articleType.setPathDeposit("http://192.168.100.185:8091/images/" + origname);
////        }
        List<ArticleType> page = articleTypeMapper.page(requestVM);
        for (ArticleType articleType : page) {
            String origname = articleType.getOrigname();
            articleType.setPathDeposit("http://192.168.100.185:8091/images/" + origname);
        }
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                articleTypeMapper.page(requestVM)
        );
    }


    @Override
    public String saveArticleType(ArticleType articleType) {

        int insert = articleTypeMapper.insert(articleType);
        if(insert != 0){
            return "上传成功";
        }
        return "上传失败";
    }

    @Override
    public void updateType(ArticleType articleType) {
        articleTypeMapper.updateType(articleType);
    }

    @Override
    public void deleteType(ArticleType articleType) {
        articleTypeMapper.deleteType(articleType);
    }

    @Override
    public List<ArticleType> findType() {
        Integer state = 1;
        return articleTypeMapper.findType(state);
    }

    @Override
    public void updateArticleType(ArticleType articleType) {
        articleTypeMapper.updateArticleType(articleType);
    }
}
