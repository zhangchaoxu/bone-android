package com.idogfooding.bone.utils;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.f2prateek.rx.preferences.Preference;

/**
 * FastjsonPreferenceAdapter
 * http://f2prateek.com/2015/10/05/rx-preferences/
 *
 * @author Charles
 */
public class FastjsonPreferenceAdapter<T> implements Preference.Adapter<T> {

    private Class<T> clazz;

    public FastjsonPreferenceAdapter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T get(@NonNull String key, @NonNull SharedPreferences preferences) {
        try {
            return JSON.parseObject(preferences.getString(key, ""), clazz);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void set(@NonNull String key, @NonNull T value, @NonNull SharedPreferences.Editor editor) {
        editor.putString(key, JSONObject.toJSONString(value));
    }
}
