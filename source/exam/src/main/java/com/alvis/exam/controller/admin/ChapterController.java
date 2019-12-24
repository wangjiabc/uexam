package com.alvis.exam.controller.admin;

import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.Chapter;
import com.alvis.exam.service.ChapterService;
import com.alvis.exam.viewmodel.admin.article.ArticleTypeVM;
import com.alvis.exam.viewmodel.admin.user.ChapterAndIdVM;
import com.alvis.exam.viewmodel.admin.user.ChapterVM;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author yangsy
 */
@CrossOrigin
@RestController("AdminChapterController")
@RequestMapping(value = "/api/admin/chapter")
@AllArgsConstructor
public class ChapterController extends BaseApiController {
	
	@Autowired
    private ChapterService chapterService;

    /**
     * 添加章节
     * @param model
     * @return
     */
    @RequestMapping(value = "/insertChapter", method = RequestMethod.POST)
    public RestResponse<Chapter> insert(@RequestBody ChapterAndIdVM model){

        ChapterVM model1= new ChapterVM();
        model1.setName(model.getChapterVM().getName());
        model1.setTypeId(model.getTypeId());
        model1.setSequence(model.getChapterVM().getSequence());
        model1.setCriterion(model.getChapterVM().getCriterion());
        model1.setState(1);
        this.chapterService.insertChapter(model1);
        return RestResponse.ok();
    }

    /**
     * 修改章节
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateChapter", method = RequestMethod.POST)
    public RestResponse<Chapter> update(@RequestBody ChapterVM model){

        this.chapterService.updateChapter(model);
        return RestResponse.ok();
    }

    /**
     * 删除章节
     * @param
     * @return
     */
    @RequestMapping(value = "/deleteChapter", method = RequestMethod.POST)
    public RestResponse delete(@RequestBody ChapterVM model) {

        this.chapterService.deleteChapterById(model.getId(),model.getState());
        return RestResponse.ok();
    }

    /**
     * 查询章节
     * @param articleTypeVM
     * @param
     * @return
     */
    @RequestMapping(value = "/queryChapter", method = RequestMethod.POST)
    public List<Chapter> queryChapter(@RequestBody ArticleTypeVM articleTypeVM) {

        List<Chapter> list= this.chapterService.queryChapter(articleTypeVM.getId());
        return list;
    }

}
