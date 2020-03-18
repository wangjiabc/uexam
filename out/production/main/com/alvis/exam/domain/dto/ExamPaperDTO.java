package com.alvis.exam.domain.dto;

import com.alvis.exam.domain.Chapter;
import lombok.Data;

@Data
public class ExamPaperDTO{
    private Integer chapterId; //文章章节id
    private Integer passScore; //合格分
    private String name;
    private Integer typeId;
    private Integer sequence;
    private Integer criterion;
    private Integer state;
    private Integer chapterSequence;

    private Integer examPaperId;  //试卷id
}
