package com.fuyou.community.sys.util;

import cn.hutool.crypto.digest.DigestUtil;

public class PasswordUtil {

    public static String excr(String resource,String salt) {
        resource = salt + salt + resource + salt;
        return DigestUtil.md5Hex(resource);
    }

    public static void main(String[] args) {

    }
}
