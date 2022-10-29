package com.lczyfz.edp.springboot.demo.sys.controller;

import com.lczyfz.edp.springboot.core.controller.BaseController;
import com.lczyfz.edp.springboot.core.entity.CommonResult;
import com.lczyfz.edp.springboot.core.entity.Page;
import com.lczyfz.edp.springboot.core.entity.PageResult;
import com.lczyfz.edp.springboot.demo.sys.dto.LoginJudgeDTO;
import com.lczyfz.edp.springboot.demo.sys.entity.CorrectInfo;
import com.lczyfz.edp.springboot.demo.sys.entity.CorrectInfoExample;
import com.lczyfz.edp.springboot.demo.sys.entity.TestGroup;
import com.lczyfz.edp.springboot.demo.sys.mapper.CorrectInfoMapper;
import com.lczyfz.edp.springboot.demo.sys.mapper.TestGroupMapper;
import com.lczyfz.edp.springboot.demo.sys.service.CorrectInfoService;
import com.lczyfz.edp.springboot.demo.sys.service.TestGroupService;
import com.lczyfz.edp.springboot.demo.sys.service.TestPaperInfoService;
import com.lczyfz.edp.springboot.demo.sys.service.UserInfoService;
import com.lczyfz.edp.springboot.demo.sys.vo.CorrectPageVO;
import com.lczyfz.edp.springboot.demo.sys.vo.SubmitPaperVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author 86189
 */
@RestController
@RequestMapping("/correct")
@Api(value = "CorrectController",tags = "试卷批改接口")
public class CorrectController extends BaseController {
    @Autowired
    UserInfoService userInfoService;

    @Autowired
    TestGroupService testGroupService;

    @Autowired
    CorrectInfoMapper correctInfoMapper;

    @Autowired
    CorrectInfoService correctInfoService;

    @Autowired
    TestGroupMapper testGroupMapper;

    @Autowired
    TestPaperInfoService testPaperInfoService;

    @ApiOperation(value = "待批改试卷列表",notes = "获得待批改试卷的列表")
    @RequestMapping(value = "/paperList",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
            @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
            @ApiImplicitParam(name = "pageNo",value = "要获得的页码",readOnly = true),
            @ApiImplicitParam(name = "pageSize",value = "要获得的页面数据数量",readOnly = true)
    }
    )
    public PageResult<CorrectPageVO> correctList(@RequestHeader String userId, @RequestHeader String password, int pageNo,int pageSize){
        PageResult<CorrectPageVO> result = new PageResult<CorrectPageVO>().init();
        CommonResult commonResult = userInfoService.getResultMes(userId, password);

        if(!commonResult.isSuccess()){
            result.fail(commonResult.getMsgCode());

            logger.info(commonResult.getErrMsg());
            return result;
        }else{
            Page<CorrectPageVO> page = new Page<>(pageNo,pageSize, "");

            result.success(correctInfoService.getList(page,userId,pageNo,pageSize));

            return (PageResult<CorrectPageVO>) result.end();
        }
    }


    @ApiOperation(value = "评分",notes = "为学生的答案评分")
    @RequestMapping(value = "/setScore",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
            @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
            @ApiImplicitParam(name = "score",value = "成绩",readOnly = true),
            @ApiImplicitParam(name = "correctId",value = "改的试卷id",readOnly = true)
    }
    )
    public CommonResult correctPaper(@RequestHeader String userId, @RequestHeader String password, int score,Long correctId){
        CommonResult result = userInfoService.getResultMes(userId, password);

        if(!result.isSuccess()){
            logger.info(result.getErrMsg());
        }else{

            CorrectInfo correctInfo=new CorrectInfo();

            correctInfo.setId(correctId.toString());

            correctInfo.setScore((byte) score);

            correctInfo.setNewRecord(false);

            correctInfo.setCorrectFlag(true);

            correctInfo.setUpdateDate(new Date());

            correctInfo.setUpdateBy(userId);

            correctInfoService.save(correctInfo);

            CorrectInfo info = correctInfoMapper.selectByPrimaryKey(correctId);

            TestGroup testGroup=new TestGroup();

            testGroup.setUpdateDate(new Date());

            testGroup.setId(info.getTestGroupId().toString());

            testGroup.setStatus("end");

            testGroup.setNewRecord(false);

            testGroupService.save(testGroup);

            logger.info("批改成功");
        }
        return result;
    }

}
