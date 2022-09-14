package com.fuyou.community.code;

import com.fuyou.community.code.model.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * @Author yanfuyou
 * @Description 验证码生成器
 * @Date 下午9:30 2022/9/13
 */
public interface ValidateCodeGenerator {
    /**
     * @Author yanfuyou
     * @Description 创建验证码
     * @Date 下午9:31
     */
    ImageCode creatCode();
}
