package edu.grinnell.appdev.grinnelldirectory.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.grinnell.appdev.grinnelldirectory.Model.Person;
import edu.grinnell.appdev.grinnelldirectory.R;

public class DetailActivity extends AppCompatActivity {

  Person p;

  @BindView(R.id.name) TextView name;
  @BindView(R.id.phone) TextView phone;
  @BindView(R.id.address) TextView address;
  @BindView(R.id.major) TextView major;
  @BindView(R.id.username) TextView username;
  @BindView(R.id.year) TextView classYear;
  @BindView(R.id.boxNumber) TextView boxNumber;
  @BindView(R.id.concentration) TextView concentration;
  @BindView(R.id.pic) ImageView pic;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    ButterKnife.bind(this);
    phone.setText(p.getPhone());
    name.setText(p.getFirstName());
    address.setText(p.getAddress());
    major.setText(p.getMajor());
    username.setText(p.getUserName());
    classYear.setText(p.getClassYear());
    boxNumber.setText(p.getBox());
    concentration.setText(p.getMinor());
    pic.setImageIcon(p.getImage());
  }

}
