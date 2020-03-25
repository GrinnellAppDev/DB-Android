package edu.grinnell.appdev.grinnelldirectory.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.grinnell.appdev.grinnelldirectory.R;

public class WebLoginActivity extends AppCompatActivity {

    @BindView(R.id.login_webview)
    WebView mLoginWebview;

    private final String DB_LOGIN_URL = "https://itwebapps.grinnell.edu/Private/asp/campusdirectory/GcDefault.asp";
    private final String DB_LOGIN_COOKIE = ".AspNet.Cookies";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_login);
        ButterKnife.bind(this);
        clearCookies(this);
        mLoginWebview.getSettings().setJavaScriptEnabled(true);
        mLoginWebview.setWebViewClient(new LoginWebViewClient(this));
        mLoginWebview.loadUrl(DB_LOGIN_URL);

    }

    @SuppressWarnings("deprecation")
    public static void clearCookies(Context context)
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else
        {
            CookieSyncManager cookieSyncMngr=CookieSyncManager.createInstance(context);
            cookieSyncMngr.startSync();
            CookieManager cookieManager=CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }

    private void saveLoginCookie(Context context) {

    }

    protected class LoginWebViewClient extends WebViewClient {

        Activity parentActivity;

        public LoginWebViewClient(Activity activity){
            this.parentActivity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            String allCookies = CookieManager.getInstance().getCookie(url);
            if(allCookies != null){
                String[] cookies =allCookies.split(";");
                for (String cookie : cookies){
                    if(cookie.contains(DB_LOGIN_COOKIE)){
                        String[] loginCookie=cookie.split("=");
                        saveCookie(loginCookie[1]);
                        parentActivity.finish();
                        return true;
                    }
                }
            }

            return false;
        }

        private void saveCookie(String cookie) {
            SharedPreferences sharedPref = parentActivity.getSharedPreferences(getString(R.string.sharedprefs_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.sharedprefs_login_cookie_key), cookie);
            editor.commit();
        }

    }
}
