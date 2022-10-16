package com.lczyfz.edp.springboot.demo.sys.mapper;

import com.lczyfz.edp.springboot.core.mapper.CrudMapper;
import com.lczyfz.edp.springboot.demo.sys.entity.QuestionInfo;
import com.lczyfz.edp.springboot.demo.sys.entity.QuestionInfoExample;
import com.lczyfz.edp.springboot.demo.sys.vo.QuestionPageVO;
import com.lczyfz.edp.springboot.demo.sys.vo.SimpleQuestionVO;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 86189
 */
@Mapper
@Resource
public interface QuestionInfoMapper extends CrudMapper<QuestionInfo,QuestionInfoExample> {

    int deleteByPrimaryKey(Long id);

    List<SimpleQuestionVO> questionInfoList(Long paperId);

    /**
     * 获得题目列表
     * @param start 开始
     * @param pageSize 每页的大小
     */
    List<QuestionPageVO> getPageList (int start, int pageSize);

    QuestionInfo selectByPrimaryKey(Long id);

}