package com.fuyou.community.exception;

import com.fuyou.community.common.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author yanfuyou
 * @Description 统一异常处理
 * @Date 下午9:17 2022/9/12
 */
@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVo<Object> exception(ServiceException e){
//        记录日志
        log.error("catch exception:{}",e.getErrorMsg());
        return ResultVo.fail(e.getErrorCode(),e.getErrorMsg());
    }
}
