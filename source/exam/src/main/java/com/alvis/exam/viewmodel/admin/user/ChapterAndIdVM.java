package com.alvis.exam.viewmodel.admin.user;

import lombok.Data;

/**
 * 章节管理
 */
@Data
public class ChapterAndIdVM {

    private static final long serialVersionUID = 0L;

    /**
     * 分类ID
     */
    private Integer typeId;
    /**
     * 章节对象
     */
    private ChapterVM chapterVM;


    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public ChapterVM getChapterVM() {
        return chapterVM;
    }

    public void setChapterVM(ChapterVM chapterVM) {
        this.chapterVM = chapterVM;
    }
}
