package edu.grinnell.appdev.grinnelldirectory.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.models.Person;

public class DetailActivity extends AppCompatActivity {

    Person p;
    @BindView(R.id.pic)
    ImageView pic;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.year)
    TextView classYear;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.heading_phone)
    TextView headingPhone;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.heading_address)
    TextView headingAddress;
    @BindView(R.id.major)
    TextView major;
    @BindView(R.id.heading_major)
    TextView headingMajor;
    @BindView(R.id.boxNumber)
    TextView boxNumber;
    @BindView(R.id.heading_boxNumber)
    TextView headingBoxNumber;
    @BindView(R.id.concentration)
    TextView concentration;
    @BindView(R.id.heading_concentration)
    TextView headingConcentration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        p = (Person) extras.getSerializable(Person.PERSON_KEY);

        if (p != null) {
            setFields();
        }
    }

    private void setFields() {
        name.setText(p.getFirstName() + ' ' + p.getLastName());
        classYear.setText(p.getClassYear());
        String un = p.getUserName();
        if (un == null || un.isEmpty()) {
            String email = p.getEmail();
            username.setText(email.substring(0, email.indexOf('@')));
        } else {
            username.setText(un);
        }

        String mjr = p.getMajor();
        if (mjr == null || mjr.isEmpty()) {
            major.setVisibility(View.GONE);
            headingMajor.setVisibility(View.GONE);
        } else {
            major.setVisibility(View.VISIBLE);
            headingMajor.setVisibility(View.VISIBLE);
            major.setText(mjr);
        }

        String ph = p.getPhone();
        if (ph == null || ph.isEmpty()) {
            phone.setVisibility(View.GONE);
            headingPhone.setVisibility(View.GONE);
        } else {
            phone.setVisibility(View.VISIBLE);
            headingPhone.setVisibility(View.VISIBLE);
            phone.setText(p.getPhone());
        }

        String add = p.getAddress();
        if (add == null || add.isEmpty()) {
            address.setVisibility(View.GONE);
            headingAddress.setVisibility(View.GONE);
        } else {
            address.setVisibility(View.VISIBLE);
            headingAddress.setVisibility(View.VISIBLE);
            address.setText(p.getAddress());
        }

        String boxNum = p.getBox();
        if (boxNum == null || boxNum.isEmpty()) {
            boxNumber.setVisibility(View.GONE);
            headingBoxNumber.setVisibility(View.GONE);
        } else {
            boxNumber.setVisibility(View.VISIBLE);
            headingBoxNumber.setVisibility(View.VISIBLE);
            boxNumber.setText(boxNum);
        }

        String con = p.getMinor();
        if (con == null || con.isEmpty()) {
            concentration.setVisibility(View.GONE);
            headingConcentration.setVisibility(View.GONE);
        } else {
            concentration.setVisibility(View.VISIBLE);
            headingConcentration.setVisibility(View.VISIBLE);
            concentration.setText(con);
        }

        String imgPath = p.getImgPath();
        if (imgPath == null || imgPath.isEmpty()) {
            // do something
        } else {
            Picasso.with(this).load(p.getImgPath()).into(pic);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}
