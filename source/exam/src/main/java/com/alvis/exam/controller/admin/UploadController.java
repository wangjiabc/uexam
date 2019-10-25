package com.alvis.exam.controller.admin;


import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.configuration.property.SystemConfig;
import com.alvis.exam.domain.*;
import com.alvis.exam.service.FileUpload;
import com.alvis.exam.service.UploadService;
import com.alvis.exam.utility.DateTimeUtil;
import com.alvis.exam.utility.PageInfoHelper;
import com.alvis.exam.viewmodel.admin.article.ArticleVM;
import com.alvis.exam.viewmodel.admin.education.SubjectEditRequestVM;
import com.alvis.exam.viewmodel.admin.file.UeditorConfigVM;
import com.alvis.exam.viewmodel.admin.file.UploadResultVM;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.alvis.exam.viewmodel.admin.message.MessageResponseVM;
import com.alvis.exam.viewmodel.admin.question.QuestionEditRequestVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.PredicateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@AllArgsConstructor
@RequestMapping("/api/admin/upload")
@RestController("AdminUploadController")
public class UploadController extends BaseApiController {

    @Autowired
    private UploadService uploadService;

    /**
     * 返回文章类型
     * @param
     */
    @RequestMapping(value = "/typeList", method = RequestMethod.POST)
    public List<String> typeList() {
        List<ArticleType> lists = uploadService.findArticleType();
        List<String> list = new ArrayList<>();  //存ArticleType表的typeName
        for (ArticleType articleType : lists) {
            String typeName = articleType.getTypeName();
            list.add(typeName);
        }
        return list;
    }

    /**
     * 存储上传文章
     * @param jsonObject
     */
    @RequestMapping("save")
    public void save(@RequestBody JSONObject jsonObject){
        List<ArticleType> lists = uploadService.findArticleType();

        ArticleVM articleVM = new ArticleVM();
        String name = jsonObject.getString("name");     //文章标题
        String author = jsonObject.getString("author"); //文章作者
        String doc = jsonObject.getString("doc");       //文章内容
        String a = jsonObject.getString("down");     //下限时长
        String type = jsonObject.getString("selectType");   //文章类型
        int down = Integer.parseInt(a);

        String b = jsonObject.getString("count");   //文章类型
        int count = Integer.parseInt(b);

        articleVM.setTitle(name);
        articleVM.setAuthor(author);
        articleVM.setContent(doc);
        articleVM.setReadTimeL(down);
        articleVM.setReadTimeU(count);

        int typeId = 0; //接收typeId
        for (ArticleType articleType : lists) {
            String typeName = articleType.getTypeName();
            if(typeName.equals(type)){
                Integer id = articleType.getId();
                typeId = id;
            }
        }
        articleVM.setTypeId(typeId);
        uploadService.saveArticle(articleVM);
    }

    /**
     * 返回文章信息
     * @param
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<Article>> pageList(@RequestBody MessagePageRequestVM model) {
        PageInfo<Article> pageInfo = uploadService.page(model);

        return RestResponse.ok(pageInfo);
    }

    /**
     * 返回文章类型
     * @param
     */
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public RestResponse<PageInfo<ArticleType>> typeList(@RequestBody MessagePageRequestVM model) {
        PageInfo<ArticleType> pageInfo = uploadService.pageList(model);

        return RestResponse.ok(pageInfo);
    }


    /**
     * 添加类型
     * @param jsonObject
     */
    @RequestMapping("saveType")
    public void saveType(@RequestBody JSONObject jsonObject){
        ArticleType type = new ArticleType();
        String str = jsonObject.getString("type");

        List<ArticleType> list = uploadService.findArticleType();
        List<String> arrayList = new ArrayList<>();
        for (ArticleType articleType : list) {
            String typeName = articleType.getTypeName();
            if(typeName != null){
                arrayList.add(typeName);
            }
        }


            type.setTypeName(str);
            uploadService.saveArticle(type);

    }
}
