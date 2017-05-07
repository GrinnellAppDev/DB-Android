package edu.grinnell.appdev.grinnelldirectory.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.adapters.SearchResultsAdapter;
import edu.grinnell.appdev.grinnelldirectory.models.Person;
import edu.grinnell.appdev.grinnelldirectory.models.SimpleResult;

public class SearchResultsActivity extends AppCompatActivity {
    private List<Person> mPeopleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresults);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView rvSearchResults = (RecyclerView) findViewById(R.id.rvSearchResults);
        SearchResultsAdapter adapter = new SearchResultsAdapter(this, mPeopleList);
        rvSearchResults.setAdapter(adapter);
        rvSearchResults.setLayoutManager(new LinearLayoutManager(this));

        SimpleResult result = getIntent().getParcelableExtra(SimpleResult.SIMPLE_KEY);
        adapter.updateData(result.getPeople());
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
