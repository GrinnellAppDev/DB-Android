package edu.grinnell.appdev.grinnelldirectory.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    ProgressDialog mProgressDialog;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // authenticate user if they are already logged in
        if (User.isLoggedIn(this)) {
            try {
                login(User.getUser(this));
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        setAnimation();
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
        username = mUsernameEditText.getText().toString();
        password = mPasswordEditText.getText().toString();
        User user = new User(username, password);
        login(user);
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
        DBAPICaller dbapiCaller = new DBAPICaller(user, this);
        dbapiCaller.authenticateUser();
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
}
