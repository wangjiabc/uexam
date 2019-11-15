package com.alvis.exam.domain.dto.article;

import com.alvis.exam.domain.Article;
import lombok.Data;

@Data
public class ArticleDTO extends Article {
    private String readState;

    public String getReadState() {
        return readState;
    }

    public void setReadState(String readState) {
        this.readState = readState;
    }
}
