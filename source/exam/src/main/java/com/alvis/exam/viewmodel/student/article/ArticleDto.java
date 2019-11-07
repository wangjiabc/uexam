package com.alvis.exam.viewmodel.student.article;

import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import lombok.Data;

@Data
public class ArticleDto extends MessageRequestVM {
    private Integer typeId;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
