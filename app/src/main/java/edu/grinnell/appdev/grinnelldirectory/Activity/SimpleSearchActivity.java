package edu.grinnell.appdev.grinnelldirectory.Activity;

import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.gson.Gson;
import edu.grinnell.appdev.grinnelldirectory.DBAPICaller;
import edu.grinnell.appdev.grinnelldirectory.Interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.Model.Person;
import edu.grinnell.appdev.grinnelldirectory.Model.SimpleResult;
import edu.grinnell.appdev.grinnelldirectory.Model.User;
import edu.grinnell.appdev.grinnelldirectory.R;
import java.util.ArrayList;
import java.util.List;

/**
 * SimpleSearchActivity allows the user to search with one text field.
 */

public class SimpleSearchActivity extends AppCompatActivity implements APICallerInterface {
    @BindView(R.id.first_name_field) EditText mFirstNameEditText;
    @BindView(R.id.last_name_field) EditText mLastNameEditText;
    @BindView(R.id.search) Button mSearchButton;

    public static final String BUNDLE_KEY = "BUNDLE_KEY";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_search);
        ButterKnife.bind(this);
    }

    /**
     * Search when the search button is pressed
     *
     * @param view SimpleSearchActvity's view
     */
    @OnClick(R.id.search) void search(View view) {
        User user = User.getUser(this);

        String firstName = mFirstNameEditText.getText().toString();
        String lastName = mLastNameEditText.getText().toString();

        DBAPICaller dbapiCaller = new DBAPICaller(user, this);

        List<String> query = new ArrayList<>();
        query.add(firstName);
        query.add(lastName);
        query.add("");
        query.add("");

        dbapiCaller.simpleSearch(query);
    }

    /**
     * Bundle people and move to SearchResults Activity if search successful
     *
     * @param people List of person models
     */
    @Override public void onSearchSuccess(List<Person> people) {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();

        bundle.putParcelable(BUNDLE_KEY, new SimpleResult(people));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override public void authenticateUserCallSuccess(boolean success, Person person) {
    }

    /**
     * Show an error message if the server returns an error
     *
     * @param fail_message error description
     */
    @BindString(R.string.server_failure) String serverFailure;
    @Override public void onServerFailure(String fail_message) {
        showAlert(serverFailure + fail_message);
    }

    /**
     * Show an error message if the network has an error
     *
     * @param fail_message error description
     */
    @BindString(R.string.networking_error) String networkingError;
    @Override public void onNetworkingError(String fail_message) {
        showAlert(networkingError + fail_message);
    }

    private void showAlert(String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.show();
    }
}
