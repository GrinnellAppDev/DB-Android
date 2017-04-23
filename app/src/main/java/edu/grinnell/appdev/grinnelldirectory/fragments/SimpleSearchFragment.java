package edu.grinnell.appdev.grinnelldirectory.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

import edu.grinnell.appdev.grinnelldirectory.R;


public class SimpleSearchFragment extends Fragment implements Serializable {

    private View view;

    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private Button mSearchButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_simple_search, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        attachUI();
    }

    public void attachUI() {
        mFirstNameEditText = (EditText) view.findViewById(R.id.first_name_field);
        mLastNameEditText = (EditText) view.findViewById(R.id.last_name_field);
        mSearchButton = (Button) view.findViewById(R.id.search);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mFirstNameEditText.getText().toString();
                String lastName = mLastNameEditText.getText().toString();
            }
        });
    }
}