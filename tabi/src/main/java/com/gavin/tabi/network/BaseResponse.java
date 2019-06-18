package com.gavin.tabi.network;

/**
 * @author : com.gavin
 * @date 2018/11/6.
 * 统一的数据返回格式
 */
public class BaseResponse<T> {
    private int code;
    private String message;
    private String showErr;
    private long currentTime;
    public T data;

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

    public String getShowErr() {
        return showErr;
    }

    public void setShowErr(String showErr) {
        this.showErr = showErr;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
