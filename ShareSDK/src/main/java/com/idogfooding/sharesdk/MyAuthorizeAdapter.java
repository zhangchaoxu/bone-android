package com.idogfooding.sharesdk;

import cn.sharesdk.framework.authorize.AuthorizeAdapter;

/**
 * MyAuthorizeAdapter
 *
 * see {http://bbs.mob.com/forum.php?mod=viewthread&tid=278&page=1&extra=#pid870}
 * see {http://wiki.mob.com/%E5%BF%AB%E6%8D%B7%E5%88%86%E4%BA%AB%E5%8F%8A%E5%85%B6%E8%87%AA%E5%AE%9A%E4%B9%89/#h1-4}
 */
public class MyAuthorizeAdapter extends AuthorizeAdapter {

    @Override
    public void onCreate() {
        super.onCreate();
        hideShareSDKLogo();
        disablePopUpAnimation();
    }
}
