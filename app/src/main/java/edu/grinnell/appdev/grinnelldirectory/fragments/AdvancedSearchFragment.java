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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.grinnell.appdev.grinnelldirectory.DBAPICaller;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.activities.SearchResultsActivity;
import edu.grinnell.appdev.grinnelldirectory.interfaces.DbSearchCallback;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchCaller;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchFragmentInterface;
import edu.grinnell.appdev.grinnelldirectory.models.Person;
import edu.grinnell.appdev.grinnelldirectory.models.SimpleResult;
import edu.grinnell.appdev.grinnelldirectory.models.User;
import okhttp3.ResponseBody;


public class AdvancedSearchFragment extends Fragment implements DbSearchCallback,
        SearchFragmentInterface {

    private View view;
    private ProgressDialog mProgressDialog;
    private User mUser;
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
    @BindString(R.string.server_failure)
    String serverFailure;
    /**
     * Show an error message if the network has an error
     *
     * @param failMessage error description
     */
    @BindString(R.string.networking_error)
    String networkingError;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_advanced_search, null);
        ButterKnife.bind(this, view);

        try {
            mUser = User.getUser(getContext());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

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
        if (mProgressDialog != null) {
            return;
        }
        SearchCaller api = new DBAPICaller(this);
        api.advancedSearch(
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
        startProgressDialog();
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

    @Override public void onServerError(int code, ResponseBody error) {
        stopProgressDialog();
//        try {
//            String errorMessage = error.string();
//            showAlert(serverFailure, errorMessage);
//        } catch (IOException e) {
//            showAlert(serverFailure, String.valueOf(code));
//        }
        new AlertDialog.Builder(AdvancedSearchFragment.this.getContext())
                .setTitle("Server Error")
                .setMessage("Please Try Again Later")

                .setPositiveButton("Okay", null)

                //.setNegativeButton("Later", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override public void onNetworkError(String errorMessage) {
        stopProgressDialog();
        //showAlert(networkingError, errorMessage);
        new AlertDialog.Builder(AdvancedSearchFragment.this.getContext())
                .setTitle("Server Error")
                .setMessage("Please Try Again Later")

                .setPositiveButton("Okay", null)

                //.setNegativeButton("Later", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
