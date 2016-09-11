package com.idogfooding.bone.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/**
 * ActivityUtils
 *
 * @author Charles
 */
public class ActivityUtils {

    /**
     * fix issue: v7.view.ContextThemeWrapper cannot be cast to android.app.Activity
     * http://stackoverflow.com/questions/21657045/contextthemewrapper-cannot-be-cast-to-activity
     *
     * @param ctx
     * @return
     */
    public static Activity getActivityFromContext(Context ctx) {
        if (ctx == null)
            return null;
        else if (ctx instanceof Activity)
            return (Activity)ctx;
        else if (ctx instanceof ContextWrapper)
            return (Activity) ((ContextWrapper)ctx).getBaseContext();

        return null;
    }
}
