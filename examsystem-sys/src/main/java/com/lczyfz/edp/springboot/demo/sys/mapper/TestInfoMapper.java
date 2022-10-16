package com.lczyfz.edp.springboot.demo.sys.mapper;

import com.lczyfz.edp.springboot.core.mapper.CrudMapper;
import com.lczyfz.edp.springboot.demo.sys.entity.TestInfo;
import com.lczyfz.edp.springboot.demo.sys.entity.TestInfoExample;
import java.util.List;

import com.lczyfz.edp.springboot.demo.sys.vo.TeacherTestPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;

@Mapper
@Resource
public interface TestInfoMapper extends CrudMapper<TestInfo,TestInfoExample> {

    int deleteByPrimaryKey(Long id);


    TestInfo selectByPrimaryKey(Long id);

    List<TeacherTestPageVO> getTestList(String teacherId,int start,int pageSize);
}