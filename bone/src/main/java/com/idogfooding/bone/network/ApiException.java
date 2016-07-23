package com.idogfooding.bone.network;

import android.text.TextUtils;

/**
 * ApiException
 *
 * @author Charles Zhang
 */
public class ApiException extends RuntimeException {

    private int code;

    public ApiException(int code, String msg) {
        super(TextUtils.isEmpty(msg) ? "unknown error" : msg);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public boolean isUnauthorized() {
        return code == 401;
    }

}
