package com.fuyou.community.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.fuyou.community.common.ResultVo;
import com.sun.org.apache.xpath.internal.objects.XObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author yanfuyou
 * @Description 统一异常处理
 * @Date 下午9:17 2022/9/12
 */
@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResultVo<Object> exception(ServiceException e) {
//        记录日志
        log.error("catch exception:{}", e.getErrorMsg());
//        HttpStatus.INTERNAL_SERVER_ERROR
//        return new ResponseEntity<String>(e.getErrorMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResultVo.fail(e.getErrorCode(), e.getErrorMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultVo<Object> validateException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        log.error("参数有误{}", fieldErrors.get(0).getField());
        return ResultVo.fail(5000, fieldErrors.get(0).getDefaultMessage());
    }

    @ExceptionHandler(NotLoginException.class)
    public ResultVo notLogin(NotLoginException notLoginException){
        log.info("用户未登录！");
        return ResultVo.fail(401,"请登录");
    }
}
