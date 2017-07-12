package com.idogfooding.sharesdk;


import android.content.Context;

import com.mob.MobSDK;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;

public class OnekeyShareUtils {

    public static void shareLink(Context context, final String url, final String title, final String content, final String imgUrl) {
        MobSDK.init(context);
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setImageUrl(imgUrl);
        oks.setTitle(title);
        oks.setTitleUrl(url);
        oks.setUrl(url);
        oks.setSite(title);
        oks.setSiteUrl(url);
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                if (platform.getName().equalsIgnoreCase("SinaWeibo")) {
                    paramsToShare.setText(title + url);
                } else {
                    paramsToShare.setText(content);
                }
            }
        });
        oks.show(context);
    }

    public static void shareLink(Context context, final String url, final String title, final String imgUrl) {
        MobSDK.init(context);
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setImageUrl(imgUrl);
        oks.setTitle(title);
        oks.setTitleUrl(url);
        oks.setUrl(url);
        oks.setSite(title);
        oks.setSiteUrl(url);
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                if (platform.getName().equalsIgnoreCase("SinaWeibo")) {
                    paramsToShare.setText(title + url);
                } else {
                    paramsToShare.setTitle(title);
                }
            }
        });
        oks.show(context);
    }

}
