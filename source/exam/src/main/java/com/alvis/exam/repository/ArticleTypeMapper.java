package com.alvis.exam.repository;

import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleType record);

    int insertSelective(ArticleType record);

    ArticleType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleType record);

    void updateType(ArticleType record);

    List<ArticleType> page(MessagePageRequestVM requestVM);

    List<ArticleType> findAll();

    List<ArticleType> findAllType(ArticleType articleType);


    void deleteType(ArticleType articleType);

    void updateArticleType(ArticleType articleType);

    List<ArticleType> findType(Integer state);

    ArticleType findByTypeId(Integer typeId);

    ArticleType findByTypeName(String typeName);

    List<Integer> find();
}