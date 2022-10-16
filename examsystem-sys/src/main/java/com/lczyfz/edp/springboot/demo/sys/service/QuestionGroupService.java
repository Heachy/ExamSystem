package com.lczyfz.edp.springboot.demo.sys.service;

import com.lczyfz.edp.springboot.core.service.CrudService;
import com.lczyfz.edp.springboot.demo.sys.entity.QuestionGroup;
import com.lczyfz.edp.springboot.demo.sys.entity.QuestionGroupExample;
import com.lczyfz.edp.springboot.demo.sys.mapper.QuestionGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 86189
 */
@Service
@Transactional(readOnly = true)
public class QuestionGroupService extends CrudService<QuestionGroupMapper, QuestionGroup, QuestionGroupExample> {
    @Autowired
    QuestionGroupMapper questionGroupMapper;

    /**
     * 判断改题是否已经添加到该试卷中
     */
    public boolean judgeQuestion(Long paperId, Long questionId){
        QuestionGroupExample questionGroupExample=new QuestionGroupExample();

        QuestionGroupExample.Criteria criteria = questionGroupExample.createCriteria();

        criteria.andPaperIdEqualTo(paperId)
                .andQuestionIdEqualTo(questionId);

        List<QuestionGroup> questionGroups = questionGroupMapper.selectByExample(questionGroupExample);

        return questionGroups.size() != 0;
    }

}
