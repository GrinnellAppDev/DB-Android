package edu.grinnell.appdev.grinnelldirectory.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.grinnell.appdev.grinnelldirectory.DBAPICaller;
import edu.grinnell.appdev.grinnelldirectory.Interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.Model.Person;
import edu.grinnell.appdev.grinnelldirectory.Model.User;
import edu.grinnell.appdev.grinnelldirectory.R;

import static android.widget.Toast.LENGTH_LONG;
import static edu.grinnell.appdev.grinnelldirectory.Constants.searchConstansts.BUILDING_DORM_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.Constants.searchConstansts.CAMPUS_ADDRESS_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.Constants.searchConstansts.CAMPUS_PHONE_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.Constants.searchConstansts.CLASS_YEAR_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.Constants.searchConstansts.CONCENTRATION_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.Constants.searchConstansts.FAC_STAFF_OFFICE_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.Constants.searchConstansts.FIRST_NAME_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.Constants.searchConstansts.HIATUS_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.Constants.searchConstansts.HOME_ADDRESS_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.Constants.searchConstansts.LAST_NAME_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.Constants.searchConstansts.MAJOR_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.Constants.searchConstansts.POSITION_DESCRIPTION_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.Constants.searchConstansts.SGA_FIELD;
import static edu.grinnell.appdev.grinnelldirectory.Constants.searchConstansts.USERNAME_FIELD;
import static java.sql.Types.NULL;


/**
 * AdvancedSearchActivity that prompts the user to enter search fields
 */
public class AdvancedSearchActivity extends AppCompatActivity
implements APICallerInterface{
    
    //binding the search parameters from layout
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
    @BindView(R.id.search_button)
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);
        ButterKnife.bind(this);
    }

    /**
     * Search for users when search button is clicked
     *
     * @param view SearchActivities' view
     */
    @OnClick(R.id.search_button)
    public void submit(View view) {
        User user =  User.getUser(this);//new User("test1stu", "selfserv1"); //this is a test user for now; i guess i will get it from login activity
        DBAPICaller apiCaller = new DBAPICaller(user, this);
        List<String> searchObject = new ArrayList();

        //populating searchObject with parameters
        searchObject.add(FIRST_NAME_FIELD, lastNameText.getText().toString());
        searchObject.add(LAST_NAME_FIELD, firstNameText.getText().toString());
        searchObject.add(MAJOR_FIELD, studentMajorSpinner.getSelectedItem().toString());
        searchObject.add(FAC_STAFF_OFFICE_FIELD, facDeptSpinner.getSelectedItem().toString());
        searchObject.add(CONCENTRATION_FIELD, concentrationSpinner.getSelectedItem().toString());
        searchObject.add(SGA_FIELD, sgaSpinner.getSelectedItem().toString());
        searchObject.add(USERNAME_FIELD, usernameText.getText().toString());
        searchObject.add(CAMPUS_PHONE_FIELD, phoneText.getText().toString());
        searchObject.add(HIATUS_FIELD, hiatusSpinner.getSelectedItem().toString());
        searchObject.add(HOME_ADDRESS_FIELD, homeAddressText.getText().toString());
        searchObject.add(CLASS_YEAR_FIELD, studentClassSpinner.getSelectedItem().toString());
        searchObject.add(CAMPUS_ADDRESS_FIELD, campusAddressText.getText().toString());
        searchObject.add(BUILDING_DORM_FIELD, ""); //student building
        searchObject.add(POSITION_DESCRIPTION_FIELD, ""); //position

        if (searchObject != null) {
            apiCaller.advancedSearch(searchObject);
        }
        //search toast after button gets pressed
        Toast.makeText(this, R.string.search_toast, LENGTH_LONG).show();
    }

    /**
     * all functions below are inherited from other class and no need to override them
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onSearchSuccess(List<Person> people) {
        //call to the seach results will be called here
    }

    @Override
    public void authenticateUserCallSuccess(boolean success, Person person) {

    }

    @Override
    public void onServerFailure(String fail_message) {

    }

    @Override
    public void onNetworkingError(String fail_message) {

    }
}
