package com.alvis.exam.module;

import com.alvis.exam.ExamApplication;
import com.alvis.exam.service.ExamPaperService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExamApplication.class)
public class IndexTests {

    @Autowired
    private ExamPaperService examPaperService;

    @Test
    public void sumTest() {
        //DataSourceSwitch.switchDataSource();
        Integer count = examPaperService.selectAllCount();
        List<Integer> kv = examPaperService.selectMothCount();
        System.out.print(kv);
    }


}
