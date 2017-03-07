package com.idogfooding.bone.utils;

import android.os.Build;

/**
 * EaseUtils
 *
 * @author Charles
 */

public class EaseUtils {

    // https://easemob.com/question/9894
    public boolean isSupport() {
        return Build.VERSION.SDK_INT > 23;
    }
}
