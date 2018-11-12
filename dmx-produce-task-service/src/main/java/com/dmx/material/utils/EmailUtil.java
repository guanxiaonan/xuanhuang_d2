package com.dmx.material.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public class EmailUtil {
    @Autowired
    static JavaMailSender mailSender;

    public static void send() throws Exception{
        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setFrom("15924162290@163.com");
        message.setTo("760674018@qq.com");
        message.setSubject("测试邮件主题");
        message.setText("测试邮件内容");
        mailSender.send(mimeMessage);
    }
}
