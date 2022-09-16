package com.fuyou.community.code.model;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Author yanfuyou
 * @Description 验证码
 * @Date 下午9:27 2022/9/13
 */
@Data
public class ImageCode {
        /**
         * 图片
         */
        private BufferedImage image;
        /**
         * 随机数
         */
        private String code;

        public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
            this.image = image;
            this.code = code;
        }
        public ImageCode(BufferedImage image, String code, int  expireIn) {
            this.image = image;
            this.code = code;
        }


}
