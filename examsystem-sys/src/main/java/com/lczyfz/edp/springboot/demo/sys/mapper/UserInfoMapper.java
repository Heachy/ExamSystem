package com.lczyfz.edp.springboot.demo.sys.mapper;

import com.lczyfz.edp.springboot.core.mapper.CrudMapper;
import com.lczyfz.edp.springboot.demo.sys.entity.UserInfo;
import com.lczyfz.edp.springboot.demo.sys.entity.UserInfoExample;
import com.lczyfz.edp.springboot.demo.sys.vo.StudentPageVO;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 86189
 */
@Mapper
@Resource
public interface UserInfoMapper extends CrudMapper<UserInfo,UserInfoExample> {

    List<StudentPageVO> getStudentList(int start, int pageSize);

}