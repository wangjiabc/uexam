package com.alvis.exam.controller.wx.student;

import com.alvis.exam.base.RestResponse;
import com.alvis.exam.controller.wx.BaseWXApiController;
import com.alvis.exam.domain.*;
import com.alvis.exam.service.ReadService;
import com.alvis.exam.service.UploadService;
import com.alvis.exam.utility.DateTimeUtil;
import com.alvis.exam.utility.PageInfoHelper;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.alvis.exam.viewmodel.student.exam.ExamPaperPageResponseVM;
import com.alvis.exam.viewmodel.student.exam.ExamPaperPageVM;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.alvis.exam.viewmodel.student.user.MessageResponseVM;
import com.alvis.exam.viewmodel.wx.student.KeyObjDTO;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(value = "/api/wx/student/read",produces = {"text/json;charset=utf-8"})
@AllArgsConstructor
@ResponseBody
public class ArticleController extends BaseWXApiController {

    @Resource
    private ReadService readService;
    @Resource
    private UploadService uploadService;

    /**
     * 返回文章信息
     * @param
     */
    @RequestMapping(value = "/xxx", method = RequestMethod.POST)
    public RestResponse<PageInfo<Article>> pageList(@RequestBody MessagePageRequestVM model) {
        PageInfo<Article> pageInfo = readService.page(model);

        return RestResponse.ok(pageInfo);
    }


    /**
     * 根据文章类型返回该类型所有文章
     * @param
     */
    @RequestMapping(value = "/articleList", method = RequestMethod.POST)
    public List<Article> articleList(@RequestBody JSONObject jsonObject) {

        //拿到返回的类型
        int id = jsonObject.getInt("typeId");
        List<Article> list = new ArrayList<>();
        //在文章表中查询该类型
        List<Article> articles = readService.findAll();
        for (Article article : articles) {
            int typeId = article.getTypeId();
            if(typeId == id){
                list.add(article);
            }
        }
        return list;
    }


    /**
     * 传递文章类型
     * @param
     */
    @RequestMapping(value = "/typeMap")
    public String typeList() {
        //传递所有文章类型
        List<KeyObjDTO> arrayList = new ArrayList<>();

        List<ArticleType> lists = uploadService.findArticleType();
        for (ArticleType articleType : lists) {
            KeyObjDTO dto = new KeyObjDTO();
            String typeName = articleType.getTypeName();
            Integer id = articleType.getId();
            dto.setName(id+"");
            dto.setValue(typeName);
            arrayList.add(dto);
        }
//        JSONObject jsonObject = JSONObject.fromObject(arrayList);
        JSONArray jsonArray = JSONArray.fromObject(arrayList);
        String string = jsonArray.toString();
        log.info("文章类型---"+string);
        return string;
    }

}
