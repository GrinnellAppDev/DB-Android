package edu.grinnell.appdev.grinnelldirectory.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

import edu.grinnell.appdev.grinnelldirectory.R;

/**
 * Created by nicholasroberson on 4/16/17.
 */

public class simpleSearchFragment extends Fragment implements Serializable {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.simple_search_fragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        attachUI();
    }

    public void attachUI() {
        // add code to bind the UI to the activity view
    }
}