package com.lczyfz.edp.springboot.demo.sys.controller;

import com.lczyfz.edp.springboot.core.controller.BaseController;
import com.lczyfz.edp.springboot.core.entity.CommonResult;
import com.lczyfz.edp.springboot.core.entity.Page;
import com.lczyfz.edp.springboot.core.entity.PageResult;
import com.lczyfz.edp.springboot.core.utils.MsgCodeUtils;
import com.lczyfz.edp.springboot.demo.sys.entity.QuestionInfo;
import com.lczyfz.edp.springboot.demo.sys.mapper.QuestionInfoMapper;
import com.lczyfz.edp.springboot.demo.sys.service.QuestionInfoService;
import com.lczyfz.edp.springboot.demo.sys.service.UserInfoService;
import com.lczyfz.edp.springboot.demo.sys.util.OssManagerUtil;
import com.lczyfz.edp.springboot.demo.sys.vo.DetailedQuestionVO;
import com.lczyfz.edp.springboot.demo.sys.vo.QuestionPageVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



/**
 * @author 86189
 */
@RestController
@RequestMapping("/question")
@Api(value = "QuestionController",tags = "题目接口")
public class QuestionController extends BaseController {

    @Autowired
    QuestionInfoService questionInfoService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    QuestionInfoMapper questionInfoMapper;

    @ApiOperation(value = "单题题目详情",notes = "根据id查询题目详情")
    @RequestMapping(value = "/details",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
            @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
            @ApiImplicitParam(name = "questionId",value = "题目id",readOnly = true)
    })
    public DetailedQuestionVO questionDetails(@RequestHeader String userId, @RequestHeader String password, long questionId){
        return questionInfoService.questionDetails(userId,password,questionId);
    }

    @ApiOperation(value = "新建题目",notes = "仅限教师创建题目")
    @PostMapping(value = "/add",consumes = "multipart/form-data")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "description",value = "问题描述",readOnly = true),
                    @ApiImplicitParam(name = "remarks",value = "备注",readOnly = true)
            }
    )
    @ResponseBody
    public CommonResult addQuestion(@RequestHeader String userId, @RequestHeader String password,
                                @RequestPart(value = "file",required = false) @ApiParam("图片") MultipartFile file, String description, String remarks) {
        CommonResult result = userInfoService.getResultMes(userId, password);

        if(!result.isSuccess()){
            logger.info(result.getErrMsg());
        }else{
            String picture;

            if(file==null){
                picture = "null";
            }else{
                picture = OssManagerUtil.getUrl(file);
            }

            QuestionInfo questionInfo=new QuestionInfo(userId,description,picture,remarks);

            questionInfoMapper.insert(questionInfo);

            result.success("questionInfo",questionInfo );

            logger.info("新增成功！");
        }
        return (CommonResult) result.end();
    }

    @ApiOperation(value = "修改题目",notes = "仅限教师修改题目")
    @PostMapping(value = "/update",consumes = "multipart/form-data")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "questionId",value = "题目id",readOnly = true),
                    @ApiImplicitParam(name = "description",value = "问题描述",readOnly = true),
                    @ApiImplicitParam(name = "remarks",value = "备注",readOnly = true)
            }
    )
    @ResponseBody
    public CommonResult updateQuestion(@RequestHeader String userId, @RequestHeader String password,String questionId,
                                           @RequestPart(value = "file",required = false) @ApiParam("图片") MultipartFile file,
                                       String description, String remarks) {
        CommonResult result = userInfoService.getResultMes(userId, password);

        if(!result.isSuccess()){
            logger.info(result.getErrMsg());
        }else{
            String picture;

            if(file==null){
                picture = "null";
            }else{
                picture = OssManagerUtil.getUrl(file);
            }

            QuestionInfo questionInfo=new QuestionInfo(description,picture,remarks);

            questionInfo.setUpdateBy(userId);

            questionInfo.setId(questionId);

            questionInfo.setNewRecord(false);

            questionInfo.setCreateDate(null);

            questionInfo.setCreateBy(null);

            questionInfoService.save(questionInfo);


            result.success("questionInfo",questionInfo );

            logger.info("修改成功！");
        }
        return (CommonResult) result.end();
    }

    @ApiOperation(value = "删除题目",notes = "仅限教师删除题目")
    @PostMapping(value = "/delete",consumes = "multipart/form-data")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "questionId",value = "题目id",readOnly = true)
            }
    )
    @ResponseBody
    public CommonResult deleteQuestion(@RequestHeader String userId, @RequestHeader String password,String questionId) {

        CommonResult result = userInfoService.getResultMes(userId, password);

        if(!result.isSuccess()){
            logger.info(result.getErrMsg());
        }else {

            QuestionInfo questionInfo = new QuestionInfo();

            questionInfo.setId(questionId);

            if (0 < questionInfoService.delete(questionInfo)) {
                result.success();
                logger.info("删除成功！");
            } else {
                result.fail(MsgCodeUtils.MSG_CODE_UNKNOWN);
                logger.info("删除失败！");
            }
        }
        return (CommonResult) result.end();
    }

    @ApiOperation(value = "题库", notes = "仅限老师查询题库")
    @RequestMapping(value = "/selectAll",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId",value = "用户id",readOnly = true),
                    @ApiImplicitParam(name = "password",value = "密码",readOnly = true),
                    @ApiImplicitParam(name = "pageNo",value = "要获得的页码",readOnly = true)
            }
    )
    public PageResult<QuestionPageVO> selectAllByPage(@RequestHeader String userId, @RequestHeader String password,int pageNo){
        PageResult<QuestionPageVO> result = new PageResult<QuestionPageVO>().init();
        CommonResult commonResult = userInfoService.getResultMes(userId, password);

        if(!commonResult.isSuccess()){
            result.fail(commonResult.getMsgCode());

            logger.info(commonResult.getErrMsg());
            return result;
        }else{
            Page<QuestionPageVO> page = new Page<>(pageNo,5, "");

            result.success(questionInfoService.getList(page,pageNo,5));

            return (PageResult<QuestionPageVO>) result.end();
        }
    }
}
