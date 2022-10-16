package com.lczyfz.edp.springboot.demo.sys.mapper;

import com.lczyfz.edp.springboot.core.mapper.CrudMapper;
import com.lczyfz.edp.springboot.demo.sys.entity.QuestionGroup;
import com.lczyfz.edp.springboot.demo.sys.entity.QuestionGroupExample;
import java.util.List;

import com.lczyfz.edp.springboot.demo.sys.entity.QuestionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;

/**
 * @author 86189
 */
@Mapper
@Resource
public interface QuestionGroupMapper extends CrudMapper<QuestionGroup,QuestionGroupExample> {

    int deleteByPrimaryKey(Long id);

    QuestionGroup selectByPrimaryKey(Long id);



}