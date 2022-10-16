package com.lczyfz.edp.springboot.demo.sys.service;


import com.lczyfz.edp.springboot.core.entity.Page;
import com.lczyfz.edp.springboot.core.service.CrudService;
import com.lczyfz.edp.springboot.demo.sys.entity.TestGroup;
import com.lczyfz.edp.springboot.demo.sys.entity.TestGroupExample;
import com.lczyfz.edp.springboot.demo.sys.mapper.TestGroupMapper;
import com.lczyfz.edp.springboot.demo.sys.vo.StuTestPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author 86189
 */
@Service
public class TestGroupService extends CrudService<TestGroupMapper, TestGroup, TestGroupExample> {

    @Autowired
    TestGroupMapper testGroupMapper;

    /**
     * 判断该考试是否已经发布给该学生
     */
    public boolean judgeTestGroup(Long testId, String studentId){
        TestGroupExample testGroupExample=new TestGroupExample();

        TestGroupExample.Criteria criteria = testGroupExample.createCriteria();


        criteria.andStudentIdEqualTo(studentId)
                .andTestIdEqualTo(testId);

        return testGroupMapper.countByExample(testGroupExample) != 0;
    }

    /**
     * 获得学生所拥有的考试列表
     */
    public Page<StuTestPageVO> getList(Page<StuTestPageVO> page,String studentId, int pageNo, int pageSize){

        TestGroupExample testGroupExample=new TestGroupExample();

        TestGroupExample.Criteria criteria = testGroupExample.createCriteria();

        criteria.andDelFlagEqualTo((byte) 0)
                .andStudentIdEqualTo(studentId);

        page.setCount(testGroupMapper.countByExample(testGroupExample));

        List<StuTestPageVO> pageList = testGroupMapper.getTestList(studentId,(pageNo - 1) * pageSize, pageSize);

        page.setList(pageList);

        return page;

    }
}
