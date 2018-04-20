package edu.grinnell.appdev.grinnelldirectory.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.adapters.SearchResultsAdapter;
import edu.grinnell.appdev.grinnelldirectory.models.Person;
import edu.grinnell.appdev.grinnelldirectory.models.Persons;
import edu.grinnell.appdev.grinnelldirectory.models.SimpleResult;

public class SearchResultsActivity extends AppCompatActivity {
    private List<Person> mPeopleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresults);

        loadPersons();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView rvSearchResults = (RecyclerView) findViewById(R.id.rvSearchResults);
        SearchResultsAdapter adapter = new SearchResultsAdapter(this, mPeopleList);
        rvSearchResults.setAdapter(adapter);
        rvSearchResults.setLayoutManager(new LinearLayoutManager(this));

        SimpleResult result = getIntent().getParcelableExtra(SimpleResult.SIMPLE_KEY);
        // REMOVED
        //adapter.updateData(result.getPeople());
        // FOR
        adapter.updateData(mPeopleList);
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


    private void loadPersons() {
        // get multiple persons
        String json = loadJSONFromAsset("dummyData.json");
        Persons persons = new Gson().fromJson(json, Persons.class);
        System.out.println(persons.getPersons().get(0).getFirstName());

        // return our persons object
        this.mPeopleList = persons.getPersons();
    }

    public String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = this.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
