package com.lczyfz.edp.springboot.demo.sys.controller;

import com.lczyfz.edp.springboot.core.controller.BaseController;
import com.lczyfz.edp.springboot.core.entity.CommonResult;
import com.lczyfz.edp.springboot.core.entity.Page;
import com.lczyfz.edp.springboot.core.entity.PageResult;
import com.lczyfz.edp.springboot.core.utils.MsgCodeUtils;
import com.lczyfz.edp.springboot.demo.sys.dto.LoginJudgeDTO;
import com.lczyfz.edp.springboot.demo.sys.entity.UserInfo;
import com.lczyfz.edp.springboot.demo.sys.service.TestGroupService;
import com.lczyfz.edp.springboot.demo.sys.service.TestInfoService;
import com.lczyfz.edp.springboot.demo.sys.service.UserInfoService;
import com.lczyfz.edp.springboot.demo.sys.vo.StuTestPageVO;
import com.lczyfz.edp.springboot.demo.sys.vo.StudentPageVO;
import com.lczyfz.edp.springboot.demo.sys.vo.TeacherTestPageVO;
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

/**
 * @author 86189
 */
@RestController
@RequestMapping("/user")
@Api(value = "UserController",tags = "用户接口")
public class UserInfoController extends BaseController {
    @Autowired
    UserInfoService userInfoService;

    @Autowired
    TestInfoService testInfoService;

    @Autowired
    TestGroupService testGroupService;


    @ApiOperation(value = "学生列表", notes = "仅限老师查询学生列表")
    @RequestMapping(value = "/students",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "pageNo",value = "要获得的页码",readOnly = true),
                    @ApiImplicitParam(name = "pageSize",value = "要获得的页面数据数量",readOnly = true)
            }
    )
    public PageResult<StudentPageVO> selectAllByPage(@RequestHeader String userId, @RequestHeader String password, int pageNo,int pageSize){
        PageResult<StudentPageVO> result = new PageResult<StudentPageVO>().init();

        CommonResult commonResult = userInfoService.getResultMes(userId, password);

        if(!commonResult.isSuccess()){
            result.fail(commonResult.getMsgCode());

            logger.info(commonResult.getErrMsg());
            return result;
        }else{
            Page<StudentPageVO> page = new Page<>(pageNo,pageSize, "");

            result.success(userInfoService.getStudentList(page,pageNo,pageSize));

            return (PageResult<StudentPageVO>) result.end();
        }
    }

    @ApiOperation(value = "更改个人邮箱", notes = "学生老师都可以自己改")
    @RequestMapping(value = "/updateMail",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "newMail",value = "新邮箱",readOnly = true),
            }
    )
    public CommonResult updateMail(@RequestHeader String userId, @RequestHeader String password,String newMail){

        LoginJudgeDTO loginJudgeDTO=userInfoService.judgeUser(userId, password);

        CommonResult result = new CommonResult().init();
        if(!loginJudgeDTO.getJudge()){
            result.fail(MsgCodeUtils.MSG_CODE_USERNAME_OR_PASSWORD_INCORRECT);
        }else{
            UserInfo userInfo=new UserInfo();

            userInfo.setId(userId);

            userInfo.setMail(newMail);

            userInfo.setUpdateDate(new Date());

            userInfo.setUpdateBy(userId);

            userInfo.setNewRecord(false);

            userInfoService.save(userInfo);

            result.success("userInfo",userInfo);

            logger.info("修改成功！");
        }
        return result;
    }

    @ApiOperation(value = "学生考试列表", notes = "学生所参加的考试列表")
    @RequestMapping(value = "/stuTests",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "pageNo",value = "要获得的页码",readOnly = true),
                    @ApiImplicitParam(name = "pageSize",value = "要获得的页面数据数量",readOnly = true)
            }
    )
    public PageResult<StuTestPageVO> getStuTests(@RequestHeader String userId, @RequestHeader String password, int pageNo,int pageSize){
        PageResult<StuTestPageVO> result = new PageResult<StuTestPageVO>().init();

        LoginJudgeDTO judgeDTO = userInfoService.judgeUser(userId, password);

        if(!judgeDTO.getJudge()){
            result.fail(MsgCodeUtils.MSG_CODE_USERNAME_OR_PASSWORD_INCORRECT);

            logger.info(result.getErrMsg());
        }else if (judgeDTO.getRole()!=1){
            result.fail(MsgCodeUtils.MSG_CODE_SYSTEM_NOT_SUPER_ADMIN_PERMISSION);

            logger.info(result.getErrMsg());
        }else{
            Page<StuTestPageVO> page = new Page<>(pageNo,pageSize, "");

            result.success(testGroupService.getStuList(page,userId,pageNo,pageSize));

            return (PageResult<StuTestPageVO>) result.end();
        }
        return result;
    }

    @ApiOperation(value = "教师考试列表", notes = "教师所创建的考试列表")
    @RequestMapping(value = "/teacherTests",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "pageNo",value = "要获得的页码",readOnly = true),
                    @ApiImplicitParam(name = "pageSize",value = "要获得的页面数据数量",readOnly = true)
            }
    )
    public PageResult<TeacherTestPageVO> getTeacherTests(@RequestHeader String userId, @RequestHeader String password, int pageNo,int pageSize){
        PageResult<TeacherTestPageVO> result = new PageResult<TeacherTestPageVO>().init();

        CommonResult commonResult=userInfoService.getResultMes(userId,password);
        if(!commonResult.isSuccess()){
            result.fail(commonResult.getMsgCode());

            result.setErrMsg(result.getErrMsg());
        }else{
            Page<TeacherTestPageVO> page = new Page<>(pageNo,pageSize, "");

            result.success(testInfoService.getList(page,userId,pageNo,pageSize));

            return (PageResult<TeacherTestPageVO>) result.end();
        }
        return result;
    }
}
