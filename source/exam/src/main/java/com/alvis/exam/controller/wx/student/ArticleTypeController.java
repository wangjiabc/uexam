package com.alvis.exam.controller.wx.student;

import com.alvis.exam.base.RestResponse;
import com.alvis.exam.controller.wx.BaseWXApiController;
import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.service.ArticleService;
import com.alvis.exam.service.ArticleTypeService;
import com.alvis.exam.service.ReadService;
import com.alvis.exam.service.UploadService;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.alvis.exam.viewmodel.wx.student.KeyObjDTO;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/api/wx/student/type")
@AllArgsConstructor
@ResponseBody
public class ArticleTypeController extends BaseWXApiController {

    @Resource
    private ArticleTypeService articleTypeService;


    /**
     * 传递文章类型
     * @param
     */
    @RequestMapping(value = "type")
    public List<ArticleType> typeList() {
//        List<ArticleType> articleTypes = articleTypeService.findArticleType();
        List<ArticleType> articleTypes = articleTypeService.findType();
        for (ArticleType articleType : articleTypes) {
            String origname = articleType.getOrigname();
            articleType.setPathDeposit("images/" + origname);
        }
        return articleTypes;
    }


}
