package com.fuyou.community.sys.util;

import cn.hutool.core.util.ObjectUtil;
import com.fuyou.community.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @Author yanfuyou
 * @Description 文章内容替换工具类
 * @Date 上午1:50 2022/12/10
 */
@Slf4j
public class ArticleContentUtil {

    public static String getContent(String path){
        File file = new File(path);
        if (!file.exists()){
            throw new ServiceException(5000,"文件内容丢失");
        }
        InputStream in = null;
        try{
            in = Files.newInputStream(file.toPath());
            int len = 0;
            byte[] swap = new byte[1024];
            StringBuilder contentBuffer = new StringBuilder("");
            while ((len = in.read(swap)) != -1){
                contentBuffer.append(new String(swap,0,len));
            }
            return contentBuffer.toString();
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ServiceException(5000,"获取文件内容失败");
        }finally {
            if (ObjectUtil.isNotNull(in)){
                try {
                    in.close();
                }catch (IOException e){
                    throw new ServiceException(5000,"文件流关闭异常");
                }
            }
        }
    }
}
