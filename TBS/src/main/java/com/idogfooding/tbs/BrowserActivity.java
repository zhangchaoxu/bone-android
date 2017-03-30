package com.idogfooding.tbs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

/**
 * BrowserActivity
 *
 * @author Charles
 */
public class BrowserActivity extends AppCompatActivity {


    WebView wvBrowser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);

        wvBrowser = (WebView) findViewById(R.id.wv_browser);
    }

    @Override
    protected void onDestroy() {
        wvBrowser.setVisibility(View.GONE);
        wvBrowser.destroy();
        super.onDestroy();
    }
}
