package edu.grinnell.appdev.grinnelldirectory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.adapters.SearchPagerAdapter;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchFragmentInterface;
import edu.grinnell.appdev.grinnelldirectory.models.Persons;
import edu.grinnell.appdev.grinnelldirectory.models.User;

public class SearchPagerActivity extends AppCompatActivity implements Serializable {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.search_fab)
    FloatingActionButton mSearchFab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pager);
        ButterKnife.bind(this);

        setupUiElements();
        setAnimation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_logout:
                logoutAndRedirect();
                break;
            case R.id.action_clear:
                getCurrentSearchInterface().clear();
                break;
            case R.id.action_about:
                // pop up a dialog fragment that has a description of the app and how to use it.
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * Call the current fragment's search method when the floating action button is clicked
     */
    @OnClick(R.id.search_fab)
    void onClickSearchFab() {
        getCurrentSearchInterface().search();
    }

    private SearchFragmentInterface getCurrentSearchInterface() {
        SearchPagerAdapter searchPagerAdapter = (SearchPagerAdapter) mViewPager.getAdapter();
        return searchPagerAdapter.getCurrentFragment();
    }


    private void setAnimation() {
        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String callingClass = extras.getString(getString(R.string.calling_class));
                if (callingClass != null) {
                    if (callingClass.contains(getString(R.string.login_activity))) {
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupUiElements() {
        setSupportActionBar(mToolbar);

        mViewPager.setAdapter(new SearchPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void logoutAndRedirect() {
        User.deleteCredentials(this);
        // send user to the login screen
        Intent intent = new Intent(this, LoginActivity.class);
        // clear the back stack
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(getString(R.string.calling_class), SearchPagerActivity.class.toString());
        startActivity(intent);
        finish();
    }
}
