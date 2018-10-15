package edu.grinnell.appdev.grinnelldirectory.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import edu.grinnell.appdev.grinnelldirectory.activities.ResultsActivity;
import edu.grinnell.appdev.grinnelldirectory.interfaces.DbSearchCallback;
import edu.grinnell.appdev.grinnelldirectory.models.Query;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;
import edu.grinnell.appdev.grinnelldirectory.DBAPICaller;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.activities.SearchResultsActivity;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchCaller;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchFragmentInterface;
import edu.grinnell.appdev.grinnelldirectory.models.Person;
import edu.grinnell.appdev.grinnelldirectory.models.SimpleResult;
import edu.grinnell.appdev.grinnelldirectory.models.User;
import okhttp3.ResponseBody;

import static android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH;

public class SimpleSearchFragment extends Fragment implements DbSearchCallback,
        SearchFragmentInterface {

    private View view;
    private ProgressDialog mProgressDialog;

    @BindView(R.id.first_name_field)
    EditText mFirstNameEditText;
    @BindView(R.id.last_name_field)
    EditText mLastNameEditText;
    private User mUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_simple_search, null);
        ButterKnife.bind(this, view);

        try {
            mUser = User.getUser(getContext());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        return view;
    }

    /**
     * Search when the last name field is submitted
     *
     * @param view     The text view that was submitted from
     * @param actionId Identifier of the action - should always be EditorInfo.IME_ACTION_SEARCH
     * @return true if action was consumed, false otherwise
     */
    @OnEditorAction(R.id.last_name_field)
    boolean onSubmit(TextView view, int actionId) {
        if (actionId != IME_ACTION_SEARCH) {
            // log smth
            return false;
        }
        search();

        return true; // consumed action
    }

    /**
     * Execute a simple search
     */
    @Override
    public void search() {
        Query query = new Query();
        query.setFirstName(mFirstNameEditText.getText().toString().trim());
        query.setLastName(mLastNameEditText.getText().toString().trim());
        Intent intent = new Intent(getActivity(), ResultsActivity.class);
        intent.putExtra(Query.QUERY_KEY, query);
        startActivity(intent);

        /*

        // new code above
        // old code below

        if (mProgressDialog != null) {
            return;
        }


        String firstName = mFirstNameEditText.getText().toString().trim();
        String lastName = mLastNameEditText.getText().toString().trim();

        SearchCaller api = new DBAPICaller(this);

        api.simpleSearch(lastName, firstName, "", "");
        startProgressDialog();

        */
    }

    @Override
    public void clear() {
        mFirstNameEditText.setText("");
        mLastNameEditText.setText("");
    }

    private void showAlert(String label, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(label + ": " + message);
        builder.show();
    }

    @BindString(R.string.searching)
    String message;

    private void startProgressDialog() {
        mProgressDialog = new ProgressDialog(this.getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    private void stopProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
            mProgressDialog = null;
        }
    }

    @Override public void onSuccess(List<Person> people) {
        stopProgressDialog();
        Intent intent = new Intent(getActivity(), SearchResultsActivity.class);
        intent.putExtra(SimpleResult.SIMPLE_KEY, new SimpleResult(people));
        startActivity(intent);
    }

    @BindString(R.string.server_failure)
    String serverFailure;
    @Override public void onServerError(int code, ResponseBody error) {
        stopProgressDialog();
        try {
            String errorMessage = error.string();
            showAlert(serverFailure, errorMessage);
        } catch (IOException e) {
            showAlert(serverFailure, String.valueOf(code));
        }
    }

    @BindString(R.string.networking_error)
    String networkingError;
    @Override public void onNetworkError(String errorMessage) {
        stopProgressDialog();
        showAlert(networkingError, errorMessage);
    }
}