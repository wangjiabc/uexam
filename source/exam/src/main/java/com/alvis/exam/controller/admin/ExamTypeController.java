package com.alvis.exam.controller.admin;

import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.ExamType;
import com.alvis.exam.service.ExamTypeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yangsy
 */
@CrossOrigin
@RestController("AdminExamTypeController")
@RequestMapping(value = "/api/admin/examType")
@AllArgsConstructor
public class ExamTypeController extends BaseApiController {
	
	@Autowired
    private ExamTypeService examTypeService;

    /**
     * 查询试卷分类
     * @return
     */
    @RequestMapping(value = "/queryExamType", method = RequestMethod.POST)
    public RestResponse queryExamType() {
        List<ExamType> list= this.examTypeService.queryExamType();
        return RestResponse.ok(list);
    }
}
