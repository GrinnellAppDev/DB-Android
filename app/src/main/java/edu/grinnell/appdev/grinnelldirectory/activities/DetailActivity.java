package edu.grinnell.appdev.grinnelldirectory.activities;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.BaseInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.models.Person;

public class DetailActivity extends AppCompatActivity {

    public static final int ANIMATION_DURATION = 300;

    private Person person;
    boolean isImageZoomed;
    private Pair<Float, Float> zoomedPicTranslate;
    private Interpolator interpolator = new DecelerateInterpolator();

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

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        person = getIntent().getParcelableExtra(Person.PERSON_KEY);
        if (person != null) {
            setFields();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    private void setFields() {
        name.setText(getString(R.string.full_name, person.getFirstName(), person.getLastName()));

        int year = person.getClassYear();
        if (year == 0) {
            classYear.setText("");
        } else {
            classYear.setText(String.valueOf(year));
        }

        username.setText(person.formattedEmail(this));

        String mjr = person.getMajor();
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

        String ph = String.valueOf(person.getPhone());
        if (ph.isEmpty()) {
            phone.setVisibility(View.GONE);
            headingPhone.setVisibility(View.GONE);
            borderPhone.setVisibility(View.GONE);
        } else {
            phone.setVisibility(View.VISIBLE);
            headingPhone.setVisibility(View.VISIBLE);
            borderPhone.setVisibility(View.VISIBLE);
            phone.setText(String.valueOf(person.getPhone()));
        }

        String addressTxt = person.getAddress();
        if (addressTxt == null || addressTxt.isEmpty()) {
            address.setVisibility(View.GONE);
            headingAddress.setVisibility(View.GONE);
            borderAddress.setVisibility(View.GONE);
        } else {
            address.setVisibility(View.VISIBLE);
            headingAddress.setVisibility(View.VISIBLE);
            borderAddress.setVisibility(View.VISIBLE);
            address.setText(person.getAddress());
        }

        String boxNum = person.getBox();
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

        String con = person.getMinor();
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

        String imgPath = person.getImgPath();
        if (imgPath == null || imgPath.isEmpty()) {
            // do something
            Picasso.with(this).load(R.drawable.person_grey).into(pic);
        } else {
            Picasso.with(this).load(person.getImgPath()).into(pic);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                safeFinish();
                break;
            case R.id.action_email:
                sendEmail();
                break;
            default:
                break;
        }
        return true;
    }

    private void safeFinish() {
        if (Build.VERSION.SDK_INT < 21) {
            finish();
        } else {
            finishAfterTransition();
        }
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
        animDarken.setInterpolator(interpolator);
        target.startAnimation(animDarken);
    }

    void animateScale(float from, float to, View target) {
        ObjectAnimator animScaleNewX = ObjectAnimator.ofFloat(target, "scaleX", from, to);
        ObjectAnimator animScaleNewY = ObjectAnimator.ofFloat(target, "scaleY", from, to);
        animScaleNewX.setDuration(ANIMATION_DURATION);
        animScaleNewY.setDuration(ANIMATION_DURATION);
        animScaleNewX.setInterpolator(interpolator);
        animScaleNewY.setInterpolator(interpolator);
        animScaleNewX.start();
        animScaleNewY.start();
    }

    void animateTranslation(float fromWidth, float fromHeight, float toWidth, float toHeight, View target) {
        ObjectAnimator animTransNewX = ObjectAnimator.ofFloat(target, "translationX", fromWidth, toWidth);
        ObjectAnimator animTransNewY = ObjectAnimator.ofFloat(target, "translationY", fromHeight, toHeight);
        animTransNewX.setDuration(ANIMATION_DURATION);
        animTransNewY.setDuration(ANIMATION_DURATION);
        animTransNewX.setInterpolator(interpolator);
        animTransNewY.setInterpolator(interpolator);
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

        // dimensions of the picture in the beginning
        float picWidth = (float) pic.getWidth();
        float picHeight = (float) pic.getHeight();

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

        if (uname != null && !uname.isEmpty()) {
            /* send mail and handle exception if no mail app is found */
            try {
                String email = getString(R.string.base_email, uname.substring(1, uname.length() - 1));

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
                    Toast.makeText(getApplicationContext(),"Error: could not find email client.",Toast.LENGTH_SHORT).show();}

            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

