package com.fuyou.community.sys.util;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

/**
 * 邮件发送工具类
 */
@Component
@Data
public class EmailUtil {
    @Value("${spring.mail.username}")
    private String username;
    @Resource
    JavaMailSenderImpl javaMailSender;

    /**
     * 邮件发送
     * @param to 收件人
     * @param topic 主题
     * @param content 内容
     * @param files 附件
     * @return 成功/失败
     */
    public boolean sendMail(String to, String topic, String content, List<File> files){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(username);
            helper.setTo(to);
            helper.setSubject(topic);
            helper.setText(content);
            if (CollectionUtil.isNotEmpty(files)){
                for (File file : files) {
                    helper.addAttachment(file.getName(),file);
                }
            }
            javaMailSender.send(message);
            return true;
        }catch (MessagingException e){
            e.printStackTrace();
            return false;
        }
    }
}
