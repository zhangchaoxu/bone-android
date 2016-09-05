package com.idogfooding.bone;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.request.target.ViewTarget;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Base Application
 *
 * @author Charles <zhangchaoxu@gmail.com>
 */
public class BaseApplication extends Application {

    protected static String PREF_NAME = "app.pref";

    static Context _context;
    static Resources _resource;

    @Override
    public void onCreate() {
        super.onCreate();
        _context = getApplicationContext();
        _resource = _context.getResources();
        // fix glide issue "You must not call setTag() on a view Glide is targeting"
        // https://github.com/bumptech/glide/issues/370
        // http://stackoverflow.com/questions/34833627/error-you-must-not-call-settag-on-a-view-glide-is-targeting-when-use-glide/35096552
        ViewTarget.setTagId(R.id.glide_tag);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // add multidex support
        MultiDex.install(this);
    }

    public static synchronized BaseApplication context() {
        return (BaseApplication) _context;
    }

    public static Resources resources() {
        return _resource;
    }

    protected boolean isProcessInRunning() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appList = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : appList) {
            if (info.pid == android.os.Process.myPid()) {
                return true;
            }
        }
        return false;
    }

    // [+] Shared Preference
    public static SharedPreferences getPreferences() {
        return getPreferences(PREF_NAME, Context.MODE_MULTI_PROCESS);
    }

    public static SharedPreferences getPreferences(String name) {
        return getPreferences(name, Context.MODE_MULTI_PROCESS);
    }

    public static SharedPreferences getPreferences(String name, int mode) {
        return context().getSharedPreferences(name, mode);
    }

    public static void set(String key, boolean value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void set(String key, String value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void set(String key, int value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void set(String key, long value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void set(String key, Serializable entity) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key, JSON.toJSONString(entity));
        editor.apply();
    }

    public static boolean get(String key, boolean defValue) {
        return getPreferences().getBoolean(key, defValue);
    }

    public static String get(String key, String defValue) {
        return getPreferences().getString(key, defValue);
    }

    public static int get(String key, int defValue) {
        return getPreferences().getInt(key, defValue);
    }

    public static long get(String key, long defValue) {
        return getPreferences().getLong(key, defValue);
    }

    public static float get(String key, float defValue) {
        return getPreferences().getFloat(key, defValue);
    }

    public static <T> T get(String key, Class<T> clazz, T t) {
        String strValue = getPreferences().getString(key, "");
        if (TextUtils.isEmpty(strValue)) {
            return t;
        } else {
            try {
                return JSON.parseObject(strValue, clazz);
            } catch (Exception e) {
                return t;
            }
        }
    }

    // [+] Last Refresh Time
    public static void setRefreshTime(String key) {
        setRefreshTime(key, Calendar.getInstance().get(Calendar.MILLISECOND));
    }

    public static void setRefreshTime(String key, long value) {
        set("refresh_" + key, value);
    }

    public static long getRefreshTime(String key) {
        return get("refresh_" + key, 0L);
    }

    public static long getRefreshInterval(String key) {
        return Calendar.getInstance().get(Calendar.MILLISECOND) - getRefreshTime(key);
    }
    // [-] Last Refresh Time
    // [-] Shared Preference

    // [+] Show Toast
    private static String lastToast = "";
    private static long lastToastTime;

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
        showToast(context().getString(message), duration, icon, gravity);
    }

    public static void showToast(int message, int duration, int icon, int gravity, Object... args) {
        showToast(context().getString(message, args), duration, icon, gravity);
    }

    public static void showToast(String message, int duration, int icon, int gravity) {
        // return if message is empty
        if (TextUtils.isEmpty(message))
            return;

        // return if message same as the last in a short time(2s)
        if (message.equalsIgnoreCase(lastToast) && Math.abs(System.currentTimeMillis() - lastToastTime) < 2000)
            return;

        Toast toast=Toast.makeText(context(), message, duration);
        if (0 != icon) {
            ImageView imageView= new ImageView(context());
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
    // [-] Show Toast

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // auto gc when the memory is low
        System.gc();
    }

}
