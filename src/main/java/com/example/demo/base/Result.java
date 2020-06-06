package com.example.demo.base;

/**
 * API格式封装
 */
public class Result {

    private int code;
    private String message;
    private Object data;

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
        this.code = Status.SUCCESS.getCode();
        this.message = Status.SUCCESS.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result message(int code, String message) {
        return new Result(code, message, null);
    }

    public static Result error(Status status) {
        return new Result(status.getCode(), status.getMessage(), null);
    }

    public static Result success(Object data) {
        return new Result(Status.SUCCESS.getCode(), Status.SUCCESS.getMessage(), data);
    }
}
