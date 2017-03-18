package com.idogfooding.bone.network;

import android.text.TextUtils;

/**
 * ApiException
 *
 * @author Charles Zhang
 */
public class ApiException extends RuntimeException {

    private int code;
    private Object data;


    public ApiException(int code, String msg) {
        super(TextUtils.isEmpty(msg) ? "unknown error" : msg);
        this.code = code;
    }

    public ApiException(int code, String msg, Object data){
        super(TextUtils.isEmpty(msg) ? "unknown error" : msg);
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isUnauthorized() {
        return code == 401;
    }

}
