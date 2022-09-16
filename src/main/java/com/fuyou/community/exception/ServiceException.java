package com.fuyou.community.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yanfuyou
 * @Description 自定义业务异常
 * @Date 下午9:13 2022/9/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceException extends RuntimeException{
    /**
     * 异常代码
     */
    private Integer errorCode;
    /**
     * 异常信息
     */
    private String errorMsg;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
