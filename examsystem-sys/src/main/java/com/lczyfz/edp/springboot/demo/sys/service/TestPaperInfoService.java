package com.lczyfz.edp.springboot.demo.sys.service;

import com.lczyfz.edp.springboot.core.entity.Page;
import com.lczyfz.edp.springboot.core.service.CrudService;
import com.lczyfz.edp.springboot.demo.sys.entity.*;
import com.lczyfz.edp.springboot.demo.sys.mapper.QuestionInfoMapper;
import com.lczyfz.edp.springboot.demo.sys.mapper.TestPaperInfoMapper;
import com.lczyfz.edp.springboot.demo.sys.vo.SimpleQuestionVO;
import com.lczyfz.edp.springboot.demo.sys.vo.DetailedTestPaperVO;
import com.lczyfz.edp.springboot.demo.sys.vo.SimpleTestPaperVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 86189
 */
@Service
@Transactional(readOnly = true)
public class TestPaperInfoService extends CrudService<TestPaperInfoMapper, TestPaperInfo, TestPaperInfoExample> {

    @Autowired
    QuestionInfoMapper questionInfoMapper;

    @Autowired
    TestPaperInfoMapper testPaperInfoMapper;

    /**
     * 详细的考试信息
     */
    public DetailedTestPaperVO getTestPaper(Long testPaperId){
        TestPaperInfo testPaperInfo = testPaperInfoMapper.selectByPrimaryKey(testPaperId);

        List<SimpleQuestionVO> simpleQuestionVO = questionInfoMapper.questionInfoList(testPaperId);

        return new DetailedTestPaperVO(testPaperInfo, simpleQuestionVO);
    }

    /**
     * 简单的考试列表
     */
    public Page<SimpleTestPaperVO> getList(Page<SimpleTestPaperVO> page, int pageNo, int pageSize){

        TestPaperInfoExample testPaperInfoExample=new TestPaperInfoExample();

        TestPaperInfoExample.Criteria criteria = testPaperInfoExample.createCriteria();

        criteria.andDelFlagEqualTo((byte) 0);

        page.setCount(testPaperInfoMapper.countByExample(testPaperInfoExample));

        List<SimpleTestPaperVO> pageList = testPaperInfoMapper.getPageList((pageNo - 1) * pageSize, pageSize);

        page.setList(pageList);

        return page;

    }
}
