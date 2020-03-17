package com.alvis.exam.controller.wx.student;

import com.alibaba.fastjson.JSONObject;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.News;
import com.alvis.exam.service.NewsService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/wx/student/news")
@RestController("newsWXController")
@Api(value = "新闻controller")
public class NewsController {

    @Resource
    private NewsService newsService;

    /**
     * 查询新闻
     */
    @RequestMapping("select")
    public RestResponse select() {
        List<News> list = newsService.select();
        return RestResponse.ok(list);
    }
}
