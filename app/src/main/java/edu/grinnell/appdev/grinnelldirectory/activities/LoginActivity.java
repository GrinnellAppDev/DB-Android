package edu.grinnell.appdev.grinnelldirectory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    /**
     * Sign in when the sign in button is clicked
     *
     * @param view LoginActivity's view
     */
    @OnClick(R.id.login)
    void signIn(View view) {
        username = mUsernameEditText.getText().toString();
        password = mPasswordEditText.getText().toString();
        User user = new User(username, password);
        DBAPICaller dbapiCaller = new DBAPICaller(user, this);
        dbapiCaller.authenticateUser();
    }

    @Override
    public void onSearchSuccess(List<Person> people) {
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
        if (success) {
            User.saveCredentials(this, username, password);
            User.saveUserDetails(this, person);

            // If login was successful, navigate to the SearchPagerActivity which allows you
            // to tab between simple search and advanced search.
            Intent intent = new Intent(this, SearchPagerActivity.class);
            startActivity(intent);

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
        showAlert(networkingError + fail_message);
    }

    private void showAlert(String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.show();
    }
}
