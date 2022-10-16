package com.lczyfz.edp.springboot.demo.sys.service;

import com.lczyfz.edp.springboot.core.entity.Page;
import com.lczyfz.edp.springboot.core.service.CrudService;
import com.lczyfz.edp.springboot.demo.sys.entity.TestInfo;
import com.lczyfz.edp.springboot.demo.sys.entity.TestInfoExample;
import com.lczyfz.edp.springboot.demo.sys.mapper.TestInfoMapper;
import com.lczyfz.edp.springboot.demo.sys.vo.TeacherTestPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86189
 */
@Service
public class TestInfoService extends CrudService<TestInfoMapper, TestInfo, TestInfoExample> {

    @Autowired
    TestInfoMapper testInfoMapper;

    /**
     * 获得老师自己创建过的所有考试列表
     */
    public Page<TeacherTestPageVO> getList(Page<TeacherTestPageVO> page, String teacherId, int pageNo, int pageSize){

        TestInfoExample testGroupExample=new TestInfoExample();

        TestInfoExample.Criteria criteria = testGroupExample.createCriteria();

        criteria.andDelFlagEqualTo((byte) 0)
                .andCreateByEqualTo(teacherId);

        page.setCount(testInfoMapper.countByExample(testGroupExample));

        List<TeacherTestPageVO> pageList = testInfoMapper.getTestList(teacherId,(pageNo - 1) * pageSize, pageSize);

        page.setList(pageList);

        return page;

    }
}
