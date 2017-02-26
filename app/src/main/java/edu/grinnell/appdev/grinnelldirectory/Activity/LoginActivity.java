package edu.grinnell.appdev.grinnelldirectory.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.grinnell.appdev.grinnelldirectory.R;

/**
 * LoginActivity prompts the user to sign in.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.username) private EditText mUsernameEditText;
    @BindView(R.id.password) private EditText mPasswordEditText;
    @BindView(R.id.signin) Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    /**
     * Sign in when the sign in button is clicked
     * @param view LoginActivity's view
     */
    @OnClick(R.id.signin)
    private void signIn(View view) {
        String usernameTxt = mUsernameEditText.getText().toString();
        String passwordTxt = mPasswordEditText.getText().toString();
    }
}
