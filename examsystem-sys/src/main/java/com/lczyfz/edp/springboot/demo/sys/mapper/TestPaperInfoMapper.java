package com.lczyfz.edp.springboot.demo.sys.mapper;

import com.lczyfz.edp.springboot.core.mapper.CrudMapper;
import com.lczyfz.edp.springboot.demo.sys.entity.TestPaperInfo;
import com.lczyfz.edp.springboot.demo.sys.entity.TestPaperInfoExample;
import com.lczyfz.edp.springboot.demo.sys.vo.SimpleTestPaperVO;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 86189
 */
@Mapper
@Resource
public interface TestPaperInfoMapper extends CrudMapper<TestPaperInfo,TestPaperInfoExample> {

    int deleteByPrimaryKey(Long id);

    TestPaperInfo selectByPrimaryKey(Long id);

    List<SimpleTestPaperVO> getPageList(int start,int pageSize);

}