package com.lczyfz.edp.springboot.demo.sys.service;


import com.lczyfz.edp.springboot.core.entity.CommonResult;
import com.lczyfz.edp.springboot.core.entity.Page;
import com.lczyfz.edp.springboot.core.service.CrudService;
import com.lczyfz.edp.springboot.core.utils.MsgCodeUtils;
import com.lczyfz.edp.springboot.demo.sys.dto.LoginJudgeDTO;
import com.lczyfz.edp.springboot.demo.sys.entity.UserInfo;
import com.lczyfz.edp.springboot.demo.sys.entity.UserInfoExample;
import com.lczyfz.edp.springboot.demo.sys.mapper.UserInfoMapper;
import com.lczyfz.edp.springboot.demo.sys.vo.StudentPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86189
 */
@Service
public class UserInfoService extends CrudService<UserInfoMapper,UserInfo,UserInfoExample> {
    private static final Integer TEACHER_CORD = 2;
    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 判断用户的账户信息
     */
    public LoginJudgeDTO judgeUser(String id, String password){
        LoginJudgeDTO loginJudgeDTO =new LoginJudgeDTO();

        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);

        if(userInfo!=null){
            BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

            System.out.println(encoder.encode(password));

            if(encoder.matches(password,userInfo.getPassword())){
                loginJudgeDTO.setJudge(true);

                loginJudgeDTO.setMes("认证通过");

                loginJudgeDTO.setRole((int)userInfo.getRole());
            }else{
                loginJudgeDTO.setJudge(false);

                loginJudgeDTO.setMes("密码错误");

                loginJudgeDTO.setRole(0);
            }
        }else{
            loginJudgeDTO.setJudge(false);

            loginJudgeDTO.setMes("用户名错误");

            loginJudgeDTO.setRole(0);
        }
        return loginJudgeDTO;
    }

    /**
     * 设置返回result信息
     */
    public CommonResult getResultMes(String userId, String password){
        LoginJudgeDTO loginJudgeDTO=judgeUser(userId, password);

        CommonResult result = new CommonResult().init();
        if(!loginJudgeDTO.getJudge()){
            result.fail(MsgCodeUtils.MSG_CODE_USERNAME_OR_PASSWORD_INCORRECT);

        }else if(!loginJudgeDTO.getRole().equals(TEACHER_CORD)){
            result.fail(MsgCodeUtils.MSG_CODE_SYSTEM_NOT_SUPER_ADMIN_PERMISSION);
        }
        return result;
    }

    /**
     * 获得学生列表
     */
    public Page<StudentPageVO> getStudentList(Page<StudentPageVO> page, int pageNo, int pageSize) {
        UserInfoExample userInfoExample=new UserInfoExample();

        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();

        criteria.andDelFlagEqualTo(String.valueOf((byte) 0))
                .andRoleEqualTo((byte) 1);

        page.setCount(userInfoMapper.countByExample(userInfoExample));

        List<StudentPageVO> pageList = userInfoMapper.getStudentList((pageNo - 1) * pageSize, pageSize);

        page.setList(pageList);

        return page;
    }
}
