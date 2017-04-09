package edu.grinnell.appdev.grinnelldirectory.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.adapters.SearchResultsAdapter;
import edu.grinnell.appdev.grinnelldirectory.models.Person;

public class SearchResultsActivity extends AppCompatActivity {
  private List<Person> mPeopleList;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_searchresults);
    RecyclerView rvSearchResults = (RecyclerView) findViewById(R.id.rvSearchResults);
    SearchResultsAdapter adapter = new SearchResultsAdapter(this, mPeopleList);
    rvSearchResults.setAdapter(adapter);
    rvSearchResults.setLayoutManager(new LinearLayoutManager(this));
  }
}
