package com.fuyou.community.sys.constant;

import lombok.Data;
import springfox.documentation.service.ApiListing;

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
        public static final String COMMENT = "comment";

        /**
         * 文章
         */
        public static final String ARTICLE = "article";

        /**
         * 队伍
         */
        public static final String TEAM = "team";
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

    enum Role{
        ORDINARY("ordinary","f695ab4049294c818ab806a1ffa0af93");
        private final String roleName;
        private final String roleId;
        //平台默认用户
        Role(String roleName,String roleId){
            this.roleName=roleName;
            this.roleId= roleId;
        }

        public String getRoleName(){
            return this.roleName;
        }
        public String getRoleId(){
            return this.roleId;
        }
    }
}
