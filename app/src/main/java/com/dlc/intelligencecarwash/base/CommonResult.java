package com.dlc.intelligencecarwash.base;

/**
 * Created by YoungeTao on 2017/4/11
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 */

public class CommonResult {

    private int statu;
    private String message;
    private Object data;

    public CommonResult(int statu, String message, Object data) {
        this.statu = statu;
        this.message = message;
        this.data = data;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
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
}
