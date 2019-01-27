package edu.grinnell.appdev.grinnelldirectory.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import edu.grinnell.appdev.grinnelldirectory.activities.ResultsActivity;
import edu.grinnell.appdev.grinnelldirectory.models.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchFragmentInterface;

import static android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH;

public class SimpleSearchFragment extends Fragment implements SearchFragmentInterface {

    @BindView(R.id.first_name_field)
    EditText mFirstNameEditText;
    @BindView(R.id.last_name_field)
    EditText mLastNameEditText;

    @Nullable
    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_search, container, false);
        ButterKnife.bind(this, view);

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
    }

    @Override
    public void clear() {
        mFirstNameEditText.setText("");
        mLastNameEditText.setText("");
    }

}