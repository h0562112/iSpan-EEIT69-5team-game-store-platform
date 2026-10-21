package com.ispan.eeit69.utils.memberutils;

import java.util.HashMap;
import java.util.Map;



public class Msg {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    public static Msg success() {
        Msg msg = new Msg();
        msg.setSuccess(true);
        msg.setCode(200);
        msg.setMessage("成功");
        return msg;
    }

    public static Msg fail() {
        Msg msg = new Msg();
        msg.setSuccess(false);
        msg.setCode(100);
        msg.setMessage("失敗");
        return msg;
    }

    public Msg setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public Msg setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Msg setMessage(String message) {
        this.message = message;
        return this;
    }

    public Msg addData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Msg data(String key,Object value){
        this.data.put(key,value);
        return this;
    }
    
    public Boolean getSuccess() {
        return success;
    }
    // 其他 getter 和 setter 方法

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
