package com.alvis.exam.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.News;
import com.alvis.exam.viewmodel.admin.article.ArticleVM;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/admin/news")
@RestController("newsController")
@Api(value = "新闻controller")
public class NewsController {

    /**
     * 存储上传新闻
     * @param jsonObject
     */
    @RequestMapping("saveArticle")
    public RestResponse save(@RequestBody JSONObject jsonObject) {
        //拿到信息
        String title = jsonObject.getString("title");         //新闻标题
        String writer = jsonObject.getString("writer");         //新闻作者
        String content = jsonObject.getString("content");           //新闻内容
        //插入
        News news = new News();
        news.setTitle(title);
        news.setWriter(writer);
        news.setStatus(1);              //状态
        news.setAddTime(new Date());    //时间
        news.setContent(content);


        return RestResponse.ok();
    }
}
