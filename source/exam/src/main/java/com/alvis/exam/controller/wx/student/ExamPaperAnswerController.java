package com.alvis.exam.controller.wx.student;

import com.alvis.exam.base.RestResponse;
import com.alvis.exam.controller.wx.BaseWXApiController;
import com.alvis.exam.domain.*;
import com.alvis.exam.domain.enums.ExamPaperAnswerStatusEnum;
import com.alvis.exam.domain.enums.ExamPaperTypeEnum;
import com.alvis.exam.domain.enums.QuestionTypeEnum;
import com.alvis.exam.event.CalculateExamPaperAnswerCompleteEvent;
import com.alvis.exam.event.UserEvent;
import com.alvis.exam.service.ExamPaperAnswerService;
import com.alvis.exam.service.ExamPaperService;
import com.alvis.exam.service.SubjectService;
import com.alvis.exam.utility.DateTimeUtil;
import com.alvis.exam.utility.ExamUtil;
import com.alvis.exam.utility.PageInfoHelper;
import com.alvis.exam.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.alvis.exam.viewmodel.student.exam.*;
import com.alvis.exam.viewmodel.student.exampaper.ExamPaperAnswerPageResponseVM;
import com.alvis.exam.viewmodel.student.exampaper.ExamPaperAnswerPageVM;
import com.github.pagehelper.PageInfo;
import com.voucher.manage.tools.MyTestUtil;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@Controller("WXStudentExamPaperAnswerController")
@RequestMapping(value = "/api/wx/student/exampaper/answer")
@AllArgsConstructor
@ResponseBody
public class ExamPaperAnswerController extends BaseWXApiController {
	
	@Autowired
    private  ExamPaperAnswerService examPaperAnswerService;
	@Autowired
	private  SubjectService subjectService;
	@Autowired
	private  ApplicationEventPublisher eventPublisher;
	@Autowired
	private ExamPaperService examPaperService;

    /**
     * 用于测试试卷  可以重复做
     * @param request
     * @return
     */
    @RequestMapping(value = "/examSubmit", method = RequestMethod.POST)
    public RestResponse examSubmit(HttpServletRequest request) {
        ExamPaperSubmitVM examPaperSubmitVM = requestToExamPaperSubmitVM(request);
        User user = getCurrentUser();
        ExamPaperAnswerInfo examPaperAnswerInfo = examPaperAnswerService.calculateExamPaperAnswerTest(examPaperSubmitVM, user);
        ExamPaperAnswer examPaperAnswer = examPaperAnswerInfo.getExamPaperAnswer();
        Integer userScore = examPaperAnswer.getUserScore();
        String scoreVm = ExamUtil.scoreToVM(userScore);
        UserEventLog userEventLog = new UserEventLog(user.getId(), user.getUserName(), user.getRealName(), new Date());
        String content = user.getUserName() + " 提交试卷：" + examPaperAnswerInfo.getExamPaper().getName()
                + " 得分：" + scoreVm
                + " 耗时：" + ExamUtil.secondToVM(examPaperAnswer.getDoTime());
        userEventLog.setContent(content);

        Integer examPaperId = examPaperAnswer.getExamPaperId(); //试卷id
        Article article = new Article();
        article.setExamPaperId(examPaperId);
        ExamPaper examPaper = examPaperService.findExamPaperByExamPaperId(article);
        Integer passScore = examPaper.getPassScore();   //试卷合格分
        if(passScore == null){
            passScore = 0;
        }
        HashMap<String, Object> map = new HashMap<>();

        int score = Integer.parseInt(scoreVm);
        Boolean isTrue = true;  //判断是否合格
        if(score < passScore/10){
            isTrue = false;
        }
        map.put("score",score);
        map.put("passScore",passScore/10);
        map.put("isQualified",isTrue);

        eventPublisher.publishEvent(new CalculateExamPaperAnswerCompleteEvent(examPaperAnswerInfo));
        eventPublisher.publishEvent(new UserEvent(userEventLog));
        return RestResponse.ok(map);
    }




    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamPaperAnswerPageResponseVM>> pageList(@Valid ExamPaperAnswerPageVM model) {
        model.setCreateUser(getCurrentUser().getId());
        //查询已考试卷
        PageInfo<ExamPaperAnswer> pageInfo = examPaperAnswerService.studentPage(model);
        PageInfo<ExamPaperAnswerPageResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamPaperAnswerPageResponseVM vm = modelMapper.map(e, ExamPaperAnswerPageResponseVM.class);
            Subject subject = subjectService.selectById(vm.getSubjectId());
            vm.setDoTime(ExamUtil.secondToVM(e.getDoTime()));
            vm.setSystemScore(ExamUtil.scoreToVM(e.getSystemScore()));
            vm.setUserScore(ExamUtil.scoreToVM(e.getUserScore()));
            vm.setPaperScore(ExamUtil.scoreToVM(e.getPaperScore()));
            vm.setSubjectName(subject.getName());
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }

    //固定试卷所用提交方式
    @RequestMapping(value = "/answerSubmit", method = RequestMethod.POST)
    public RestResponse answerSubmit(HttpServletRequest request) {
        ExamPaperSubmitVM examPaperSubmitVM = requestToExamPaperSubmitVM(request);
        User user = getCurrentUser();
        ExamPaperAnswerInfo examPaperAnswerInfo = examPaperAnswerService.calculateExamPaperAnswer(examPaperSubmitVM, user);
        if (null == examPaperAnswerInfo) {
            return RestResponse.fail(2, "试卷不能重复做");
        }
        ExamPaperAnswer examPaperAnswer = examPaperAnswerInfo.getExamPaperAnswer();
        Integer userScore = examPaperAnswer.getUserScore();
        String scoreVm = ExamUtil.scoreToVM(userScore);

        UserEventLog userEventLog = new UserEventLog(user.getId(), user.getUserName(), user.getRealName(), new Date());
        String content = user.getUserName() + " 提交试卷：" + examPaperAnswerInfo.getExamPaper().getName()
                + " 得分：" + scoreVm
                + " 耗时：" + ExamUtil.secondToVM(examPaperAnswer.getDoTime());
        userEventLog.setContent(content);

        Integer examPaperId = examPaperAnswer.getExamPaperId(); //试卷id
        Article article = new Article();
        article.setExamPaperId(examPaperId);
        ExamPaper examPaper = examPaperService.findExamPaperByExamPaperId(article);
        Integer passScore = examPaper.getPassScore();   //试卷合格分(千分制)
        if(passScore == null){
            passScore = 0;
        }
        HashMap<String, Object> map = new HashMap<>();
        int score = Integer.parseInt(scoreVm);
        Boolean isTrue = true;  //判断是否合格
        if(score < passScore/10){
            isTrue = false;
        }
        map.put("score",score);
        map.put("passScore",passScore/10);
        map.put("isQualified",isTrue);

        eventPublisher.publishEvent(new CalculateExamPaperAnswerCompleteEvent(examPaperAnswerInfo));
        eventPublisher.publishEvent(new UserEvent(userEventLog));
        return RestResponse.ok(map);
    }

    private ExamPaperSubmitVM requestToExamPaperSubmitVM(HttpServletRequest request) {
        ExamPaperSubmitVM examPaperSubmitVM = new ExamPaperSubmitVM();
        examPaperSubmitVM.setId(Integer.parseInt(request.getParameter("id")));
        examPaperSubmitVM.setDoTime(Integer.parseInt(request.getParameter("doTime")));
        List<String> parameterNames = Collections.list(request.getParameterNames()).stream()
                .filter(n -> n.contains("_"))
                .collect(Collectors.toList());
        //题目答案按序号分组
        Map<String, List<String>> questionGroup = parameterNames.stream().collect(Collectors.groupingBy(p -> p.substring(0, p.indexOf("_"))));
        List<ExamPaperSubmitItemVM> answerItems = new ArrayList<>();
        questionGroup.forEach((k, v) -> {
            ExamPaperSubmitItemVM examPaperSubmitItemVM = new ExamPaperSubmitItemVM();
            String p = v.get(0);
            String[] keys = p.split("_");
            examPaperSubmitItemVM.setQuestionId(Integer.parseInt(keys[1]));
            examPaperSubmitItemVM.setItemOrder(Integer.parseInt(keys[0]));
            QuestionTypeEnum typeEnum = QuestionTypeEnum.fromCode(Integer.parseInt(keys[2]));
            if (v.size() == 1) {
                String content = request.getParameter(p);
                examPaperSubmitItemVM.setContent(content);
                if (typeEnum == QuestionTypeEnum.MultipleChoice) {
                    examPaperSubmitItemVM.setContentArray(Arrays.asList(content.split(",")));
                }
            } else {  //多个空 填空题
                List<String> answers = v.stream().sorted(Comparator.comparingInt(ExamUtil::lastNum)).map(inputKey -> request.getParameter(inputKey)).collect(Collectors.toList());
                examPaperSubmitItemVM.setContentArray(answers);
            }
            answerItems.add(examPaperSubmitItemVM);
        });
        examPaperSubmitVM.setAnswerItems(answerItems);
        return examPaperSubmitVM;
    }


    @PostMapping(value = "/read/{id}")
    public RestResponse<ExamPaperReadVM> read(@PathVariable Integer id) {
        ExamPaperAnswer examPaperAnswer = examPaperAnswerService.selectById(id);
        ExamPaperReadVM vm = new ExamPaperReadVM();
        ExamPaperEditRequestVM paper = examPaperService.examPaperToVM(examPaperAnswer.getExamPaperId());
        ExamPaperSubmitVM answer = examPaperAnswerService.examPaperAnswerToVM(examPaperAnswer.getId());
        vm.setPaper(paper);
        vm.setAnswer(answer);
        return RestResponse.ok(vm);
    }
}
