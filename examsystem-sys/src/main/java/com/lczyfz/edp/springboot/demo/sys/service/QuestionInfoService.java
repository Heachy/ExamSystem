package com.lczyfz.edp.springboot.demo.sys.service;


import com.lczyfz.edp.springboot.core.entity.Page;
import com.lczyfz.edp.springboot.core.service.CrudService;
import com.lczyfz.edp.springboot.demo.sys.dto.LoginJudgeDTO;
import com.lczyfz.edp.springboot.demo.sys.entity.QuestionInfo;
import com.lczyfz.edp.springboot.demo.sys.entity.QuestionInfoExample;
import com.lczyfz.edp.springboot.demo.sys.mapper.QuestionInfoMapper;
import com.lczyfz.edp.springboot.demo.sys.vo.DetailedQuestionVO;
import com.lczyfz.edp.springboot.demo.sys.vo.QuestionPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 86189
 */
@Service
@Transactional(readOnly = true)
public class QuestionInfoService extends CrudService<QuestionInfoMapper, QuestionInfo, QuestionInfoExample> {
    @Autowired
    QuestionInfoMapper questionInfoMapper;

    @Autowired
    UserInfoService userInfoService;

    public static final int TEACHER_CORD=2;

    /**
     * 题目详细信息
     */
    public DetailedQuestionVO questionDetails(String userId, String password, long questionId){

        QuestionInfoExample questionInfoExample=new QuestionInfoExample();

        QuestionInfoExample.Criteria criteria = questionInfoExample.createCriteria();

        criteria.andIdEqualTo(questionId).andDelFlagEqualTo((byte) 0);

        List<QuestionInfo> questionInfos = questionInfoMapper.selectByExample(questionInfoExample);

        LoginJudgeDTO loginJudgeDTO = userInfoService.judgeUser(userId, password);
        if(!loginJudgeDTO.getJudge()||questionInfos.size()==0){
            return null;
        }

        boolean isTeacher= loginJudgeDTO.getRole() == TEACHER_CORD;

        return new DetailedQuestionVO(questionInfos.get(0),isTeacher);
    }

    /**
     * 题目页面列表
     */
    public Page<QuestionPageVO> getList(Page<QuestionPageVO> page,int pageNo,int pageSize){

        QuestionInfoExample questionInfoExample=new QuestionInfoExample();

        QuestionInfoExample.Criteria criteria = questionInfoExample.createCriteria();

        criteria.andDelFlagEqualTo((byte) 0);

        page.setCount(questionInfoMapper.countByExample(questionInfoExample));

        List<QuestionPageVO> pageList = questionInfoMapper.getPageList((pageNo - 1) * pageSize, pageSize);

        page.setList(pageList);

        return page;

    }
}
