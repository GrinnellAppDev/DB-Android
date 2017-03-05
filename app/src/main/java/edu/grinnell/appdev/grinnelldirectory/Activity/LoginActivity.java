package edu.grinnell.appdev.grinnelldirectory.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.grinnell.appdev.grinnelldirectory.DBAPICaller;
import edu.grinnell.appdev.grinnelldirectory.Interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.Model.Person;
import edu.grinnell.appdev.grinnelldirectory.Model.User;
import edu.grinnell.appdev.grinnelldirectory.R;
import java.util.List;

/**
 * LoginActivity prompts the user to sign in.
 */

// make this activity default?
public class LoginActivity extends AppCompatActivity implements APICallerInterface {
    @BindView(R.id.username)
    EditText mUsernameEditText;
    @BindView(R.id.password)
    EditText mPasswordEditText;
    @BindView(R.id.login)
    Button mSignInButton;

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
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        User user = new User(username, password);
        DBAPICaller dbapiCaller = new DBAPICaller(user, this);
        dbapiCaller.authenticateUser();
    }

    @Override public void onSearchSuccess(List<Person> people) {}

    /**
     * Save credentials and move to MainActivity if login succeeded
     *
     * @param success whether the login was successful
     * @param person model for the logged in user
     */
    @Override public void authenticateUserCallSuccess(boolean success, Person person) {
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        if (success) {
            User.saveCredentials(getApplicationContext(), username, password);
            User.saveUserDetails(getApplicationContext(), person);

            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage(R.string.authentication_failure);
            builder.show();
        }
    }

    /**
     * Show an error message if the server returns an error
     *
     * @param fail_message error description
     */
    @Override public void onServerFailure(String fail_message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage(R.string.server_failure + fail_message);
        builder.show();
    }

    /**
     * Show an error message if the network has an error
     *
     * @param fail_message error description
     */
    @Override public void onNetworkingError(String fail_message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage(R.string.networking_error + fail_message);
        builder.show();
    }
}
