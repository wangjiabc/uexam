package com.alvis.exam.controller.admin;

import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.Chapter;
import com.alvis.exam.service.ChapterService;
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
    public RestResponse<Chapter> insert(@RequestBody ChapterVM model){

        this.chapterService.insertChapter(model);
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
     * @param model
     * @param
     * @return
     */
    @RequestMapping(value = "/queryChapter", method = RequestMethod.POST)
    public List<Chapter> queryChapter(@RequestBody ChapterVM model) {

        List<Chapter> list= this.chapterService.queryChapter(model.getTypeId());
        return list;
    }

}
