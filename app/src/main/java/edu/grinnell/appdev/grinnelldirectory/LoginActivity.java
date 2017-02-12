package edu.grinnell.appdev.grinnelldirectory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.username) EditText username;
    @BindView(R.id.password) EditText password;
    @BindView(R.id.signin) Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.signin)
    public void signin(View view) {
        String usernameTxt = username.getText().toString();
        String passwordTxt = password.getText().toString();

        // toast username for testing
        Toast.makeText(this, usernameTxt, Toast.LENGTH_SHORT).show();
        // TODO send username & password to API
    }
}
