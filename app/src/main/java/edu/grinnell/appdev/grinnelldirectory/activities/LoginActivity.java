package edu.grinnell.appdev.grinnelldirectory.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.GeneralSecurityException;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.grinnell.appdev.grinnelldirectory.DBAPICaller;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.models.Person;
import edu.grinnell.appdev.grinnelldirectory.models.User;

/**
 * LoginActivity prompts the user to sign in.
 */

public class LoginActivity extends AppCompatActivity implements APICallerInterface {
    @BindView(R.id.username)
    EditText mUsernameEditText;
    @BindView(R.id.password)
    EditText mPasswordEditText;
    @BindView(R.id.login)
    Button mSignInButton;

    @BindView(R.id.login_textview)
    TextView mLoginTextview;

    LoginWebViewClient mLoginWebViewClient;
    ProgressDialog mProgressDialog;

    private String username;
    private String password;
    private final String DB_LOGIN_URL = "https://itwebapps.grinnell.edu/Private/asp/campusdirectory/GcDefault.asp";
    private final String DB_LOGIN_COOKIE = ".AspNet.Cookies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // authenticate user if they are already logged in
        if (isLoggedIn(this)) {
            Intent newActivity = new Intent(this, SearchPagerActivity.class);
            this.startActivity(newActivity);
            /*
            try {
                login(User.getUser(this));
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
            */
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.mLoginWebViewClient = new LoginWebViewClient(this);
        ButterKnife.bind(this);
        setAnimation();
        eraseCookie(this);
    }

    private boolean isLoggedIn(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.sharedprefs_file_key), Context.MODE_PRIVATE);
        return sharedPref.getString(getString(R.string.sharedprefs_login_cookie_key), null) != null;
    }

    private void eraseCookie(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.sharedprefs_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.sharedprefs_login_cookie_key), null);
        editor.commit();
    }

    public void setAnimation() {
        try {
            String callingClass = getIntent().getExtras().getString(getString(R.string.calling_class));
            if (callingClass != null) {
                if (callingClass.contains(getString(R.string.search_pager_activity))) {
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sign in when the sign in button is clicked
     *
     * @param view LoginActivity's view
     */
    @OnClick(R.id.login)
    void signInClicked(View view) {
        /*
        username = mUsernameEditText.getText().toString();
        password = mPasswordEditText.getText().toString();
        User user = new User(username, password);
        login(user);
        */
        // Hide textview and buttno
        mSignInButton.setVisibility(View.INVISIBLE);
        mLoginTextview.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onResume(Bundle savedInstanceState) {

    }

    @Override
    public void onSearchSuccess(List<Person> people) {
        stopProgressDialog();
    }

    /**
     * Save credentials and move to SearchPagerActivity if login succeeded
     *
     * @param success whether the login was successful
     * @param person model for the logged in user
     */
    @BindString(R.string.authentication_failure)
    String authenticationFailure;

    @Override
    public void authenticateUserCallSuccess(boolean success, Person person) {
        stopProgressDialog();
        if (success) {
            try {
                User.saveCredentialsEncrypt(this, username, password);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
                showAlert("Error saving credentials");
            }
            User.saveUserDetails(this, person);

            // If login was successful, navigate to the SearchPagerActivity which allows you
            // to tab between simple search and advanced search.
            navigateToSearchPager();

        } else {
            showAlert(authenticationFailure);
        }
    }

    /**
     * Show an error message if the server returns an error
     *
     * @param fail_message error description
     */
    @BindString(R.string.server_failure)
    String serverFailure;

    @Override
    public void onServerFailure(String fail_message) {
        stopProgressDialog();
        showAlert(serverFailure + fail_message);
    }

    /**
     * Show an error message if the network has an error
     *
     * @param fail_message error description
     */
    @BindString(R.string.networking_error)
    String networkingError;

    @Override
    public void onNetworkingError(String fail_message) {
        stopProgressDialog();
        showAlert(networkingError + fail_message);
    }

    private void login(User user) {
        //DBAPICaller dbapiCaller = new DBAPICaller(user, this);
        //dbapiCaller.authenticateUser();
        startProgressDialog();
    }

    private void showAlert(String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.show();
    }

    @BindString(R.string.logging_in)
    String message;

    private void startProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    private void stopProgressDialog() {
        if (mProgressDialog != null)
            mProgressDialog.cancel();
    }

    private void navigateToSearchPager() {
        Intent intent = new Intent(this, SearchPagerActivity.class);
        intent.putExtra(getString(R.string.calling_class), getString(R.string.login_activity));
        startActivity(intent);
    }

    public class LoginWebViewClient extends WebViewClient {

        Context appcontext;

        public LoginWebViewClient(Context context){
            this.appcontext = context;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            String allCookies = CookieManager.getInstance().getCookie(url);
            if(allCookies != null){
                String[] cookies =allCookies.split(";");
                for (String cookie : cookies ){
                    if(cookie.contains(DB_LOGIN_COOKIE)){
                        String[] loginCookie=cookie.split("=");
                        //saveCookie(loginCookie[1]);
                        webView.setVisibility(View.INVISIBLE);
                        return true;
                    }
                }
            }

            return false;
        }

        private void saveCookie(String cookie) {
            SharedPreferences sharedPref = appcontext.getSharedPreferences(getString(R.string.sharedprefs_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.sharedprefs_login_cookie_key), cookie);
            editor.commit();
        }

    }

}
