package com.fuyou.community.common;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("标准返回对象")
public class ResultVo<T> {
    private int code;
    private String msg;
    private T records;

    private ResultVo() {
    }

    private ResultVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResultVo(int code, String msg, T records) {
        this.code = code;
        this.msg = msg;
        this.records = records;
    }

    public static ResultVo<Object> success(int code, String msg) {
        return new ResultVo<>(code, msg);
    }

    public static <T> ResultVo<T> success(int code, String msg, T records) {
        return new ResultVo<>(code, msg,records);
    }

    public static ResultVo<Object> fail(int code, String msg) {
        return new ResultVo<>(code, msg);
    }
}
