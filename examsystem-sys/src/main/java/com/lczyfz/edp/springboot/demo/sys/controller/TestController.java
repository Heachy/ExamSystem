package com.lczyfz.edp.springboot.demo.sys.controller;

import com.lczyfz.edp.springboot.core.controller.BaseController;
import com.lczyfz.edp.springboot.core.entity.CommonResult;
import com.lczyfz.edp.springboot.core.entity.Page;
import com.lczyfz.edp.springboot.core.entity.PageResult;
import com.lczyfz.edp.springboot.core.utils.MsgCodeUtils;
import com.lczyfz.edp.springboot.demo.sys.dto.LoginJudgeDTO;
import com.lczyfz.edp.springboot.demo.sys.entity.*;
import com.lczyfz.edp.springboot.demo.sys.mapper.CorrectInfoMapper;
import com.lczyfz.edp.springboot.demo.sys.mapper.TestGroupMapper;
import com.lczyfz.edp.springboot.demo.sys.mapper.TestInfoMapper;
import com.lczyfz.edp.springboot.demo.sys.mapper.UserInfoMapper;
import com.lczyfz.edp.springboot.demo.sys.service.TestGroupService;
import com.lczyfz.edp.springboot.demo.sys.service.TestInfoService;
import com.lczyfz.edp.springboot.demo.sys.service.TestPaperInfoService;
import com.lczyfz.edp.springboot.demo.sys.service.UserInfoService;
import com.lczyfz.edp.springboot.demo.sys.util.DelayUtils;
import com.lczyfz.edp.springboot.demo.sys.util.OssManagerUtil;
import com.lczyfz.edp.springboot.demo.sys.vo.DetailedTestPaperVO;
import com.lczyfz.edp.springboot.demo.sys.vo.PublishPageVO;
import com.lczyfz.edp.springboot.demo.sys.vo.SubmitPaperVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author 86189
 */
@RestController
@RequestMapping("/test")
@Api(value = "TestController",tags = "考试接口")
public class TestController extends BaseController {
    @Autowired
    TestInfoService testInfoService;

    @Autowired
    TestPaperInfoService testPaperInfoService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    TestGroupService testGroupService;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    TestInfoMapper testInfoMapper;

    @Autowired
    TestGroupMapper testGroupMapper;

    @Autowired
    CorrectInfoMapper correctInfoMapper;

    private static final String END_STATUS = "end";

    private static final String NOT_START_STATUS = "notStart";

    @ApiOperation(value = "新建考试",notes = "仅限教师创建考试")
    @PostMapping(value = "/add",consumes = "multipart/form-data")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "testName",value = "考试名称",readOnly = true),
                    @ApiImplicitParam(name = "paperId",value = "选用的试卷id",readOnly = true),
                    @ApiImplicitParam(name = "timeLimit",value = "时间限制",readOnly = true),
                    @ApiImplicitParam(name = "remarks",value = "备注",readOnly = true)
            }
    )
    @ResponseBody
    public CommonResult addTestPaper(@RequestHeader String userId, @RequestHeader String password, String testName,
                                     Long paperId,int timeLimit,String remarks) {

        CommonResult result = userInfoService.getResultMes(userId, password);

        if(!result.isSuccess()){
            logger.info(result.getErrMsg());
        }else{

            TestInfo testInfo=new TestInfo(userId,testName,paperId,timeLimit,remarks);

            testInfoMapper.insert(testInfo);

            result.success("testInfo", testInfo);

            logger.info("新增成功！");
        }
        return result;
    }

    @ApiOperation(value = "发布考试",notes = "仅限教师发布考试")
    @RequestMapping(value = "/releaseTest",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "testId",value = "考试id",readOnly = true),
                    @ApiImplicitParam(name = "idList",value = "学生Id数组",readOnly = true,allowMultiple = true,paramType = "query",dataType = "string"),
            }
    )
    public CommonResult deleteTestQuestionList(@RequestHeader String userId, @RequestHeader String password,Long testId,
                                               @ApiParam(value = "学生Id数组") @RequestParam("idList") List<String> idList) {
        CommonResult result = userInfoService.getResultMes(userId, password);

        if(!result.isSuccess()){
            logger.info(result.getErrMsg());
        }else{
            for (String id : idList) {
                testGroupMapper.insert(new TestGroup(userId,testId,id));
            }
            logger.info("发布成功！");
        }
        return result;
    }

    @ApiOperation(value = "判断是否发布过",notes = "选择发布的学生时进行判断（单人）")
    @RequestMapping(value = "/judgeStudent",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "testId",value = "考试id",readOnly = true),
                    @ApiImplicitParam(name = "studentId",value = "学生id",readOnly = true)
            }
    )
    public CommonResult questionJudge(@RequestHeader String userId, @RequestHeader String password,Long testId,String studentId){
        CommonResult result = userInfoService.getResultMes(userId, password);

        if(!result.isSuccess()){
            logger.info(result.getErrMsg());
        }else{
            boolean isExist= testGroupService.judgeTestGroup(testId,studentId);

            result.success("isExist",isExist);
        }
        return result;
    }



    @ApiOperation(value = "试卷信息",notes = "从考试查看试卷,学生老师均可查看，学生可以提交答案")
    @RequestMapping(value = "/paper",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "testId",value = "考试id",readOnly = true)
            }
    )
    public DetailedTestPaperVO testPaperDetail(@RequestHeader String userId, @RequestHeader String password, Long testId){
        CommonResult result = userInfoService.getResultMes(userId, password);

        LoginJudgeDTO judgeDTO = userInfoService.judgeUser(userId, password);

        if(!judgeDTO.getJudge()){
            result.fail(MsgCodeUtils.MSG_CODE_USERNAME_OR_PASSWORD_INCORRECT);

            logger.info(result.getErrMsg());
        }else{
            TestInfo testInfo = testInfoMapper.selectByPrimaryKey(testId);

            DetailedTestPaperVO testPaper = testPaperInfoService.getTestPaper(testInfo.getPaperId());

            if(judgeDTO.getRole()==1){
                testPaper.setIsTeacher(false);
            }

            return testPaper;
        }
        return null;
    }

    @ApiOperation(value = "学生开始考试", notes = "学生开始考试接口")
    @RequestMapping(value = "/startTest",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "testGroupId",value = "考试组号id",readOnly = true)
            }
    )
    public CommonResult startTest(@RequestHeader String userId, @RequestHeader String password, Long testGroupId){
        CommonResult result=new CommonResult().init();

        LoginJudgeDTO judgeDTO = userInfoService.judgeUser(userId, password);

        if(!judgeDTO.getJudge()){
            result.fail(MsgCodeUtils.MSG_CODE_USERNAME_OR_PASSWORD_INCORRECT);

            logger.info(result.getErrMsg());
        }else if (judgeDTO.getRole()!=1){
            result.fail(MsgCodeUtils.MSG_CODE_SYSTEM_NOT_SUPER_ADMIN_PERMISSION);

            logger.info(result.getErrMsg());
        }else{
            TestGroup testGroup = testGroupMapper.selectByPrimaryKey(testGroupId);

            TestInfo testInfo = testInfoMapper.selectByPrimaryKey(testGroup.getTestId());

            testGroup.setStatus("ing");

            testGroup.setNewRecord(false);

            testGroup.setUpdateDate(new Date());

            testGroupMapper.updateByPrimaryKey(testGroup);

            Map<String,Object> map=new HashMap<>();

            UUID uuid = UUID.randomUUID();

            String id = uuid.toString();

            map.put("id",id);

            map.put("type",1);

            map.put("testGroupId",testGroupId);

            map.put("stuId",userId);

            DelayUtils.delayUtils.setDelay(id,map,testInfo.getTimeLimit()*60*1000 );

        }
        return result;
    }

    @ApiOperation(value = "提交答案",notes = "学生提交答案接口")
    @PostMapping(value = "/subAnswer",consumes = "multipart/form-data")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "testGroupId",value = "考试组号id",readOnly = true),
            }
    )
    @ResponseBody
    public CommonResult subAnswer(@RequestHeader String userId, @RequestHeader String password,Long testGroupId,
                                       @RequestPart(value = "file") @ApiParam("答案图片") MultipartFile file) {
        CommonResult result=new CommonResult().init();

        LoginJudgeDTO judgeDTO = userInfoService.judgeUser(userId, password);

        if(!judgeDTO.getJudge()){
            result.fail(MsgCodeUtils.MSG_CODE_USERNAME_OR_PASSWORD_INCORRECT);

            logger.info(result.getErrMsg());
        }else if (judgeDTO.getRole()!=1){
            result.fail(MsgCodeUtils.MSG_CODE_SYSTEM_NOT_SUPER_ADMIN_PERMISSION);

            logger.info(result.getErrMsg());
        }else{
            TestGroup testGroup = testGroupMapper.selectByPrimaryKey(testGroupId);

            if(END_STATUS.equals(testGroup.getStatus())){
                result.fail(MsgCodeUtils.MSC_DATA_ADDDATA_ERROR);

                logger.info("答题时间已结束");
                return result;
            }

            String pictureUrl= OssManagerUtil.getUrl(file);

            CorrectInfo correctInfo=new CorrectInfo(userId,pictureUrl,testGroupId);

            correctInfoMapper.insert(correctInfo);

            Map<String,Object> map=new HashMap<>();

            UUID uuid = UUID.randomUUID();

            String id = uuid.toString();

            map.put("id",id);

            map.put("testGroupId",testGroupId);

            map.put("type",2);

            TestInfo testInfo = testInfoMapper.selectByPrimaryKey(testGroup.getTestId());

            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(testInfo.getCreateBy());

            map.put("email",userInfo.getMail());

            map.put("testName",testInfo.getTestName());

            testGroup.setStatus("waitCorrect");

            testGroup.setNewRecord(false);

            testGroupMapper.updateByPrimaryKey(testGroup);

            DelayUtils.delayUtils.setDelay(id,map,50*60*1000 );

        }
        return result;
    }


    @ApiOperation(value = "教师查看自己发布的考试", notes = "仅限教师查看自己发布的考试信息")
    @RequestMapping(value = "/publishList",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "pageNo",value = "要获得的页码",readOnly = true),
                    @ApiImplicitParam(name = "pageSize",value = "要获得的页面数据数量",readOnly = true)
            }
    )
    public PageResult<PublishPageVO> getTeacherTests(@RequestHeader String userId, @RequestHeader String password, int pageNo,int pageSize){
        PageResult<PublishPageVO> result = new PageResult<PublishPageVO>().init();

        CommonResult commonResult=userInfoService.getResultMes(userId,password);
        if(!commonResult.isSuccess()){
            result.fail(commonResult.getMsgCode());

            result.setErrMsg(result.getErrMsg());
        }else{
            Page<PublishPageVO> page = new Page<>(pageNo,pageSize, "");

            result.success(testGroupService.getPublishList(page,userId,pageNo,pageSize));

            return (PageResult<PublishPageVO>) result.end();
        }
        return result;
    }
    @ApiOperation(value = "撤销发布考试", notes = "仅限教师撤销发布考试")
    @RequestMapping(value = "/cancelPub",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "testGroupId",value = "考试组号",readOnly = true)
            }
    )
    public CommonResult cancelPub(@RequestHeader String userId, @RequestHeader String password,Long testGroupId){
        CommonResult result = userInfoService.getResultMes(userId, password);

        if(!result.isSuccess()){
            logger.info(result.getErrMsg());
        }else{
            TestGroup testGroup = testGroupMapper.selectByPrimaryKey(testGroupId);

            if(!NOT_START_STATUS.equals(testGroup.getStatus())){
                result.fail(MsgCodeUtils.MSG_DATA_NOT_ALLOW_DELETE);

                logger.info("该学生已开始考试，无法撤销发布。");
            }else{
                testGroupService.delete(testGroup);
            }
        }
        return result;
    }

    @ApiOperation(value = "已提交答案的试卷信息",notes = "获得已提交答案的试卷信息，若correctFlag为true，显示批改完，可显示成绩，false为待批改，不显示成绩")
    @RequestMapping(value = "/subDetail",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
            @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
            @ApiImplicitParam(name = "testGroupId",value = "考试组号id",readOnly = true)
    }
    )
    public SubmitPaperVO correctPaperDetail(@RequestHeader String userId, @RequestHeader String password, Long testGroupId){
        LoginJudgeDTO judgeDTO = userInfoService.judgeUser(userId, password);

        if(!judgeDTO.getJudge()){
            logger.info("账号或密码错误");

            return null;
        }else{

            CorrectInfoExample correctInfoExample=new CorrectInfoExample();

            CorrectInfoExample.Criteria criteria = correctInfoExample.createCriteria();

            criteria.andTestGroupIdEqualTo(testGroupId);

            List<CorrectInfo> correctInfos = correctInfoMapper.selectByExample(correctInfoExample);

            CorrectInfo correctInfo = correctInfos.get(0);

            return new SubmitPaperVO(testPaperInfoService.getTestPaper(testGroupMapper.getPaperId(testGroupId)), correctInfo.getAnswerPicture(),
                    Long.valueOf(correctInfo.getId()),(int) correctInfo.getScore(),correctInfo.getCorrectFlag());
        }
    }
}
