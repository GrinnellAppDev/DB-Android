package edu.grinnell.appdev.grinnelldirectory.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import edu.grinnell.appdev.grinnelldirectory.activities.ResultsActivity;
import edu.grinnell.appdev.grinnelldirectory.models.Query;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchFragmentInterface;


public class AdvancedSearchFragment extends Fragment implements SearchFragmentInterface {

    @BindView(R.id.first_text)
    TextView firstNameText;
    @BindView(R.id.last_text)
    TextView lastNameText;
    @BindView(R.id.username_text)
    TextView usernameText;
    @BindView(R.id.phone_text)
    TextView phoneText;
    @BindView(R.id.campus_address_text)
    TextView campusAddressText;
    @BindView(R.id.home_address_text)
    TextView homeAddressText;
    @BindView(R.id.fac_dept_spinner)
    Spinner facDeptSpinner;
    @BindView(R.id.student_major_spinner)
    Spinner studentMajorSpinner;
    @BindView(R.id.concentration_spinner)
    Spinner concentrationSpinner;
    @BindView(R.id.sga_spinner)
    Spinner sgaSpinner;
    @BindView(R.id.hiatus_spinner)
    Spinner hiatusSpinner;
    @BindView(R.id.student_class_spinner)
    Spinner studentClassSpinner;

    @Nullable
    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_advanced_search, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    private void setupSpinnerStatic(Spinner spinner, @ArrayRes int resId) {
        spinner.setAdapter(ArrayAdapter.createFromResource(getContext(), resId, R.layout.spinner_item));
    }

    private void setupSpinnerDynamic(Spinner spinner, List<String> list) {
        spinner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.spinner_item, list));
    }

    public ArrayList<String> getYears() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        ArrayList<String> years = new ArrayList<>();
        years.add("Any Class Year");

        // If it is the fall semester, the current 4th years graduate next year, not this year
        int nextGraduation;
        if (currentMonth > 7) {
            nextGraduation = currentYear + 1;
        } else {
            nextGraduation = currentYear;
        }

        for (int i = 0; i < 4; i++) {
            years.add(Integer.toString(nextGraduation + i));
        }
        return years;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupSpinnerDynamic(studentClassSpinner, getYears());
        setupSpinnerStatic(facDeptSpinner, R.array.facultydeptarray);
        setupSpinnerStatic(studentMajorSpinner, R.array.studentmajorarray);
        setupSpinnerStatic(concentrationSpinner, R.array.studentconcentrationarray);
        setupSpinnerStatic(sgaSpinner, R.array.sgaarray);
        setupSpinnerStatic(hiatusSpinner, R.array.hiatusarray);
    }

    @Override
    public void search() {
        Query query = new Query(
            lastNameText.getText().toString().trim(),
            firstNameText.getText().toString().trim(),
            usernameText.getText().toString().trim(),
            phoneText.getText().toString().trim(),
            campusAddressText.getText().toString().trim(),
            homeAddressText.getText().toString().trim(),
            studentClassSpinner.getSelectedItem().toString(),
            facDeptSpinner.getSelectedItem().toString(),
            studentMajorSpinner.getSelectedItem().toString(),
            concentrationSpinner.getSelectedItem().toString(),
            sgaSpinner.getSelectedItem().toString(),
            hiatusSpinner.getSelectedItem().toString()
        );
        Intent intent = new Intent(getActivity(), ResultsActivity.class);
        intent.putExtra(Query.QUERY_KEY, query);
        startActivity(intent);
    }

    @Override
    public void clear() {
        firstNameText.setText("");
        lastNameText.setText("");
        usernameText.setText("");
        phoneText.setText("");
        homeAddressText.setText("");
        campusAddressText.setText("");
        studentMajorSpinner.setSelection(0);
        studentClassSpinner.setSelection(0);
        concentrationSpinner.setSelection(0);
        sgaSpinner.setSelection(0);
        hiatusSpinner.setSelection(0);
        facDeptSpinner.setSelection(0);
    }
}
