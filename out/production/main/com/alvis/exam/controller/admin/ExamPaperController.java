package com.alvis.exam.controller.admin;

import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.base.SystemCode;
import com.alvis.exam.domain.*;
import com.alvis.exam.repository.ArticleTypeMapper;
import com.alvis.exam.repository.ChapterMapper;
import com.alvis.exam.service.ArticleTypeService;
import com.alvis.exam.service.ChapterService;
import com.alvis.exam.service.ExamPaperService;
import com.alvis.exam.utility.DateTimeUtil;
import com.alvis.exam.utility.PageInfoHelper;
import com.alvis.exam.viewmodel.admin.education.SubjectResponseVM;
import com.alvis.exam.viewmodel.admin.exam.ExamPaperPageRequestVM;
import com.alvis.exam.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.alvis.exam.viewmodel.admin.exam.ExamPaperTitleItemVM;
import com.alvis.exam.viewmodel.admin.exam.ExamResponseVM;
import com.alvis.exam.viewmodel.admin.question.QuestionEditRequestVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController("AdminExamPaperController")
@RequestMapping(value = "/api/admin/exam/paper")
@AllArgsConstructor
public class ExamPaperController extends BaseApiController {
	
	@Autowired
    private ExamPaperService examPaperService;
	@Resource
    private ArticleTypeService articleTypeService;
	@Resource
    private ChapterService chapterService;

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamResponseVM>> pageList(@RequestBody ExamPaperPageRequestVM model) {
        PageInfo<ExamPaper> pageInfo = examPaperService.page(model);
        PageInfo<ExamResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamResponseVM vm = modelMapper.map(e, ExamResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            Integer typeId = vm.getTypeId();
            Integer chapterId = vm.getChapterId();
            ArticleType articleType = articleTypeService.findByTypeId(typeId);
            Chapter chapter = chapterService.findChapterById(chapterId);
            if(articleType != null){
                vm.setArticleTypeName(articleType.getTypeName());
            }
            if(chapter != null){
                vm.setChapterName(chapter.getName());
            }

            return vm;
        });

        return RestResponse.ok(page);
    }



    @RequestMapping(value = "/taskExamPage", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamResponseVM>> taskExamPageList(@RequestBody ExamPaperPageRequestVM model) {
    	PageInfo<ExamPaper> pageInfo = examPaperService.taskExamPage(model);
        PageInfo<ExamResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamResponseVM vm = modelMapper.map(e, ExamResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }



    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RestResponse<ExamPaperEditRequestVM> edit(@RequestBody ExamPaperEditRequestVM model) {
        if(model.getId() == null){
            ExamPaper examPaper1 = examPaperService.findExamPaperByName(model.getName());
            if(examPaper1 != null){
                return RestResponse.fail(2,"试卷名称重复");
            }
        }

        List<ExamPaperTitleItemVM> titleItems = model.getTitleItems();
        if(titleItems.size() == 0){
            return RestResponse.fail(2,"请添加标题");
        }
        for (ExamPaperTitleItemVM titleItem : titleItems) {
            @Size(min = 1, message = "请添加题目") @Valid List<QuestionEditRequestVM> questionItems = titleItem.getQuestionItems();
            if(questionItems.size() == 0){
                return RestResponse.fail(2,"请添加题目");
            }
        }

        if(model.getId() == null){
            List<Integer> arr = model.getChapterType();    //设置试卷类型
            if(arr != null){
                int size = arr.size();
                if(size == 2){
                    Integer typeId = arr.get(0);  //TypeId
                    Integer chapterId = arr.get(1); //ChapterId
                    Article article = new Article();
                    article.setTypeId(typeId);
                    article.setChapterId(chapterId);
                    ExamPaper examPaper = examPaperService.findByTypeIdAndChapterId(article);
                    if(examPaper != null){
                        return RestResponse.fail(2,"不可重复使用同一章节");
                    }
                }
            }
        }

        ExamPaper examPaper = examPaperService.savePaperFromVM(model, getCurrentUser());
        ExamPaperEditRequestVM newVM = examPaperService.examPaperToVM(examPaper.getId());
        return RestResponse.ok(newVM);
    }

    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    public RestResponse<ExamPaperEditRequestVM> select(@PathVariable Integer id) {
        ExamPaperEditRequestVM vm = examPaperService.examPaperToVM(id);
        return RestResponse.ok(vm);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public RestResponse delete(@PathVariable Integer id) {
        ExamPaper examPaper = examPaperService.selectById(id);
        examPaper.setDeleted(true);
        examPaperService.updateByIdFilter(examPaper);
        return RestResponse.ok();
    }
}
