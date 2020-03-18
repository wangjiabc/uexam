package com.alvis.exam.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.News;
import com.alvis.exam.service.NewsService;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/admin/news")
@RestController("newsController")
@Api(value = "新闻controller")
public class NewsController {

    @Resource
    private NewsService newsService;

    /**
     * 存储上传新闻
     * @param jsonObject
     */
    @RequestMapping("save")
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

        newsService.insert(news);
        return RestResponse.ok();
    }


    /**
     * 删除新闻 :实际是修改状态
     * @param jsonObject
     */
    @RequestMapping("delete")
    public RestResponse delete(@RequestBody JSONObject jsonObject) {
        //根据id删除
        Integer id = jsonObject.getInteger("id");
        newsService.delete(id);
        return RestResponse.ok();
    }

    /**
     * 修改新闻
     * @param jsonObject
     */
    @RequestMapping("update")
    public RestResponse update(@RequestBody JSONObject jsonObject) {
        //拿到信息
        String title = jsonObject.getString("title");         //新闻标题
        String writer = jsonObject.getString("writer");         //新闻作者
        String content = jsonObject.getString("content");           //新闻内容
        Integer id = jsonObject.getInteger("id");
        //插入
        News news = new News();
        news.setTitle(title);
        news.setWriter(writer);
        news.setStatus(1);              //状态
        news.setAddTime(new Date());    //时间
        news.setContent(content);
        news.setId(id);
        //根据id修改
        newsService.update(news);
        return RestResponse.ok();
    }

    /**
     * 查询新闻
     */
    @RequestMapping("selectAll")
    public RestResponse selectAll(@RequestBody MessagePageRequestVM model) {
        PageInfo<News> pageInfo = newsService.selectAll(model);
        return RestResponse.ok(pageInfo);
    }
}
