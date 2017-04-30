package edu.grinnell.appdev.grinnelldirectory.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.grinnell.appdev.grinnelldirectory.fragments.SimpleSearchFragment;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchFragmentInterface;
import java.io.Serializable;

import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.adapters.SearchPagerAdapter;


public class SearchPagerActivity extends AppCompatActivity implements Serializable {

    @BindView(R.id.search_fab) FloatingActionButton mSearchFab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_pager_activity);

        ButterKnife.bind(this);

        ViewPager searchPager = (ViewPager) findViewById(R.id.pager);
        searchPager.setAdapter(new SearchPagerAdapter(getSupportFragmentManager()));
    }

    /**
     * Call the current fragment's search method when the floating action button is clicked
     */
    @OnClick(R.id.search_fab)
    void onClick() {
        ViewPager searchPager = (ViewPager) findViewById(R.id.pager);
        SearchPagerAdapter searchPagerAdapter = (SearchPagerAdapter) searchPager.getAdapter();
        searchPagerAdapter.getCurrentFragment().search();
    }
}
