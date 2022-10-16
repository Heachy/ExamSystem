package com.lczyfz.edp.springboot.demo.sys.mapper;

import com.lczyfz.edp.springboot.core.mapper.CrudMapper;
import com.lczyfz.edp.springboot.demo.sys.entity.CorrectInfo;
import com.lczyfz.edp.springboot.demo.sys.entity.CorrectInfoExample;
import com.lczyfz.edp.springboot.demo.sys.vo.CorrectPageVO;
import org.apache.ibatis.annotations.Mapper;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author 86189
 */
@Mapper
@Resource
public interface CorrectInfoMapper extends CrudMapper<CorrectInfo,CorrectInfoExample> {

    int deleteByPrimaryKey(Long id);

    CorrectInfo selectByPrimaryKey(Long id);

    /**
     * 获得要批改的试卷列表
     * @param teacherId 老师id
     * @param start 开始
     * @param pageSize 每页的大小
     */
    List<CorrectPageVO> getList(String teacherId, int start, int pageSize);
}