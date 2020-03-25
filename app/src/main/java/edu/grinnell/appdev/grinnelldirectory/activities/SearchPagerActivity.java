package edu.grinnell.appdev.grinnelldirectory.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import edu.grinnell.appdev.grinnelldirectory.DBAPICaller;
import edu.grinnell.appdev.grinnelldirectory.interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.interfaces.DbSearchCallback;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchCaller;
import edu.grinnell.appdev.grinnelldirectory.models.DBRespoonse;
import edu.grinnell.appdev.grinnelldirectory.models.Person;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.adapters.SearchPagerAdapter;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchFragmentInterface;
import edu.grinnell.appdev.grinnelldirectory.models.User;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Parent activity of the simple and advanced search fragments
 */

public class SearchPagerActivity extends AppCompatActivity implements Serializable,
    DbSearchCallback {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.search_fab)
    FloatingActionButton mSearchFab;
    @BindView(R.id.message)
    TextView mErrorMessage;
    @BindView(R.id.retry_button)
    Button mRetryButton;
    @BindView(R.id.connection_progress)
    ProgressBar mConnectionProgress;

    private User mUser;
    int expiredCookie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pager);
        ButterKnife.bind(this);

        // Prompt to login when user is not logged in
        if(!isLoggedIn(this)) {
            showLoginPrompt(this);
        }else{
            Log.e("credential", getLoginCredential());
        }

        try {
            mUser = User.getUser(this);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        setupUiElements();
        setAnimation();
        testConnection();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_login:
                loginAndRedirect();
                break;
            case R.id.action_clear:
                SearchFragmentInterface searchFragmentInterface = getCurrentSearchInterface();
                if (searchFragmentInterface != null) {
                    searchFragmentInterface.clear();
                }
                break;
            case R.id.action_about:
                // pop up a dialog fragment that has a description of the app and how to use it.
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * Call the current fragment's search method when the floating action button is clicked
     */
    @OnClick(R.id.search_fab)
    void onClickSearchFab() {
        if(!isLoggedIn(this)) {
            showLoginPrompt(this);
            return;
        }
        getCurrentSearchInterface().search();
    }

    private SearchFragmentInterface getCurrentSearchInterface() {
        SearchPagerAdapter searchPagerAdapter = (SearchPagerAdapter) mViewPager.getAdapter();
        return searchPagerAdapter.getCurrentFragment();
    }


    private void setAnimation() {
        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String callingClass = extras.getString(getString(R.string.calling_class));
                if (callingClass != null) {
                    if (callingClass.contains(getString(R.string.login_activity))) {
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.retry_button)
    void testConnection() {
        mConnectionProgress.setVisibility(View.VISIBLE);
        mErrorMessage.setVisibility(View.INVISIBLE);
        mRetryButton.setVisibility(View.INVISIBLE);
        SearchCaller api = new DBAPICaller(this);
        api.simpleSearch("fakeName", "fakeNAme", "fakeMajor", "0", getLoginCredential());
    }

    private void setupUiElements() {
        setSupportActionBar(mToolbar);

        mViewPager.setAdapter(new SearchPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void logoutAndRedirect() {
        User.deleteCredentials(this);
        // send user to the login screen
        Intent intent = new Intent(this, LoginActivity.class);
        // clear the back stack
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(getString(R.string.calling_class), SearchPagerActivity.class.toString());
        startActivity(intent);
        finish();
    }

    private void loginAndRedirect() {
        Intent intent = new Intent(this, WebLoginActivity.class);
        intent.putExtra("EXPIRED_COOKIE", this.expiredCookie);
        startActivity(intent);
    }

    @Override public void onSuccess(DBRespoonse response) {
        mConnectionProgress.setVisibility(View.INVISIBLE);
        mTabLayout.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.VISIBLE);
        mSearchFab.setVisibility(View.VISIBLE);
        if(response.getStatus() == 401) {
            showLoginPrompt(this);
            this.expiredCookie = 1;
            eraseCookie(this);
        }
    }

    @Override public void onServerError(int code, ResponseBody error) {
        mConnectionProgress.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
        mRetryButton.setVisibility(View.VISIBLE);
        mErrorMessage.setText(R.string.server_failure);
    }

    @Override public void onNetworkError(String errorMessage) {
        mConnectionProgress.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
        mRetryButton.setVisibility(View.VISIBLE);
        mErrorMessage.setText(R.string.no_connection);
        showLoginPrompt(this);
    }

    /*
        Methods related to detect login information:

        isLoggedIn : check if login cookie is present
        eraseCookie : remove login cookie
        getLoginCredential : get the login cookie
        showLoginPrompt: show alert dialog to let user login

     */
    private boolean isLoggedIn(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.sharedprefs_file_key), Context.MODE_PRIVATE);
        //String credential = getLoginCredential();
        //Log.e("cookie", credential== null? credential:"");
        return sharedPref.getString(getString(R.string.sharedprefs_login_cookie_key), null) != null;
    }

    protected String getLoginCredential() {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.sharedprefs_file_key), Context.MODE_PRIVATE);
        return sharedPref.getString(getString(R.string.sharedprefs_login_cookie_key), null);
    }

    private void eraseCookie(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.sharedprefs_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.sharedprefs_login_cookie_key), null);
        editor.commit();
    }

    public static void showLoginPrompt(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("You are not logged in or your login credential is no longer valid! " +
                "\n Login first to search in Grinnell Database: click on the three dots from upper right corner and select login.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
