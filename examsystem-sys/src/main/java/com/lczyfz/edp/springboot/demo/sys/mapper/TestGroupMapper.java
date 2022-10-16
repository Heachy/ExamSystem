package com.lczyfz.edp.springboot.demo.sys.mapper;

import com.lczyfz.edp.springboot.core.mapper.CrudMapper;
import com.lczyfz.edp.springboot.demo.sys.entity.TestGroup;

import com.lczyfz.edp.springboot.demo.sys.entity.TestGroupExample;
import com.lczyfz.edp.springboot.demo.sys.vo.StuTestPageVO;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 86189
 */
@Mapper
@Resource
public interface TestGroupMapper extends CrudMapper<TestGroup, TestGroupExample> {

    int deleteByPrimaryKey(Long id);

    TestGroup selectByPrimaryKey(Long id);

    List<StuTestPageVO> getTestList(String studentId,int start,int pageSize);

    Long getPaperId(Long testGroupId);

}