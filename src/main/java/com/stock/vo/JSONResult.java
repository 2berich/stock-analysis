package com.stock.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(Include.NON_NULL)
public class JSONResult {

    private static final int CODE_SUCCESS = 0;

    private Integer code = CODE_SUCCESS;

    private String msg;

    private Object data;

    private Long ts;

    public JSONResult() {
    }

    public JSONResult(Object data) {
        this(CODE_SUCCESS, null, data);
    }

    public JSONResult(Integer code, String msg) {
        this(code, msg, null);
    }

    public JSONResult(String key, Object value) {
        this.ts = System.currentTimeMillis();
        this.code = 0;
        Map<String, Object> m = new HashMap<String, Object>(1);
        m.put(key, value);
        data = m;
    }

    public JSONResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        ts = System.currentTimeMillis();
    }


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public Long getTs() {
        return ts;
    }
}
