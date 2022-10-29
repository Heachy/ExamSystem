package com.lczyfz.edp.springboot.demo.sys.util;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author 86189
 */
public class EmailUtil {
    /**
     *利用qq邮箱发送短信提醒
     */
    public static Boolean emailSend(String email,String contents)  {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.qq.com");

        mailSender.setUsername("xxxx@qq.com");

        mailSender.setPassword("xxxx");

        mailSender.setPort(465);

        mailSender.setProtocol("smtps");

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("xxxx.com");

        message.setTo(email);

        message.setSubject("【考试服务系统】试卷待批改提醒");

        message.setText(contents);

        try {
            mailSender.send(message);
        }catch (MailException ignored){
            return false;
        }

        return true;
    }
}
