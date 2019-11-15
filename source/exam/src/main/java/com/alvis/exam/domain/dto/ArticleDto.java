package com.alvis.exam.domain.dto;

import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class ArticleDto extends MessageRequestVM {
    private Integer typeId;

    private Integer receiveUserId;

    public Integer getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(Integer receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
