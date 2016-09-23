package com.idogfooding.bone.utils;

import android.content.Context;
import android.content.pm.PackageInfo;

/**
 * AppInfoUtils
 *
 * @author Charles
 */
public class AppInfoUtils {

    /**
     * get package info
     * @param context
     * @return
     */
    public static PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get package name
     */
    public static String getPackageName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * get the current version name
     */
    public static String getVersionName(Context context) {
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
    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
