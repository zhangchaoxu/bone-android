package com.idogfooding.bone.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.idogfooding.bone.BaseApplication;
import com.tbruyelle.rxpermissions.RxPermissions;

import rx.functions.Action1;

/**
 * TelephoneUtils
 *
 * @author Charles
 */

public class TelephoneUtils {

    /**
     * call telephone
     * do not forget add permission
     * <uses-permission android:name="android.permission.CALL_PHONE"/>
     * @param context
     * @param phone
     */
    public static void call(final Activity context, final String phone) {
        if (TextUtils.isEmpty(phone)) {
            BaseApplication.showToast("电话号码不能为空");
            return;
        }
        new RxPermissions(context)
                .request(Manifest.permission.CALL_PHONE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone)));
                        } else {
                            BaseApplication.showToast("请在设置中打开打电话权限");
                        }
                    }
                });
    }

}
