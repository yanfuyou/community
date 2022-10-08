package com.fuyou.community.sys.constant;
/**
 * @Author yanfuyou
 * @Description 系统的常量类
 * @Date 下午5:44 2022/9/25
 */
public interface Constant {
    /**
     * 是否包含附件
     */
    class ArticleEnc{
        public static final String HAS_ENC = "1";
        public static final String NO_ENC = "0";
    }

    /**
     * 业务条线
     */
    class BizType{
        /**
         * 附件
         */
        public static final String ENCL = "enclosure";
        /**
         * 独立文件
         */
        public static final String FILE = "file";
    }

}