package edu.grinnell.appdev.grinnelldirectory.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.squareup.picasso.Picasso;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.grinnell.appdev.grinnelldirectory.models.Person;
import edu.grinnell.appdev.grinnelldirectory.R;

import static android.view.View.GONE;

public class DetailActivity extends AppCompatActivity {

  Person p;
  @BindView(R.id.pic) ImageView pic;
  @BindView(R.id.name) TextView name;
  @BindView(R.id.year) TextView classYear;
  @BindView(R.id.phone) TextView phone;
  @BindView(R.id.username) TextView username;
  @BindView(R.id.heading_phone) TextView headingPhone;
  @BindView(R.id.address) TextView address;
  @BindView(R.id.heading_address) TextView headingAddress;
  @BindView(R.id.major) TextView major;
  @BindView(R.id.heading_major) TextView headingMajor;
  @BindView(R.id.boxNumber) TextView boxNumber;
  @BindView(R.id.heading_boxNumber) TextView headingBoxNumber;
  @BindView(R.id.concentration) TextView concentration;
  @BindView(R.id.heading_concentration) TextView headingConcentration;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    ButterKnife.bind(this);
    name.setText(p.getFirstName() + p.getLastName());
    classYear.setText(p.getClassYear());
    username.setText(p.getUserName());

    String mjr = p.getMajor();
    if (mjr == null || mjr.isEmpty()) {
      major.setVisibility(View.GONE);
      headingMajor.setVisibility(View.GONE);
    } else {
      major.setVisibility(View.VISIBLE);
      headingMajor.setVisibility(View.VISIBLE);
      phone.setText(mjr);
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

    boxNumber.setText(p.getBox());
    concentration.setText(p.getMinor());
    Picasso.with(this).load(p.getImgPath()).into(pic);
  }
}
