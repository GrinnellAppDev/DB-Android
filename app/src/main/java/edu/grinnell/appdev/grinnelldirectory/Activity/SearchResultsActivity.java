package edu.grinnell.appdev.grinnelldirectory.Activity;

import android.os.Bundle;
//import android.support.v7.app.AlertController;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.Model.Person;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.SearchResultsAdapter;

/**
 * Created by ritikaagarwal on 2/19/17.
 */

public class SearchResultsActivity extends AppCompatActivity {
    private List<Person> mPeople;

    public void populatePeopleList() {
        mPeople = new ArrayList<Person>();
        for(int i = 0; i < 10; i++) {
            Person ritika = new Person();
            Person prabir = new Person();
            ritika.setFirstName("Ritika" + i);
            ritika.setLastName("Agarwal");
            ritika.setImgPath("https://itwebapps.grinnell.edu/PcardImages/moved/93613.jpg");
            ritika.setAddress("Younker");
            ritika.setMajor("Math");
            prabir.setFirstName("Prabir" + i);
            prabir.setLastName("Pradhan");
            prabir.setImgPath("https://itwebapps.grinnell.edu/PcardImages/moved/72038.jpg");
            prabir.setAddress("Rose");
            prabir.setMajor("Math");
            mPeople.add(ritika);
            mPeople.add(prabir);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresults);

        // Lookup the recyclerview in activity layout
        RecyclerView rvSearchResults = (RecyclerView) findViewById(R.id.rvSearchResults);
        // Initialize people list
        populatePeopleList();
        // Create adapter passing in the people list
        SearchResultsAdapter adapter = new SearchResultsAdapter(this, mPeople);
        // Attach the adapter to the recyclerview to populate items
        rvSearchResults.setAdapter(adapter);
        // Set layout manager to position the items
        rvSearchResults.setLayoutManager(new LinearLayoutManager(this));

    }

}
