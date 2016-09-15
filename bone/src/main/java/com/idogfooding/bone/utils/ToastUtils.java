package com.idogfooding.bone.utils;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.idogfooding.bone.BaseApplication;

/**
 * ToastUtils
 *
 * @author Charles
 */
public class ToastUtils {

    public static Toast toast;
    public static String lastToast = "";
    public static long lastToastTime;
    private static final int THRESHOLD = 2000; // ms

    public static void showToast(int message) {
        showToast(message, Toast.LENGTH_LONG, 0);
    }

    public static void showToast(String message) {
        showToast(message, Toast.LENGTH_LONG, 0, Gravity.BOTTOM);
    }

    public static void showToast(int message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon);
    }

    public static void showToast(String message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon, Gravity.BOTTOM);
    }

    public static void showToastShort(int message) {
        showToast(message, Toast.LENGTH_SHORT, 0);
    }

    public static void showToastShort(String message) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM);
    }

    public static void showToastShort(int message, Object... args) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM, args);
    }

    public static void showToast(int message, int duration, int icon) {
        showToast(message, duration, icon, Gravity.BOTTOM);
    }

    public static void showToast(int message, int duration, int icon, int gravity) {
        showToast(BaseApplication.context().getString(message), duration, icon, gravity);
    }

    public static void showToast(int message, int duration, int icon, int gravity, Object... args) {
        showToast(BaseApplication.context().getString(message, args), duration, icon, gravity);
    }

    public static void showToast(String message, int duration, int icon, int gravity) {
        // return if message is empty
        if (TextUtils.isEmpty(message))
            return;

        // return if message same as the last in a short time(2s)
        if (message.equalsIgnoreCase(lastToast) && Math.abs(System.currentTimeMillis() - lastToastTime) < THRESHOLD)
            return;

        toast = Toast.makeText(BaseApplication.context(), message, duration);
        if (0 != icon) {
            ImageView imageView = new ImageView(BaseApplication.context());
            imageView.setImageResource(icon);
            LinearLayout toastView = (LinearLayout) toast.getView();
            toastView.setOrientation(LinearLayout.HORIZONTAL);
            toastView.addView(imageView, 0);
        }
        if (gravity == Gravity.CENTER) {
            toast.setGravity(gravity, 0, 0);
        } else {
            toast.setGravity(gravity, 0, 35);
        }

        toast.show();
        lastToast = message;
        lastToastTime = System.currentTimeMillis();
    }

}
