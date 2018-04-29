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

import java.io.Serializable;
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
import edu.grinnell.appdev.grinnelldirectory.interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchCaller;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchFragmentInterface;
import edu.grinnell.appdev.grinnelldirectory.models.Person;
import edu.grinnell.appdev.grinnelldirectory.models.SimpleResult;
import edu.grinnell.appdev.grinnelldirectory.models.User;

import static edu.grinnell.appdev.grinnelldirectory.constants.searchConstansts.CAMPUS_ADDRESS_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.constants.searchConstansts.CAMPUS_PHONE_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.constants.searchConstansts.CLASS_YEAR_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.constants.searchConstansts.CONCENTRATION_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.constants.searchConstansts.FAC_STAFF_OFFICE_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.constants.searchConstansts.FIRST_NAME_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.constants.searchConstansts.HIATUS_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.constants.searchConstansts.HOME_ADDRESS_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.constants.searchConstansts.LAST_NAME_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.constants.searchConstansts.MAJOR_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.constants.searchConstansts.SGA_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.constants.searchConstansts.USERNAME_FIELD;


public class AdvancedSearchFragment extends Fragment implements Serializable, APICallerInterface,
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

        SearchCaller api = new DBAPICaller(mUser, this);

        //populating searchObject with parameters
        List<String> searchObject = new ArrayList(14);
        searchObject.add(FIRST_NAME_FIELD, firstNameText.getText().toString().trim());
        searchObject.add(LAST_NAME_FIELD, lastNameText.getText().toString());
        addSpinnerWord(searchObject, studentMajorSpinner, MAJOR_FIELD);
        addSpinnerWord(searchObject, studentClassSpinner, CLASS_YEAR_FIELD);
        addSpinnerWord(searchObject, concentrationSpinner, CONCENTRATION_FIELD);
        addSpinnerWord(searchObject, sgaSpinner, SGA_FIELD);
        searchObject.add(USERNAME_FIELD, usernameText.getText().toString());
        searchObject.add(CAMPUS_PHONE_FIELD, phoneText.getText().toString());
        addSpinnerWord(searchObject, hiatusSpinner, HIATUS_FIELD);
        searchObject.add(HOME_ADDRESS_FIELD, homeAddressText.getText().toString());
        addSpinnerWord(searchObject, facDeptSpinner, FAC_STAFF_OFFICE_FIELD);
        searchObject.add(CAMPUS_ADDRESS_FIELD, campusAddressText.getText().toString());
        searchObject.add("");
        searchObject.add("");

        api.advancedSearch(searchObject);
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

    public void addSpinnerWord(List<String> searchObject, Spinner spin, int fieldNumber) {
        if (spin.getSelectedItemPosition() == 0)
            searchObject.add(fieldNumber, "");
        else
            searchObject.add(fieldNumber, spin.getSelectedItem().toString());
    }

    /**
     * Bundle people and move to SearchResults Activity if search successful
     *
     * @param people List of person models
     */
    @Override
    public void onSearchSuccess(List<Person> people) {
        stopProgressDialog();
        Intent intent = new Intent(getActivity(), SearchResultsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(SimpleResult.SIMPLE_KEY, new SimpleResult(people));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void authenticateUserCallSuccess(boolean success, Person person) {
        // Intentionally left blank
        // The api should never call an authentication callback after a search is requested
    }

    /**
     * Show an error message if the server returns an error
     *
     * @param failMessage error description
     */
    @Override
    public void onServerFailure(String failMessage) {
        stopProgressDialog();
        showAlert(serverFailure, failMessage);
    }

    @Override
    public void onNetworkingError(String failMessage) {
        stopProgressDialog();
        showAlert(networkingError, failMessage);
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
}
