package edu.grinnell.appdev.grinnelldirectory.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import edu.grinnell.appdev.grinnelldirectory.DBAPICaller;
import edu.grinnell.appdev.grinnelldirectory.interfaces.DbSearchCallback;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchCaller;
import edu.grinnell.appdev.grinnelldirectory.models.Person;

import edu.grinnell.appdev.grinnelldirectory.models.Query;
import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.adapters.SearchPagerAdapter;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchFragmentInterface;
import java.util.List;
import okhttp3.ResponseBody;

/**
 * Parent activity of the simple and advanced search fragments
 */

public class SearchPagerActivity extends AppCompatActivity implements Serializable,
    DbSearchCallback {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.search_fab)
    FloatingActionButton mSearchFab;
    @BindView(R.id.message)
    TextView mErrorMessage;
    @BindView(R.id.retry_button)
    Button mRetryButton;
    @BindView(R.id.connection_progress)
    ProgressBar mConnectionProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pager);
        ButterKnife.bind(this);

        setupUiElements();
        setAnimation();
        testConnection();
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

    @OnClick(R.id.retry_button)
    void testConnection() {
        mConnectionProgress.setVisibility(View.VISIBLE);
        mErrorMessage.setVisibility(View.INVISIBLE);
        mRetryButton.setVisibility(View.INVISIBLE);
        SearchCaller api = new DBAPICaller(this);
        api.search(Query.dummy());
    }

    private void setupUiElements() {
        setSupportActionBar(mToolbar);

        mViewPager.setAdapter(new SearchPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override public void onSuccess(List<Person> people) {
        mConnectionProgress.setVisibility(View.INVISIBLE);
        mTabLayout.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.VISIBLE);
        mSearchFab.setVisibility(View.VISIBLE);
    }

    @Override public void onServerError(int code, ResponseBody error) {
        mConnectionProgress.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
        mRetryButton.setVisibility(View.VISIBLE);
        mErrorMessage.setText(R.string.server_failure);
    }

    @Override public void onNetworkError(String errorMessage) {
        mConnectionProgress.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
        mRetryButton.setVisibility(View.VISIBLE);
        mErrorMessage.setText(R.string.no_connection);
    }
}
