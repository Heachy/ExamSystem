package com.lczyfz.edp.springboot.demo.sys.service;

import com.lczyfz.edp.springboot.core.entity.Page;
import com.lczyfz.edp.springboot.core.service.CrudService;
import com.lczyfz.edp.springboot.demo.sys.entity.CorrectInfo;
import com.lczyfz.edp.springboot.demo.sys.entity.CorrectInfoExample;
import com.lczyfz.edp.springboot.demo.sys.mapper.CorrectInfoMapper;
import com.lczyfz.edp.springboot.demo.sys.vo.CorrectPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86189
 */
@Service
public class CorrectInfoService extends CrudService<CorrectInfoMapper, CorrectInfo, CorrectInfoExample> {

    @Autowired
    CorrectInfoMapper correctInfoMapper;

    /**
     * 获得要批改的试卷列表
     */
    public Page<CorrectPageVO> getList(Page<CorrectPageVO> page, String teacherId, int pageNo, int pageSize){

        List<CorrectPageVO> pageList = correctInfoMapper.getList(teacherId,(pageNo - 1) * pageSize, pageSize);

        page.setCount(pageList.size());

        page.setList(pageList);

        return page;

    }

}
