package com.idogfooding.bone.utils;

import android.content.Context;

/**
 * Version Utils
 *
 * @author Charles
 */
public class VersionUtils {
    /**
     * get the current version
     */
    public static String getCurrentVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * get the current versionCode
     */
    public static int getCurrentVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
