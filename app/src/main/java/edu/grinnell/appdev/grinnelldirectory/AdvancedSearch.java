package edu.grinnell.appdev.grinnelldirectory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static edu.grinnell.appdev.grinnelldirectory.R.styleable.View;


public class AdvancedSearch extends AppCompatActivity {

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
        setContentView(R.layout.advanced_search);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.search_button)
    public void submit(View view) {
        // TODO submit data to server...
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
