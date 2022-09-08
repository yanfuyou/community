package com.fuyou.community.common;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("标准返回对象")
public class ResultVo<T> {
    private int code;
    private String msg;
    private T data;

    private ResultVo() {
    }

    private ResultVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResultVo(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultVo<Object> success(int code, String msg) {
        return new ResultVo<>(code, msg);
    }

    public static <T> ResultVo<T> success(int code, String msg, T data) {
        return new ResultVo<>(code, msg,data);
    }

    public static ResultVo<Object> fail(int code, String msg) {
        return new ResultVo<>(code, msg);
    }
}
