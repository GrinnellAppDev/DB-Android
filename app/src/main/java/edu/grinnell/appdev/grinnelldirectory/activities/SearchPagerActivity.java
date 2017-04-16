package edu.grinnell.appdev.grinnelldirectory.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.Serializable;

import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.adapters.SearchPagerAdapter;


public class SearchPagerActivity extends AppCompatActivity implements Serializable {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_pager_activity);

        ViewPager searchPager = (ViewPager) findViewById(R.id.pager);
        searchPager.setAdapter(new SearchPagerAdapter(getSupportFragmentManager()));
    }
}
