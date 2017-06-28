package com.idogfooding.sharesdk;


import android.content.Context;

import com.mob.MobSDK;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class OnekeyShareUtils {

    /**
     * share link
     * @param context
     * @param url
     * @param title
     * @param imgUrl
     */
    public void shareLink(Context context, String url, String title, String imgUrl) {
        MobSDK.init(context);
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setImageUrl(imgUrl);
        oks.setTitle(title);
        oks.setTitleUrl(url);
        oks.setText(title + url);
        oks.setUrl(url);
        oks.setSite(title);
        oks.setSiteUrl(url);
        oks.show(context);
    }

}
