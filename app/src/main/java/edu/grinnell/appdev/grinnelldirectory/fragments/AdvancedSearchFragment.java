package edu.grinnell.appdev.grinnelldirectory.fragments;

import android.content.Intent;
import android.os.Bundle;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchFragmentInterface;


public class AdvancedSearchFragment extends Fragment implements /*DbSearchCallback,*/
        SearchFragmentInterface {

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

    public void setupSpinner(List<String> list, Spinner spinner) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, list);
        //dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);
    }

    public ArrayList<String> getYears() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        ArrayList<String> years = new ArrayList<String>();
        years.add("Any Class Year");

        if (month > 7)
            year = year + 1;

        for (int i = 0; i < 4; i++) {
            if (i != 0)
                year = year + 1;
            years.add(Integer.toString(year));
        }
        return years;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<String> facDept = Arrays.asList(getResources().getStringArray(R.array.facultydeptarray));
        List<String> studMajor = Arrays.asList(getResources().getStringArray(R.array.studentmajorarray));
        List<String> studConc = Arrays.asList(getResources().getStringArray(R.array.studentconcentrationarray));
        List<String> sgaPos = Arrays.asList(getResources().getStringArray(R.array.sgaarray));
        List<String> hiatusStat = Arrays.asList(getResources().getStringArray(R.array.hiatusarray));
        setupSpinner(getYears(), studentClassSpinner);
        setupSpinner(facDept, facDeptSpinner);
        setupSpinner(studMajor, studentMajorSpinner);
        setupSpinner(studConc, concentrationSpinner);
        setupSpinner(sgaPos, sgaSpinner);
        setupSpinner(hiatusStat, hiatusSpinner);
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
            getSpinnerSelection(studentClassSpinner),
            getSpinnerSelection(facDeptSpinner),
            getSpinnerSelection(studentMajorSpinner),
            getSpinnerSelection(concentrationSpinner),
            getSpinnerSelection(sgaSpinner),
            getSpinnerSelection(hiatusSpinner)
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

    public String getSpinnerSelection(Spinner spinner) {
        Object item = spinner.getSelectedItem();
        if (item == null) {
            return "";
        } else {
            return item.toString();
        }
    }
}
