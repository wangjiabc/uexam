package com.alvis.exam.service.impl;

import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ReadState;
import com.alvis.exam.repository.ArticleMapper;
import com.alvis.exam.repository.ReadStateMapper;
import com.alvis.exam.service.ReadService;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ReadServiceImpl implements ReadService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ReadStateMapper readStateMapper;


    @Override
    public PageInfo<Article> page(MessagePageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                articleMapper.page(requestVM)
        );
    }



    @Override
    public void saveReadState(ReadState readState) {
        readStateMapper.insert(readState);
    }

    @Override
    public int findIntegrate(Integer useId,Integer articleId) {
        ReadState readState1 = new ReadState();
        readState1.setUserId(useId);
        readState1.setArticleId(articleId);
        List<ReadState> list = readStateMapper.selectByTwoId(readState1);
        int count = 0;
        List<Integer> list1 = new ArrayList<>();
        for (ReadState readState : list) {
            int readTime = readState.getReadTime();
            if (readTime != 0) {
                list1.add(readTime);
            }
        }
        if (list1 != null && list1.size() > 0) {
            //取数组中的最大值
//            int max = Collections.max(list1);
            int max = getMax(list1);
            count = max;
        } else {
            count = 0;
        }
        return count;
    }




    @Override
    public Integer findJiFen(Integer userId, Integer id) {
        ReadState readState = new ReadState();
        readState.setArticleId(id);
        readState.setUserId(userId);
        List<ReadState> list = readStateMapper.selectByTwoId(readState);
        int jiFen = 0;
        for (ReadState state : list) {
            Integer count = state.getCount();
            jiFen += count;
        }

        return jiFen;
    }

    /**
     * 取list集合中的最大值
     *
     * @param list
     * @return
     */
    public static int getMax(List<Integer> list) {
        int max = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (max < list.get(i)) {
                max = list.get(i);
            }
        }
        return max;
    }


}
