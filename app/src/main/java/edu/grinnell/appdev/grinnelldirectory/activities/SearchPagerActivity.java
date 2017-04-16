package edu.grinnell.appdev.grinnelldirectory.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.Serializable;

import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.adapters.SearchPagerAdapter;

/**
 * Created by nicholasroberson on 4/16/17.
 */

public class SearchPagerActivity extends AppCompatActivity implements Serializable {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.search_pager_activity);

        ViewPager searchPager = (ViewPager) findViewById(R.id.search_pager);

        searchPager.setAdapter(new SearchPagerAdapter(getSupportFragmentManager()));


        TextView searchTitle = (TextView) findViewById(R.id.searchTitle);
        searchTitle.setText("Search DB");
    }
}
