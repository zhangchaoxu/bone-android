package com.idogfooding.bone.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * SettingsUtils
 *
 * @author Charles
 */
public class SettingsUtils {

    public static Intent openApplicationSettings(String packageName) {
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        return intent;
    }

    public static void openApplicationSettings(Context context, String packageName) {
        Intent intent = openApplicationSettings(packageName);
        context.startActivity(intent);
    }

    public static void openApplicationSettings(Context context) {
        String packageName = AppInfoUtils.getPackageName(context);
        Intent intent = openApplicationSettings(packageName);
        context.startActivity(intent);
    }

}
