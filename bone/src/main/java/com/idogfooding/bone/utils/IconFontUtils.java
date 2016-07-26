package com.idogfooding.bone.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * IconFontUtils
 *
 * @author Charles
 */
public class IconFontUtils {

    public static void setIconFont(Context context, String fontName, TextView view) {
        if (null == context || null == view || TextUtils.isEmpty(fontName))
            return;

        Typeface iconfont = Typeface.createFromAsset(context.getAssets(), fontName);
        view.setTypeface(iconfont);
    }
}
