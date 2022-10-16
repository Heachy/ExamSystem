package com.lczyfz.edp.springboot.demo.sys.controller;

import com.lczyfz.edp.springboot.core.controller.BaseController;
import com.lczyfz.edp.springboot.core.entity.CommonResult;
import com.lczyfz.edp.springboot.core.entity.Page;
import com.lczyfz.edp.springboot.core.entity.PageResult;
import com.lczyfz.edp.springboot.demo.sys.entity.QuestionGroup;
import com.lczyfz.edp.springboot.demo.sys.entity.TestPaperInfo;
import com.lczyfz.edp.springboot.demo.sys.mapper.QuestionGroupMapper;
import com.lczyfz.edp.springboot.demo.sys.mapper.TestPaperInfoMapper;
import com.lczyfz.edp.springboot.demo.sys.service.QuestionGroupService;
import com.lczyfz.edp.springboot.demo.sys.service.TestPaperInfoService;
import com.lczyfz.edp.springboot.demo.sys.service.UserInfoService;
import com.lczyfz.edp.springboot.demo.sys.vo.DetailedTestPaperVO;
import com.lczyfz.edp.springboot.demo.sys.vo.SimpleTestPaperVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 86189
 */
@RestController
@RequestMapping("/testPaper")
@Api(value = "testPaperController",tags = "试卷接口")
public class TestPaperController extends BaseController {

    @Autowired
    TestPaperInfoService testPaperInfoService;

    @Autowired
    TestPaperInfoMapper testPaperInfoMapper;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    QuestionGroupMapper questionGroupMapper;

    @Autowired
    QuestionGroupService questionGroupService;

    @ApiOperation(value = "新建试卷",notes = "仅限教师创建试卷")
    @PostMapping(value = "/addPaper",consumes = "multipart/form-data")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "paperName",value = "试卷名称",readOnly = true),
                    @ApiImplicitParam(name = "remarks",value = "备注",readOnly = true)
            }
    )
    @ResponseBody
    public CommonResult addTestPaper(@RequestHeader String userId, @RequestHeader String password, String paperName, String remarks) {

        CommonResult result = userInfoService.getResultMes(userId, password);

        if(!result.isSuccess()){
            logger.info(result.getErrMsg());
        }else{
            TestPaperInfo testPaperInfo = new TestPaperInfo(userId, paperName, remarks);

            testPaperInfoMapper.insert(testPaperInfo);

            result.success("testPaperInfo", testPaperInfo);

            logger.info("新增成功！");
        }
        return result;
    }

    @ApiOperation(value = "对试卷增加题目",notes = "仅限教师增加试题")
    @PostMapping(value = "/addQuestions",consumes = "multipart/form-data")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "paperId",value = "试卷id",readOnly = true),
                    @ApiImplicitParam(name = "idList",value = "题目id数组",readOnly = true,allowMultiple = true,paramType = "query",dataType = "Long"),
                    @ApiImplicitParam(name = "scoreList",value = "题目分数数组",readOnly = true,allowMultiple = true,paramType = "query",dataType = "Byte")
            }
    )
    public CommonResult addTestQuestionList(@RequestHeader String userId, @RequestHeader String password, Long paperId,
                                            @ApiParam(value = "题目id数组") @RequestParam("idList") List<Integer> idList,
                                            @ApiParam(value = "题目分数数组") @RequestParam("scoreList") List<Integer> scoreList) {
        CommonResult result = userInfoService.getResultMes(userId, password);

        if(!result.isSuccess()){
            logger.info(result.getErrMsg());
        }else{
            for (int i=0;i<idList.size();i++) {
                questionGroupMapper.insert(new QuestionGroup(userId,paperId, idList.get(i),(byte) (int)scoreList.get(i)));
            }
            logger.info("新增成功！");
        }
        return result;
    }

    @ApiOperation(value = "试卷信息",notes = "教师查看试卷专用通道")
    @RequestMapping(value = "/details",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "paperId",value = "试卷id",readOnly = true)
            }
    )
    public DetailedTestPaperVO testPaperDetail(@RequestHeader String userId, @RequestHeader String password, Long paperId){
        CommonResult result = userInfoService.getResultMes(userId, password);

        if(!result.isSuccess()){
            logger.info(result.getErrMsg());
        }else{
            return testPaperInfoService.getTestPaper(paperId);
        }
        return null;
    }
    @ApiOperation(value = "判断试题添加",notes = "往试卷里头添加试题试题时进行判断（单题）")
    @RequestMapping(value = "/judgeQuestion",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "paperId",value = "试卷id",readOnly = true),
                    @ApiImplicitParam(name = "questionId",value = "问题id",readOnly = true)
            }
    )
    public CommonResult questionJudge(@RequestHeader String userId, @RequestHeader String password,Long paperId,Long questionId){
        CommonResult result = userInfoService.getResultMes(userId, password);

        if(!result.isSuccess()){
            logger.info(result.getErrMsg());
        }else{
            boolean isExist=questionGroupService.judgeQuestion(paperId,questionId);

            result.success("isExist",isExist);
        }
        return result;
    }

    @ApiOperation(value = "对试卷删除题目",notes = "仅限教师删除试题")
    @RequestMapping(value = "/deleteQuestions",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "idList",value = "题目groupId数组",readOnly = true,allowMultiple = true,paramType = "query",dataType = "Long"),
            }
    )
    public CommonResult deleteTestQuestionList(@RequestHeader String userId, @RequestHeader String password,
                                            @ApiParam(value = "题目groupId数组") @RequestParam("idList") List<Integer> idList) {
        CommonResult result = userInfoService.getResultMes(userId, password);

        if(!result.isSuccess()){
            logger.info(result.getErrMsg());
        }else{
            for (Integer integer : idList) {
                questionGroupMapper.deleteByPrimaryKey(Long.valueOf(integer));
            }
            logger.info("删除成功！");
        }
        return result;
    }

    @ApiOperation(value = "修改试卷题目分数",notes = "仅限教师修改试题分数")
    @RequestMapping(value = "/updateScores",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "groupIdList",value = "题目所属id数组",readOnly = true,allowMultiple = true,paramType = "query",dataType = "Long"),
                    @ApiImplicitParam(name = "scoreList",value = "题目分数数组",readOnly = true,allowMultiple = true,paramType = "query",dataType = "Byte")
            }
    )
    public CommonResult updateTestQuestionList(@RequestHeader String userId, @RequestHeader String password,
                                            @ApiParam(value = "题目id数组") @RequestParam("groupIdList") List<Integer> groupIdList,
                                            @ApiParam(value = "题目分数数组") @RequestParam("scoreList") List<Integer> scoreList) {

        CommonResult result = userInfoService.getResultMes(userId, password);

        if(!result.isSuccess()){
            logger.info(result.getErrMsg());
        }else{
             for(int i=0;i<groupIdList.size();i++){
                 QuestionGroup questionGroup=new QuestionGroup();

                 questionGroup.setScore((byte)(int)scoreList.get(i));

                 questionGroup.setId(groupIdList.get(i).toString());

                 questionGroup.setNewRecord(false);

                 questionGroup.setUpdateBy(userId);

                 questionGroupService.save(questionGroup);
             }

            logger.info("修改成功");
        }

        return result;
    }

    @ApiOperation(value = "试卷库", notes = "仅限老师查询试卷库")
    @RequestMapping(value = "/selectAll",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "pageNo",value = "要获得的页码",readOnly = true)
            }
    )
    public PageResult<SimpleTestPaperVO> selectAllByPage(@RequestHeader String userId, @RequestHeader String password, int pageNo){
        PageResult<SimpleTestPaperVO> result = new PageResult<SimpleTestPaperVO>().init();
        CommonResult commonResult = userInfoService.getResultMes(userId, password);

        if(!commonResult.isSuccess()){
            result.fail(commonResult.getMsgCode());

            logger.info(commonResult.getErrMsg());
            return result;
        }else{
            Page<SimpleTestPaperVO> page = new Page<>(pageNo,5, "");

            result.success(testPaperInfoService.getList(page,pageNo,5));

            return (PageResult<SimpleTestPaperVO>) result.end();
        }
    }
}
