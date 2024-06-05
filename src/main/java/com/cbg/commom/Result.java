package com.cbg.commom;

public class Result<T> {
    //code属性，用于返回错误码
    private String code;
    //msg属性，用于返回错误信息
    private String msg;
    //data属性，用于返回数据
    private T data;

    //无参构造方法
    public Result() {
    }

    //带有T参数的构造方法
    public Result(T data) {
        this.data = data;
    }

    //成功方法，返回成功结果
    public static Result success() {
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    //带有T参数的成功方法，返回成功结果
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    //错误方法，返回错误结果
    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    //getter和setter方法
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

