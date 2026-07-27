package com.why.honk;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.os.VibrationEffect;
import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.JavascriptInterface;

public class MainActivity extends Activity {

    private WebView webView;


    public class AndroidBridge {

        @JavascriptInterface
        public void vibrate() {

            Vibrator vibrator =
                (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            if (android.os.Build.VERSION.SDK_INT >= 26) {

                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        100,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                );

            } else {

                vibrator.vibrate(100);

            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme);

        webView = new WebView(this);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.addJavascriptInterface(
            new AndroidBridge(),
            "Android"
        );

        setContentView(webView);

        webView.loadUrl(
            "file:///android_asset/index.html"
        );
    }
}