package edu.grinnell.appdev.grinnelldirectory.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.adapters.SearchResultsAdapter;
import edu.grinnell.appdev.grinnelldirectory.models.SimpleResult;

public class SearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresults);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SimpleResult result = getIntent().getParcelableExtra(SimpleResult.SIMPLE_KEY);

        RecyclerView rvSearchResults = findViewById(R.id.rvSearchResults);
        SearchResultsAdapter adapter = new SearchResultsAdapter(this, result.getPeople());
        rvSearchResults.setAdapter(adapter);
        rvSearchResults.setLayoutManager(new LinearLayoutManager(this));
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
