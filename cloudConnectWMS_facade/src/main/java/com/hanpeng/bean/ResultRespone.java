package com.hanpeng.bean;

/**
 * @program: cloudConnectWMS
 * @description: 返回信息收集类
 * @author: by hanpeng
 * @create: 2018-12-03 09:33
 **/
public class ResultRespone {

    private boolean success = true;//是否成功

    private String msg;//错误信息或者提示信息

    private Object data;//数据结果集

    public ResultRespone() {
    }

    public ResultRespone(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
