package com.alvis.exam.controller.admin;


import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.domain.Chapter;
import com.alvis.exam.domain.ExamType;
import com.alvis.exam.domain.Subject;
import com.alvis.exam.domain.dto.KvobjDTO;
import com.alvis.exam.service.SubjectService;
import com.alvis.exam.utility.PageInfoHelper;
import com.alvis.exam.viewmodel.admin.education.SubjectEditRequestVM;
import com.alvis.exam.viewmodel.admin.education.SubjectPageRequestVM;
import com.alvis.exam.viewmodel.admin.education.SubjectResponseVM;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController("AdminEducationController")
@RequestMapping(value = "/api/admin/education")
@AllArgsConstructor
public class EducationController extends BaseApiController {

    @Autowired
    private SubjectService subjectService;

    /**
     * 试卷列表/添加试卷  返回区域
     *
     * @return
     */
    @RequestMapping(value = "/subject/list", method = RequestMethod.POST)
    public RestResponse<List<Subject>> list() {
        List<Subject> subjects = subjectService.allSubject();
        return RestResponse.ok(subjects);
    }

    /**
     * 添加试卷  试卷类型
     *
     * @return
     */
    @RequestMapping(value = "/subject/listExamType", method = RequestMethod.POST)
    public RestResponse<List<ExamType>> listExamType() {
        List<ExamType> examTypes = subjectService.allExamType();    //试卷类型
        return RestResponse.ok(examTypes);
    }

    /**
     * 添加试卷  测试试卷下的文章分类+章节
     *
     * @return
     */
    @ApiOperation(value = "添加试卷  测试试卷下的文章分类+章节")
    @RequestMapping(value = "/subject/listTest", method = RequestMethod.POST)
    public RestResponse listTest(Integer examTypeId) {
        ExamType examType = subjectService.findNameByExamTypeId(examTypeId);
        List<ArticleType> articleTypes = new ArrayList<>();
        if ("测试试卷".equals(examType.getPaperType())) {
            articleTypes = subjectService.findArticleType();    //文章分类
            ArrayList<Object> arrayList1 = new ArrayList<>();
            for (ArticleType articleType : articleTypes) {
                String typeName = articleType.getTypeName();
                Integer typeId = articleType.getId();
                KvobjDTO k1 = new KvobjDTO();
                k1.setValue(typeId);
                k1.setLabel(typeName);
                List<Chapter> chapters = subjectService.findChapterById(typeId);  //文章分类下的章节
                ArrayList<Object> arrayList = new ArrayList<>();
                for (Chapter chapter : chapters) {
                    KvobjDTO k2 = new KvobjDTO();
                    String name = chapter.getName();
                    Integer id = chapter.getId();
                    k2.setValue(id);
                    k2.setLabel(name);
                    arrayList.add(k2);
                }
                k1.setChildren(arrayList);
                arrayList1.add(k1);
            }
            return RestResponse.ok(arrayList1);
        }
        else {
            return RestResponse.ok();
        }
    }



    @RequestMapping(value = "/subject/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<SubjectResponseVM>> pageList(@RequestBody SubjectPageRequestVM model) {
        PageInfo<Subject> pageInfo = subjectService.page(model);
        PageInfo<SubjectResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            return modelMapper.map(e, SubjectResponseVM.class);
        });
        return RestResponse.ok(page);
    }

    @RequestMapping(value = "/subject/edit", method = RequestMethod.POST)
    public RestResponse<SubjectEditRequestVM> edit(@RequestBody @Valid SubjectEditRequestVM model) {
        Subject subject = modelMapper.map(model, Subject.class);
        if (model.getId() == null) {
            subjectService.insertByFilter(subject);
        } else {
            subjectService.updateByIdFilter(subject);
        }
        return RestResponse.ok(model);
    }

    @RequestMapping(value = "/subject/select/{id}", method = RequestMethod.POST)
    public RestResponse<SubjectEditRequestVM> select(@PathVariable Integer id) {
        Subject subject = subjectService.selectById(id);
        SubjectEditRequestVM vm = modelMapper.map(subject, SubjectEditRequestVM.class);
        return RestResponse.ok(vm);
    }

}
