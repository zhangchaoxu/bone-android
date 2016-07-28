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
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + packageName));
            return intent;
        }
        return new Intent();
    }

    public static void openApplicationSettings(Context context, String packageName) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
            Intent intent = openApplicationSettings(packageName);
            context.startActivity(intent);
        }
    }

}
