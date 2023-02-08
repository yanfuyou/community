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

        /**
         * 视频文件
         */
        public static final String VIDEO = "video";

        /**
         * 用户头像
         */
        public static final String AVATAR = "avatar";

        /**
         * 评论
         */
        public static final String COMMENT = "1";

        /**
         * 文章
         */
        public static final String ARTICLE = "2";
    }

    class Base{
        /**
         * 已删除
         */
        public static final String DELETED = "1";

        /**
         * 未删除
         */
        public static final String NOTDELETED = "0";
    }

    class Tree{
        public static final String ROOT = "0";
    }

    class Status{
        public static final String disable = "1";
        public static final String enable = "0";
    }
}
