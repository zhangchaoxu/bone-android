package com.idogfooding.bone.utils;

import android.content.Context;
import android.content.pm.PackageInfo;

import java.util.List;

/**
 * ApkInstallChecker
 *
 * @author Charles
 */
public class ApkInstaller {

    /**
     * check if app installed
     *
     * @param context
     * @param packageName
     */
    public static boolean checkAppInstalled(Context context, String packageName) {
        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
        boolean installed = false;
        for (PackageInfo packageInfo : packages) {
            if (packageInfo.packageName.equalsIgnoreCase(packageName)) {
                installed = true;
                break;
            }
        }
        return installed;
    }

}
