package edu.grinnell.appdev.grinnelldirectory.activities;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.models.Person;

public class DetailActivity extends AppCompatActivity {

    public static final int ANIMATION_DURATION = 500;

    Person p;
    boolean isImageZoomed;
    Pair<Float, Float> screenDimens;
    Pair<Float, Float> initialPicDimens;
    Pair<Float, Float> zoomedPicTranslate;

    @BindView(R.id.relative_layout)
    View relativeLayout;
    @BindView(R.id.pic)
    ImageView pic;
    @BindView(R.id.card_view)
    CardView picHolder;
    float picScaleFactor;
    @BindView(R.id.dimmer)
    View dimmer;
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
    @BindView(R.id.email_button)
    Button emailButton;
    @BindView(R.id.call_button)
    Button callButton;
    @BindView(R.id.border_address)
    TextView borderAddress;
    @BindView(R.id.border_box_number)
    TextView borderBoxNum;
    @BindView(R.id.border_concentration)
    TextView borderConcentration;
    @BindView(R.id.border_major)
    TextView borderMajor;
    @BindView(R.id.border_phone)
    TextView borderPhone;


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

        /* set on click for email button */
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* call function to send email */
                sendEmail();
            }
        });

        /* set on click for call button */
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* call function to call student/professor/individual */
                sendCall();
            }
        });
    }

    private void setFields() {
        name.setText(p.getFirstName() + ' ' + p.getLastName());
        classYear.setText(p.getClassYear());

        String un = p.getUserName();
        if (un == null || un.isEmpty()) {
            String email = p.getEmail();
            username.setText("[" + email.substring(0, email.indexOf('@')) + "]");
        } else {
            username.setText("[" + un + "]");
        }

        String mjr = p.getMajor();
        if (mjr == null || mjr.isEmpty()) {
            major.setVisibility(View.GONE);
            headingMajor.setVisibility(View.GONE);
            borderMajor.setVisibility(View.GONE);
        } else {
            major.setVisibility(View.VISIBLE);
            headingMajor.setVisibility(View.VISIBLE);
            borderMajor.setVisibility(View.VISIBLE);
            major.setText(mjr);
        }

        String ph = p.getPhone();
        if (ph == null || ph.isEmpty()) {
            phone.setVisibility(View.GONE);
            headingPhone.setVisibility(View.GONE);
            borderPhone.setVisibility(View.GONE);

        } else {
            phone.setVisibility(View.VISIBLE);
            headingPhone.setVisibility(View.VISIBLE);
            borderPhone.setVisibility(View.VISIBLE);
            phone.setText(p.getPhone());
        }

        String add = p.getAddress();
        if (add == null || add.isEmpty()) {
            address.setVisibility(View.GONE);
            headingAddress.setVisibility(View.GONE);
            borderAddress.setVisibility(View.GONE);
        } else {
            address.setVisibility(View.VISIBLE);
            headingAddress.setVisibility(View.VISIBLE);
            borderAddress.setVisibility(View.VISIBLE);
            address.setText(p.getAddress());
        }

        String boxNum = p.getBox();
        if (boxNum == null || boxNum.isEmpty()) {
            boxNumber.setVisibility(View.GONE);
            headingBoxNumber.setVisibility(View.GONE);
            borderBoxNum.setVisibility(View.GONE);
        } else {
            boxNumber.setVisibility(View.VISIBLE);
            headingBoxNumber.setVisibility(View.VISIBLE);
            borderBoxNum.setVisibility(View.VISIBLE);
            boxNumber.setText(boxNum);
        }

        String con = p.getMinor();
        if (con == null || con.isEmpty()) {
            concentration.setVisibility(View.GONE);
            headingConcentration.setVisibility(View.GONE);
            borderConcentration.setVisibility(View.GONE);
        } else {
            concentration.setVisibility(View.VISIBLE);
            headingConcentration.setVisibility(View.VISIBLE);
            borderConcentration.setVisibility(View.VISIBLE);
            concentration.setText(con);
        }

        String imgPath = p.getImgPath();
        if (imgPath == null || imgPath.isEmpty()) {
            // do something
            Picasso.with(this).load(R.drawable.person_grey).into(pic);
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

    @Override
    public void onBackPressed() {
        if (isImageZoomed) {
            unZoomImage();
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.pic)
    public void onClickImage() {
        if (isImageZoomed)
            unZoomImage();
        else
            zoomImage();
    }

    @OnClick(R.id.dimmer)
    public void onClickDimmer() {
        if (isImageZoomed)
            unZoomImage();
    }

    void zoomImage() {
        calculateDimens();
        animateTranslation(0, 0, zoomedPicTranslate.first, zoomedPicTranslate.second, picHolder);
        animateScale(1f, picScaleFactor, picHolder);
        animateAlpha(0f, 1f, dimmer);
        dimmer.setVisibility(View.VISIBLE);
        isImageZoomed = true;
    }

    void unZoomImage() {
        animateTranslation(zoomedPicTranslate.first, zoomedPicTranslate.second, 0, 0, picHolder);
        animateScale(picScaleFactor, 1f, picHolder);
        animateAlpha(1f, 0f, dimmer);
        dimmer.setVisibility(View.GONE);
        isImageZoomed = false;
    }

    void animateAlpha(float from, float to, View target) {
        Animation animDarken = new AlphaAnimation(from, to);
        animDarken.setFillAfter(true);
        animDarken.setDuration(ANIMATION_DURATION);
        animDarken.setInterpolator(new OvershootInterpolator());
        target.startAnimation(animDarken);
    }

    void animateScale(float from, float to, View target) {
        ObjectAnimator animScaleNewX = ObjectAnimator.ofFloat(target, "scaleX", from, to);
        ObjectAnimator animScaleNewY = ObjectAnimator.ofFloat(target, "scaleY", from, to);
        animScaleNewX.setDuration(ANIMATION_DURATION);
        animScaleNewY.setDuration(ANIMATION_DURATION);
        animScaleNewX.setInterpolator(new OvershootInterpolator());
        animScaleNewY.setInterpolator(new OvershootInterpolator());
        animScaleNewX.start();
        animScaleNewY.start();
    }

    void animateTranslation(float fromWidth, float fromHeight, float toWidth, float toHeight, View target) {
        ObjectAnimator animTransNewX = ObjectAnimator.ofFloat(target, "translationX", fromWidth, toWidth);
        ObjectAnimator animTransNewY = ObjectAnimator.ofFloat(target, "translationY", fromHeight, toHeight);
        animTransNewX.setDuration(ANIMATION_DURATION);
        animTransNewY.setDuration(ANIMATION_DURATION);
        animTransNewX.setInterpolator(new OvershootInterpolator());
        animTransNewY.setInterpolator(new OvershootInterpolator());
        animTransNewX.start();
        animTransNewY.start();
    }

    /**
     * Calculates the dimensions and coordinates used for zooming the person
     * image when clicked.
     */
    private void calculateDimens() {
        // dimensions of the view container
        float screenWidth = relativeLayout.getWidth();
        float screenHeight = relativeLayout.getHeight();
        screenDimens = new Pair<>(screenWidth, screenHeight);

        // dimensions of the picture in the beginning
        float picWidth = (float) pic.getWidth();
        float picHeight = (float) pic.getHeight();
        initialPicDimens = new Pair<>(picWidth, picHeight);

        picScaleFactor = Math.min((0.8f * screenHeight) / picHeight, (0.8f * screenWidth) / picWidth);

        // distance to translate the view when
        float picZoomX = screenWidth / 2f - (picHolder.getWidth() / 2f + picHolder.getLeft());
        float picZoomY = screenHeight / 2f - (picHolder.getHeight() / 2f + picHolder.getTop());
        zoomedPicTranslate = new Pair<>(picZoomX, picZoomY);
    }

    /**
     * Function to open mail app on phone with empty email to user that was clicked on.
     */
    private void sendEmail() {

        /* get username */
        String uname = (String) username.getText();

        if (uname != null || !uname.isEmpty()) {
            /* send mail and handle exception if no mail app is found */
            try {
                String email  = uname.substring(1,uname.length() - 1) + "@grinnell.edu";

                PackageManager pm = this.getPackageManager();

                /* create intent and set fields */
                String[] emails = {email};
                String subject = "your subject";
                String message = "your message";

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, emails);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, message);

                emailIntent.setType("message/rfc822");

                /* get list of applications that can send mail */
                List<ResolveInfo> resolveInfos = pm.queryIntentActivities(emailIntent, 0); // returns all applications which can listen to the SEND Intent

                /* if list size is not zero, start intent */
                if (resolveInfos.size() != 0) {
                    startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
                }
                /* else show alert asking user to download email client */
                else {
                    showEmailAlert();
                }

            } catch (ActivityNotFoundException e) {

                e.printStackTrace();
            }
        }
    }

    private void showEmailAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(DetailActivity.this).create();
        alertDialog.setTitle("Email Request Failed");
        alertDialog.setMessage("No active email account was found.\n" +
                "(1) Download one or (2) log into an existing one and try again.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    /**
     * Function to open call app on phone with an active call to the person the user is viewing.
     */
    private void sendCall() {

    }
}

